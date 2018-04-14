package by.gsu.epamlab;


public class PieceCargo extends Cargo {
    private double weight;

    public PieceCargo() {
    }

    public PieceCargo(String name, double weight) {
        super(name);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
