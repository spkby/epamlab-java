package by.gsu.epamlab;

public class Material {

    private final String name;
    private final int desity;

    public Material(String name, int desity) {
        this.name = name;
        this.desity = desity;
    }

    public String getName() {
        return name;
    }

    public int getDesity() {
        return desity;
    }

    @Override
    public String toString() {
        return name + ";" + desity;
    }
}
