package by.gsu.epamlab;


public class TankTypeTransportation extends TareTypeTransportation{
    private final double length;
    private final double diameter;

    public TankTypeTransportation() {
        this(0, null, 0, 0);
    }

    public TankTypeTransportation(double weight, TareCargo tareCargo, double length, double diameter) {
        super(weight, tareCargo);
        this.length = length;
        this.diameter = diameter;
    }

    public TankTypeTransportation(TankTypeTransportation tank, TareCargo tareCargo) {
        super(tank, tareCargo);
        this.length = tank.length;
        this.diameter = tank.diameter;
    }

    public double getLength() {
        return length;
    }

    public double getDiameter() {
        return diameter;
    }

    @Override
    public double getVolume() {
        return Math.PI * length * Math.pow(diameter/2, 2);
    }
}
