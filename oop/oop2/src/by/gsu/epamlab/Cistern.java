package by.gsu.epamlab;

public class Cistern extends AbstractCargo implements IMass{
    public final static double MASS_CISTERN = 675;
    public final static double RADIUS = 45;
    public final static int HEIGHT = 68;
    public Cistern(Fluid material) {
        super(material);
    }

    @Override
    public double getMass() {
        return MASS_CISTERN+getMaterial().getDensity()*HEIGHT*RADIUS;
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
