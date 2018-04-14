package by.gsu.epamlab;

public class Staff implements Transferable {

    private String title;
    private double weight;

    public Staff(String title, double weight) {
        this.title = title;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return title + ";" + weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
