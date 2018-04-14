package by.gsu.epamlab;

public class Platform implements Transferable {

    private Staff staff;

    public Platform(Staff staff) {
        this.staff = staff;
    }

    @Override
    public double getWeight() {
        return staff.getWeight();
    }

    @Override
    public String toString() {
        return "Platform" + ";" + staff;
    }
}
