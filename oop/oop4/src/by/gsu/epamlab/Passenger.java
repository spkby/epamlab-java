package by.gsu.epamlab;

public class Passenger implements Trip {
    private String name;
    private double weight;

    public Passenger() {
    }

    public Passenger(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getTotalWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Passenger:  " + name + ", weight = " + weight;
    }
}
