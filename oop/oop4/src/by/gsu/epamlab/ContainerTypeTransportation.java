package by.gsu.epamlab;


public class ContainerTypeTransportation extends TareTypeTransportation{
    private final double height;
    private final double length;
    private final double width;

    public ContainerTypeTransportation() {
        this(0, null,0,0,0);
    }

    public ContainerTypeTransportation(double weight, TareCargo tareCargo, double height, double length, double width) {
        super(weight, tareCargo);
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public ContainerTypeTransportation(ContainerTypeTransportation container, TareCargo tareCargo) {
        super(container, tareCargo);
        this.height = container.height;
        this.length = container.length;
        this.width = container.width;

    }


    public double getHeight() {
        return height;
    }


    public double getLength() {
        return length;
    }


    public double getWidth() {
        return width;
    }


    @Override
    public double getVolume() {
        return height * length * width;
    }
}
