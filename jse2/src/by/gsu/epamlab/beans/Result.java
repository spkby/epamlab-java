package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;

import java.sql.Date;

public class Result {

    private String student;
    private String test;

    private int mark;
    private Date date;

    public Result(String student, String test, Date date, int mark) {
        this.student = student;
        this.test = test;
        this.mark = mark;
        this.date = date;
    }

    public String getStudent() {
        return student;
    }

    public String getTest() {
        return test;
    }

    public int getMark() {
        return mark;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return student + Constants.DELIMITER + test + Constants.DELIMITER + date + Constants.DELIMITER + Mark.print(mark);
    }
}
