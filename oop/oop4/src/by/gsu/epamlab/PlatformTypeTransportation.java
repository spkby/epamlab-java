package by.gsu.epamlab;


public class PlatformTypeTransportation extends TypeTransportation{

    public PlatformTypeTransportation() {
    }

    public PlatformTypeTransportation(PieceCargo pieceCargo) {
        super(0, pieceCargo);
    }

    @Override
    public double getTotalWeight() {
        PieceCargo pieceCargo = (PieceCargo)getCargo();
        return pieceCargo.getWeight();
    }


}
