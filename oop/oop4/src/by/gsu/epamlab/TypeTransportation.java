package by.gsu.epamlab;

public abstract class TypeTransportation implements Trip {
    private final double weight;
    private Cargo cargo;

    public TypeTransportation() {
        this (0, null);
    }

    public TypeTransportation(double weight, Cargo cargo) {
        this.weight = weight;
        this.cargo = cargo;
    }

    public TypeTransportation(TypeTransportation typeTransportation, Cargo cargo) {
        this.weight = typeTransportation.weight;
        this.cargo = cargo;
    }

    public double getWeight() {
        return weight;
    }


    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":   " +
                cargo.getName() + ", " + "total weight = " + getTotalWeight();
    }
}
