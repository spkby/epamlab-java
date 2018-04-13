package by.gsu.epamlab.Cargo;

public class Platform extends CargoInanimate{

    private final int cargoMass;

    public Platform() {
        this(0);
    }

    public Platform(int cargoMass) {
        this.cargoMass = cargoMass;
    }

    @Override
    public int getMass() {
        return cargoMass;
    }

    @Override
    public String fieldsToString() {
        return super.fieldsToString() + ";"
                + cargoMass;
    }
}
