package by.gsu.epamlab.Cargo;

public abstract class CargoInanimate implements TransportableObject {

    public String fieldsToString(){
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return fieldsToString() + ";"
                + getMass();
    }
}
