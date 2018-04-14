package by.gsu.epamlab;

public class Ferry {
    private int carrying;
    private Trip [] trips;

    public Ferry() {
    }

    public Ferry(int carrying, Trip[] trips) {
        this.carrying = carrying;
        this.trips = trips;
    }

    public Trip[] getTrips() {
        return trips;
    }

    public int getCarrying() {
        return carrying;
    }

    public void setCarrying(int carrying) {
        this.carrying = carrying;
    }

    public void setTrips(Trip[] trips) {
        this.trips = trips;
    }
    public boolean isTrip (){
        return sumWeight() < carrying;

    }

    public double sumWeight (){
        double sumWeight = 0;
        for (Trip trip : trips) {
            sumWeight += trip.getTotalWeight();
        }
        return sumWeight;
    }

}
