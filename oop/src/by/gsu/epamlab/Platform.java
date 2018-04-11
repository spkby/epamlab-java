package by.gsu.epamlab;

public class Platform implements Cargo {

    private static final int MASS_PLATFORM = 0;

    private int mass;

    public Platform(int mass) {
        this.mass = mass;
    }

    @Override
    public int getMass() {
        return mass + MASS_PLATFORM;
    }

    @Override
    public String toString() {
        return mass + "";
    }
}
