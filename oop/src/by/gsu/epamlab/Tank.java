package by.gsu.epamlab;

public class Tank implements Cargo {

    private int massTank;

    private int density;
    private int radius;
    private int height;

    public Tank() {
        this(0, 0, 0, 0);
    }

    public Tank(int massTank, int radius, int height, int density) {
        this.massTank = massTank;
        this.density = density;
        this.radius = radius;
        this.height = height;
    }

    @Override
    public int getMass() {
        return (int) (Math.PI * radius * radius * height * density) + massTank;
    }

    @Override
    public String toString() {
        return massTank + ";" + density + ";" + radius + ";" + height;
    }
}
