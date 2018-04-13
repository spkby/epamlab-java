package by.gsu.epamlab;

public class ContainerCargo extends AbstractCargo {

    final private Material material;
    final private int width;
    final private int height;
    final private int length;

    public ContainerCargo() {
        this(0, 0, 0, 0, null);
    }

    public ContainerCargo(int massContainer, int width, int height, int length, Material material) {
        super(massContainer);
        this.width = width;
        this.height = height;
        this.length = length;
        this.material = material;
    }

    @Override
    protected int calcWeight() {
        return width * height * length * material.getDesity();
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + material + ";" + width + ";" + height + ";" + length;
    }
}
