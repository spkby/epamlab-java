package by.gsu.epamlab;

public class Test {

    private final String name;
    private final String date;
    private final int mark;

    public Test(String name, String date, int mark) {
        this.name = name;
        this.date = date;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return name + ";" + date + ";" + mark / 10 + "." + mark % 10;
    }
}
