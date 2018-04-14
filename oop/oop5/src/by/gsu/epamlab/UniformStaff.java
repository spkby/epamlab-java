package by.gsu.epamlab;

public class UniformStaff extends Staff {

    public UniformStaff(String title, double density, double volume) {
        super(title, density * volume);
    }
}
