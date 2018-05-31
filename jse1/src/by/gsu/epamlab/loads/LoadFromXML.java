package by.gsu.epamlab.loads;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.DAO;
import by.gsu.epamlab.Utils;
import by.gsu.epamlab.Result;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;

public class LoadFromXML extends DefaultHandler implements Load {

    @Override
    public void load(String fileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        //results = new ArrayList<>();

        try {
            SAXParser parser = factory.newSAXParser();
            FileInputStream file = new FileInputStream(fileName);
            parser.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }

        /*for (Result result : results) {
            DAO.add(result);
        }*/
    }

    private String value;
    private String student;
    private String test;
    private java.sql.Date date;
    //private List<Result> results;

    public LoadFromXML() {
    }

    private static enum Tags {
        TEST, LOGIN, RESULTS, TESTS, STUDENT
    }

    private static enum TestAttributes {
        NAME, DATE, MARK
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (Tags.valueOf(qName.toUpperCase()) == Tags.TEST) {
            test = attributes.getValue(TestAttributes.NAME.name().toLowerCase());
            date = Utils.parseDate(attributes.getValue(TestAttributes.DATE.name().toLowerCase()));
            int mark = (int) (Constants.DECIMAL * Double.parseDouble(attributes.getValue(TestAttributes.MARK.name().toLowerCase())));
            DAO.add(new Result(student, test, date, mark));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (Tags.valueOf(qName.toUpperCase()) == Tags.LOGIN) {
            student = value;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        value = new String(ch, start, length);
    }
}
