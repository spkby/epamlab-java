package by.gsu.epamlab;

public class Cargo extends AbstractCargo implements IMass {
    private final String name;
    private final double mass;

    public Cargo(String name, double mass) {
        this.name = name;
        this.mass = mass;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public int getOrder() {
        return 3;
    }
    public String toString() {
        return name+";"+getMass()+"kg";
    }
}
