package by.gsu.epamlab;

import java.util.List;

public class Student {

    private String login;

    private List<Test> tests;

    public Student() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();

        for (Test test : tests){
            output.append(login).append(";").append(test).append("\n");
        }

        return output.toString();
    }
}
