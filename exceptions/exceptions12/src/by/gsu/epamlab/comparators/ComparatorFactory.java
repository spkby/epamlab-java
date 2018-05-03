package by.gsu.epamlab.comparators;

import by.gsu.epamlab.entity.Purchase;

import java.lang.reflect.Constructor;
import java.util.Comparator;

public class ComparatorFactory {

    private static final String PACKAGE = "by.gsu.epamlab.comparators";

    public static Comparator<Purchase> getComparator(String comparatorVersion){

        Comparator<Purchase> comparator = null;
        try {
            Class cls = Class.forName(PACKAGE + "." + comparatorVersion);
            Constructor constructor = cls.getConstructor();
            comparator = (Comparator<Purchase>) constructor.newInstance();
        } catch (Exception e) {
            System.err.println("Error creating comparator");
        }
        return comparator;
    }
}
