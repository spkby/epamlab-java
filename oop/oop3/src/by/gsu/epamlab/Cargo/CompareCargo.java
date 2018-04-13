package by.gsu.epamlab.Cargo;

import java.util.Comparator;

public class CompareCargo implements Comparator<TransportableObject> {

    @Override
    public int compare(TransportableObject o1, TransportableObject o2) {

        CompareTemplate obj1 = CompareTemplate.valueOf(o1.getClass().getSimpleName().toUpperCase());
        CompareTemplate obj2 = CompareTemplate.valueOf(o2.getClass().getSimpleName().toUpperCase());
        return obj1.ordinal() - obj2.ordinal();
    }
}
