package by.gsu.epamlab;

public class LightTrial extends Trial {
    private final static int PASS_MARK1 = 75;
    private final static int PASS_MARK2 = 65;

    public LightTrial(String name, int mark1, int mark2) {
        super(name, mark1, mark2);
    }

    @Override
    public boolean isPassed() {
        return getMark1() >= PASS_MARK1 && getMark2() >= PASS_MARK2;
    }
}
