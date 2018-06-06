package by.gsu.epamlab.load;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.beans.Result;
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

public class ResultImplXml extends DefaultHandler implements IResultDAO {

    private String value;
    private String login;
    private String test;
    private java.sql.Date date;
    private List<Result> results;
    private int currIndex = -1;

    private static enum Tags {
        TEST, LOGIN, RESULTS, TESTS, STUDENT
    }

    private static enum TestAttributes {
        NAME, DATE, MARK
    }

    public ResultImplXml(String fileName) {

        results = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser parser = factory.newSAXParser();
            FileInputStream file = new FileInputStream(fileName + Constants.EXT_XML);
            parser.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }

        if (results.size() > 0) currIndex = 0;
    }

    @Override
    public Result nextResult() {
        return results.get(currIndex++);
    }

    @Override
    public boolean hasResult() {
        return currIndex != -1 && currIndex < results.size();
    }

    @Override
    public void closeReader() {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (Tags.valueOf(qName.toUpperCase()) == Tags.TEST) {
            test = attributes.getValue(TestAttributes.NAME.name().toLowerCase());
            date = java.sql.Date.valueOf(attributes.getValue(TestAttributes.DATE.name().toLowerCase()));
            int mark = (int) (Constants.DECIMAL * Double.parseDouble(attributes.getValue(TestAttributes.MARK.name().toLowerCase())));

            results.add(new Result(login, test, date, mark));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (Tags.valueOf(qName.toUpperCase()) == Tags.LOGIN) {
            login = value;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        value = new String(ch, start, length);
    }


}
