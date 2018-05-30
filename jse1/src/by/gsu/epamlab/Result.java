package by.gsu.epamlab;

import java.sql.Date;

public class Result {

    private String student;
    private String test;

    private int mark;
    private Date date;

    public Result() {
    }

    public Result(String student, String test, Date date, int mark) {
        this.student = student;
        this.test = test;
        this.mark = mark;
        this.date = date;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
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
        return student + Constants.DELIMITER + test + Constants.DELIMITER + date + Constants.DELIMITER + PrintMark.print(mark);
    }
}
