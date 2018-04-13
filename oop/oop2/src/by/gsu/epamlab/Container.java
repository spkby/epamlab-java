package by.gsu.epamlab;

public class Container extends AbstractCargo implements IMass {
    public final static double MASS_CONTAINER= 55;
    public final static int LENGTH = 2;
    public final static int WIDTH=11;
    public final static int HEIGHT= 16;

    public Container(SolidMaterial material){
        super(material);
    }

    @Override
    public double getMass() {
        return MASS_CONTAINER + getMaterial().getDensity()*LENGTH*WIDTH*HEIGHT;
    }

    @Override
    public int getOrder() {
        return 2;
    }

}
