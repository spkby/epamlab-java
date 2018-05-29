package by.gsu.epamlab.beans;

public class Student {

    private String login;

    public Student() {
    }

    public Student(String login) {
        this.login = login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {

        return login;
    }

    @Override
    public String toString() {
        return login;
    }
}
