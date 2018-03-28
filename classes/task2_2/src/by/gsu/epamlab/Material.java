package by.gsu.epamlab;
/*
public enum Material {
    STEEL("Steel",7850.0),
    COOPER("Cooper",8500.0);

    private String name;
    private double density;

    Material(String name, double density) {
        this.name = name;
        this.density = density;
    }

    public double getDensity() {
        return density;
    }

    @Override
    public String toString() {
        return name + ";" + density;
    }
}
*/

public enum Material {
    STEEL(7850.0), COPPER(8500.0);

    private final double density;

    private Material(double density) {
        this.density = density;
    }
    public String getName() {
        return name().toLowerCase();
    }
    public double getDensity() {
        return density;
    }

    public String toString() {
        return getName() + ";" + density;
    }
}
