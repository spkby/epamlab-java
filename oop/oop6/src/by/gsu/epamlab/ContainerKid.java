package by.gsu.epamlab;

public enum ContainerKid {
    FOOT_20(2.350, 5.898, 2.390, 2.2),
    FOOT_40(2.350, 12.035, 2.393, 3.7),
    FOOT_45(2.352, 13.556, 2.695, 4.59);

    private final double width;
    private final double lenght;
    private final double height;
    private final double weight;

    ContainerKid(double width, double lenght, double height, double weight) {
        this.width = width;
        this.lenght = lenght;
        this.height = height;
        this.weight = weight;
    }

    public double getWidth() {
        return width;
    }

    public double getLenght() {
        return lenght;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
    public double getVolume(){
        double volume = width*lenght*height;
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
