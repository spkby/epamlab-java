package by.gsu.epamlab;

public class PlatformCargo extends AbstractCargo {

    private static final int MASS_PLATFORM = 0;

    private int weightCargo;

    public PlatformCargo(int weightCargo) {
        super(MASS_PLATFORM);
        this.weightCargo = weightCargo;
    }

    @Override
    protected int calcWeight() {
        return weightCargo;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + weightCargo;
    }
}
