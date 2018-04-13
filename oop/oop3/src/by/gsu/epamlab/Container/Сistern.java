package by.gsu.epamlab.Container;

public class Сistern {

    private final int diameter;
    private final int length;
    private final int сisternMass;

    public Сistern() {
        this(0, 0, 0);
    }

    public Сistern(int diameter, int length, int сisternMass) {
        this.diameter = diameter;
        this.length = length;
        this.сisternMass = сisternMass;
    }

    public int getValue(){
        return diameter * length;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getLength() {
        return length;
    }

    public int getСisternMass() {
        return сisternMass;
    }

    @Override
    public String toString() {
        return diameter + ";"
                + length + ";"
                + сisternMass;
    }
}
