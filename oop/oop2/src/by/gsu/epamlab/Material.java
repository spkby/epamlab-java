package by.gsu.epamlab;

public class Material {
    private final  double density;
    private final String name;

    public Material(double density, String name) {
        this.density = density;
        this.name = name;
    }

    public double getDensity() {
        return density;
    }

    public String getName() {
        return name;
    }
    public String toString() {
        return name+";"+density;
    }
}
