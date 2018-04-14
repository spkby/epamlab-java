package by.gsu.epamlab;


public abstract class TareTypeTransportation extends TypeTransportation{
    public TareTypeTransportation() {
    }

    public TareTypeTransportation(double weight, TareCargo tareCargo) {
        super(weight, tareCargo);
    }

    public TareTypeTransportation(TareTypeTransportation tare, TareCargo tareCargo) {
        super(tare, tareCargo);
    }
    public abstract double getVolume();

    @Override
    public double getTotalWeight() {
        TareCargo tareCargo = (TareCargo)getCargo();
        return getWeight() + tareCargo.getDensity() * getVolume();
    }
}
