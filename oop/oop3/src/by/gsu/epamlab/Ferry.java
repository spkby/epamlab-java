package by.gsu.epamlab;

import by.gsu.epamlab.Cargo.TransportableObject;

public class Ferry {

    private int bearingСapacity;
    private TransportableObject[] transportableObjects;

    public Ferry() {
    }

    public Ferry(int bearingСapacity, TransportableObject[] transportableObjects) {
        this.bearingСapacity = bearingСapacity;
        this.transportableObjects = transportableObjects;
    }

    public int getBearingСapacity() {
        return bearingСapacity;
    }

    public void setBearingСapacity(int bearingСapacity) {
        this.bearingСapacity = bearingСapacity;
    }

    public TransportableObject[] getTransportableObjects() {
        return transportableObjects;
    }

    public void setTransportableObjects(TransportableObject[] transportableObjects) {
        this.transportableObjects = transportableObjects;
    }
}
