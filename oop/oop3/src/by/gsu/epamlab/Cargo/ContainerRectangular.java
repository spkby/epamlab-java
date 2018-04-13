package by.gsu.epamlab.Cargo;

import by.gsu.epamlab.Container.Container;

public class ContainerRectangular extends CargoInanimate {

    private final int density;
    private final Container container;

    public ContainerRectangular() {
        this(0, null);
    }

    public ContainerRectangular(int density, Container container) {
        this.density = density;
        this.container = container;
    }

    @Override
    public int getMass() {
        return container.getContainerMass() + (container.getValue() * density);
    }

    @Override
    public String fieldsToString() {
        return super.fieldsToString()  + ";"
                + density + ";"
                + container;
    }
}
