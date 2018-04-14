package by.gsu.epamlab;

public abstract class Container implements Transferable {

    private double ownWeight;
    private UniformStaff staff;

    public Container(double ownWeight, UniformStaff staff) {
        this.ownWeight = ownWeight;
        this.staff = staff;
    }

    @Override
    public double getWeight() {
        return ownWeight + staff.getWeight();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ";" + staff + ";" + getWeight();
    }
}
