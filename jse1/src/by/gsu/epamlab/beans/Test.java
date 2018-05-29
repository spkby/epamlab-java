package by.gsu.epamlab.beans;

public class Test {

    private String name;

    public Test(String name) {
        this.name = name;
    }

    public Test() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
