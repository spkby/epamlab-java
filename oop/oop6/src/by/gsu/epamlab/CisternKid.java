package by.gsu.epamlab;

public enum CisternKid {
    MODEL_15_144(1.5, 10.818, 27.5),
    MODEL_15_811(1.5, 20.226, 48.8),
    MODEL_15_156(1.5, 15.421, 30.2);

    private final double radius;
    private final double lenght;
    private final double weight;

    CisternKid(double radius, double lenght, double weight) {
        this.radius = radius;
        this.lenght = lenght;
        this.weight = weight;
    }

    public double getRadius() {
        return radius;
    }

    public double getLenght() {
        return lenght;
    }

    public double getWeight() {
        return weight;
    }

    public double getVolume(){
        double volume = Math.PI*radius*radius*lenght;
        return volume;
    }

    public String getName(){
        return this.name();
    }

    @Override
    public String toString() {
        return getName();
    }
}
