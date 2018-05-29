package by.gsu.epamlab.loads;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.DB;
import by.gsu.epamlab.Utils;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.beans.Student;
import by.gsu.epamlab.beans.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadFromXML extends DefaultHandler implements Load {

    @Override
    public void load() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        results = new ArrayList<>();

        try {
            SAXParser parser = factory.newSAXParser();
            FileInputStream file = new FileInputStream(path);
            parser.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalStateException(e);
        }

        for (Result result:results){
            DB.add(result);
        }
    }

    private final String path = Constants.PATH + Constants.FILE_NAME + Constants.EXT_XML;

    private String value;
    private Student student;
    private Test test;
    private java.sql.Date date;
    private List<Result> results;

    public LoadFromXML() {
    }

    private static enum Tags {
        STUDENT, TESTS, TEST, LOGIN, RESULTS
    }

    private static enum TestAttributes {
        NAME, DATE, MARK
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        Tags tag = Tags.valueOf(qName.toUpperCase());

        switch (tag) {
            case STUDENT:
                student = new Student();
                break;
            case TEST:
                test = new Test(attributes.getValue(TestAttributes.NAME.name().toLowerCase()));
                date = Utils.parseDate(attributes.getValue(TestAttributes.DATE.name().toLowerCase()));
                int mark = (int) (10 * Double.parseDouble(attributes.getValue(TestAttributes.MARK.name().toLowerCase())));

                results.add(new Result(student, test, date, mark));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        Tags tag = Tags.valueOf(qName.toUpperCase());

        switch (tag) {
            case LOGIN:
                student.setLogin(value);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        value = new String(ch, start, length);
    }
}
