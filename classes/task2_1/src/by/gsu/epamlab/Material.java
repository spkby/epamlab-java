package by.gsu.epamlab;

public class Material {

    private final String name;
    private final double density;

    public Material() {
        this(null,0.0);
    }

    public Material(String name, double density) {
        this.name = name;
        this.density = density;
    }

    public double getDensity() {
        return density;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ";" + density;
    }
}
