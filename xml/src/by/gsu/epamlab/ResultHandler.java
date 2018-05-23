package by.gsu.epamlab;

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

public class ResultHandler extends DefaultHandler {

    private final String path;

    private String value;
    private Student student;
    private Test test;
    private List<Student> students;
    private List<Test> tests;

    public ResultHandler(String path) {
        this.path = path;
        students = new ArrayList<>();
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
            case TESTS:
                tests = new ArrayList<>();
                break;
            case TEST:
                test = new Test(
                        attributes.getValue(TestAttributes.NAME.name().toLowerCase()),
                        attributes.getValue(TestAttributes.DATE.name().toLowerCase()),
                        (int) (10 * Double.parseDouble(attributes.getValue(TestAttributes.MARK.name().toLowerCase()))));
                tests.add(test);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        Tags tag = Tags.valueOf(qName.toUpperCase());

        switch (tag) {
            case STUDENT:
                students.add(student);
                break;
            case LOGIN:
                student.setLogin(value);
                break;
            case TESTS:
                student.setTests(tests);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        value = new String(ch, start, length);
    }

    public List<Student> read() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser parser = factory.newSAXParser();
            FileInputStream file = new FileInputStream(path);
            parser.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalStateException(e);
        }

        return students;
    }

}
