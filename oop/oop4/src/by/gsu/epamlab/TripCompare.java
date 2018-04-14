package by.gsu.epamlab;
import java.util.Comparator;


public class TripCompare<T> implements Comparator<Trip> {
    @Override
    public int compare(Trip o1, Trip o2) {
        return Trip.ObjectTransportation.valueOf(o1.getClass().getSimpleName().toUpperCase()).ordinal() -
                Trip.ObjectTransportation.valueOf(o2.getClass().getSimpleName().toUpperCase()).ordinal();

    }
}

