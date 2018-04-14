package by.gsu.epamlab;

public class Man implements Transferable {

    private String name;
    private double weight;

    public Man(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + ";" + weight;
    }
}
