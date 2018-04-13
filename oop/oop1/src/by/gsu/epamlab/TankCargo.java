package by.gsu.epamlab;

public class TankCargo extends AbstractCargo {

    final private Material material;
    final private int radius;
    final private int height;

    public TankCargo() {
        this(0, 0, 0, null);
    }

    public TankCargo(int massTank, int radius, int height, Material material) {
        super(massTank);
        this.radius = radius;
        this.height = height;
        this.material = material;
    }

    @Override
    protected int calcWeight() {
        return (int) (Math.PI * radius * radius * height * material.getDesity());
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + material + ";" + radius + ";" + height;
    }
}
