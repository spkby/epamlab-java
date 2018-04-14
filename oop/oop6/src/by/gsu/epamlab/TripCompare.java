package by.gsu.epamlab;

import java.lang.reflect.Field;
import java.util.Comparator;

public class TripCompare implements Comparator<Trip> {


    @Override
    public int compare(Trip trip1, Trip trip2) {
        return trip1.getID() - trip2.getID();
    }
}
