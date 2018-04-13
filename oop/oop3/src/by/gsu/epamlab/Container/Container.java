package by.gsu.epamlab.Container;

public class Container {

    private final int length;
    private final int width;
    private final int height;
    private final int containerMass;

    public Container() {
        this(0, 0, 0, 0);
    }

    public Container(int length, int width, int height, int containerMass) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.containerMass = containerMass;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getContainerMass() {
        return containerMass;
    }

    public int getValue(){
        return height * length * width;
    }

    @Override
    public String toString() {
        return length + ";"
                + width + ";"
                + height + ";"
                + containerMass;
    }
}
