package by.gsu.epamlab;

public class Ferry {
    private static final double CARRYING = 300;
    private Trip[] trips;


    public Ferry() {
    }
    public Ferry(Trip[] trips   ) {
        this.trips = trips;
    }
    public static double getCARRYING() {
        return CARRYING;
    }
    public Trip[] getTrips() {
        return trips;
    }
    public void setTrips(Trip[] trips) {
        this.trips = trips;
    }

    public boolean isCarry(){
        double weight = 0;
        for(Trip trip: trips){
            weight += trip.getWeighht();
        }
        if(weight <= CARRYING){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Trip trip: trips){
            result.append(trip).append("\n");
        }
        return result.toString();
    }
}
