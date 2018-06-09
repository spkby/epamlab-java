package by.gsu.epamlab;

public class ExtraTrial extends Trial {
    private int mark3;
    private final static int PASS_MARK3 = 50;

    public ExtraTrial(String name, int mark1, int mark2, int mark3) {
        super(name, mark1, mark2);
        this.mark3 = mark3;
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    @Override
    public boolean isPassed() {
        return super.isPassed() && mark3 >= PASS_MARK3;
    }

    @Override
    public String toString() {
        return super.fieldsName() + ";" + mark3 + ";" + isPassed();
    }
}
