package by.gsu.epamlab;

public class Container implements Cargo {

    private int massContainer;

    private int density;
    private int width;
    private int height;
    private int length;

    public Container() {
        this(0, 0, 0, 0, 0);
    }

    public Container(int massContainer, int width, int height, int length, int density) {
        this.massContainer = massContainer;
        this.density = density;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    @Override
    public int getMass() {
        return width * height * length * density + massContainer;
    }

    @Override
    public String toString() {
        return massContainer + ";" + density + ";" + width + ";" + height + ";" + length;
    }
}
