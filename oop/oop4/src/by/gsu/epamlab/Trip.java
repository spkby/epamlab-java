package by.gsu.epamlab;


public interface Trip {
    public static enum ObjectTransportation {
        PASSENGER,CONTAINERTYPETRANSPORTATION, PLATFORMTYPETRANSPORTATION, TANKTYPETRANSPORTATION
    }

    double getTotalWeight();

}

