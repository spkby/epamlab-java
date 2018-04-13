package by.gsu.epamlab.Cargo;

public class People extends CargoLife {

    private final int peopleMass;
    private final String name;

    public People() {
        this(0, null);
    }

    public People(int peopleMass, String name) {
        this.peopleMass = peopleMass;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getMass() {
        return peopleMass;
    }

    @Override
    public String fieldsToString() {
        return super.fieldsToString() + ";"
                + name;
    }
}
