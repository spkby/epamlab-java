package by.gsu.epamlab;

public class ContainerCargo implements Trip {
    private static final String NAME = "Container";
    SolidContent solid;
    ContainerKid containerKid;
    private final int ID = 2;

    public ContainerCargo() {
    }
    public ContainerCargo(SolidContent solid, ContainerKid containerKid) {
        this.solid = solid;
        this.containerKid = containerKid;
    }

    public int getID() {
        return ID;
    }

    @Override
    public double getWeighht() {
        double cargoWeight = solid.getDensity()*containerKid.getVolume();
        double weight = cargoWeight + containerKid.getWeight();
        return weight;
    }
    @Override
    public String toString() {
        return NAME +";"+ solid +";"+ getWeighht();
    }
}
