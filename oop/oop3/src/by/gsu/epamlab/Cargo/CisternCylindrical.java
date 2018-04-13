package by.gsu.epamlab.Cargo;

import by.gsu.epamlab.Container.Сistern;

public class CisternCylindrical extends CargoInanimate {

    private final int density;
    private final Сistern сistern;

    public CisternCylindrical() {
        this(0, null);
    }

    public CisternCylindrical(int density, Сistern сistern) {
        this.density = density;
        this.сistern = сistern;
    }

    @Override
    public int getMass() {
        return сistern.getСisternMass() + (сistern.getValue() * density);
    }

    @Override
    public String fieldsToString() {
        return super.fieldsToString()  + ";"
                + density + ";"
                + сistern;
    }
}
