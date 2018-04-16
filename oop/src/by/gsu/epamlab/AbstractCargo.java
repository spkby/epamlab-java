package by.gsu.epamlab;

abstract class AbstractCargo implements Weight {

    final private int mass;

    public AbstractCargo(int mass) {
        this.mass = mass;
    }

    protected abstract int calcWeight();

    protected String fieldsToString() {
        return mass + "";
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + calcWeight();
    }

    @Override
    public int getWeight() {
        return calcWeight() + mass;
    }

}
