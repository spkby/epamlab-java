package by.gsu.epamlab;

public class Trial {
    private String name;
    private int mark1;
    private int mark2;
    protected static final int PASS_MARK = 130;

    public Trial() {}

    public Trial(String name, int mark1, int mark2) {
        super();
        this.name = name;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected int result() {
        return mark1 + mark2;
    }

    public boolean isPassed() {
        return result() >= PASS_MARK;
    }

    protected String fieldsName() {
        return name + ";" + mark1 + ";" + mark2;
    }

    @Override
    public String toString() {
        return fieldsName() + ";" + isPassed();
    }
}
