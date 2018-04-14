package by.gsu.epamlab;

public class CisternCargo implements Trip{
    private static final String NAME = "Cistern";
    LiquidContent liquid;
    CisternKid cisternKid;
    private final int ID = 4;

    public CisternCargo() {
    }
    public CisternCargo(LiquidContent liquid, CisternKid cisternKid) {
        this.liquid = liquid;
        this.cisternKid = cisternKid;
    }

    public int getID() {
        return ID;
    }

    @Override
    public double getWeighht() {
        double weight = cisternKid.getVolume()*liquid.getDensity();
        return weight;
    }

    @Override
    public String toString() {
        return NAME +";"+ liquid +";"+ getWeighht();
    }
}
