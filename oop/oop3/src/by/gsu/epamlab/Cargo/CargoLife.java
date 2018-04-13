package by.gsu.epamlab.Cargo;

public abstract class CargoLife implements TransportableObject {

    public String fieldsToString(){
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return fieldsToString() + ";"
                + getMass();
    }
}
