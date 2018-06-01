package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;

public class Result {

    private String login;
    private String test;

    private int mark;
    private java.sql.Date date;

    public Result(String login, String test, java.sql.Date date, int mark) {
        this.login = login;
        this.test = test;
        this.mark = mark;
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public String getTest() {
        return test;
    }

    public int getMark() {
        return mark;
    }

    public java.sql.Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return login + Constants.DELIMITER + test + Constants.DELIMITER + date + Constants.DELIMITER + Mark.print(mark);
    }
}
