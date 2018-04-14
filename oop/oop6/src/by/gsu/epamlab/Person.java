package by.gsu.epamlab;

public class Person implements Trip {
    private String name;
    private double weight;
    private final int ID = 1;

    public Person(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
    public Person() {
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }


    @Override
    public double getWeighht() {
        return weight;
    }
    @Override
    public String toString() {
        return name +";"+ weight;
    }
}
