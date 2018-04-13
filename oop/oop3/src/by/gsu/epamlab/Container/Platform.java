package by.gsu.epamlab.Container;

public class Platform {

    private final int PLATFORM_MASS = 0;

    public Platform() {}

    public int getPLATFORM_MASS() {
        return PLATFORM_MASS;
    }

    @Override
    public String toString() {
        return String.valueOf(PLATFORM_MASS);
    }
}
