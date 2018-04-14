package by.gsu.epamlab;

public class PlatformCargo implements Trip {
    private static final String NAME = "Platform";
    private String NAME_LOAD;
    private double weight;
    private final int ID = 3;

    public PlatformCargo(String NAME_LOAD, double weight) {
        this.NAME_LOAD = NAME_LOAD;
        this.weight = weight;
    }

    public static String getNAME() {
        return NAME;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return NAME +";"+ weight;
    }

    @Override
    public double getWeighht() {
        return weight;
    }
}
