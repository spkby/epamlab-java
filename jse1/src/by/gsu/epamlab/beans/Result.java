package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;

import java.sql.Date;

public class Result {

    private Student student;
    private Test test;

    private int mark;
    private Date date;

    public Result() {
    }

    public Result(Student student, Test test, Date date, int mark) {
        this.student = student;
        this.test = test;
        this.mark = mark;
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return student + Constants.DELIMITER + test + Constants.DELIMITER + date + Constants.DELIMITER + mark;
    }
}
