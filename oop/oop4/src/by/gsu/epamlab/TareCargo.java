package by.gsu.epamlab;



public class TareCargo extends Cargo{
    private double density;

    public TareCargo() {
    }

    public TareCargo(String name, double density) {
        super(name);
        this.density = density;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }
}
