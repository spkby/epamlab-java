package by.gsu.epamlab.comparators;

import by.gsu.epamlab.entity.Purchase;

import java.lang.reflect.Constructor;
import java.util.Comparator;

public class ComparatorFactory {

    public static Comparator<Purchase> getComparator(String comparatorVersion){

        Comparator<Purchase> comparator = null;
        try {
            Class clazz = Class.forName("by.gsu.epamlab.comparators." + comparatorVersion);
            Constructor constructor = clazz.getConstructor();
            comparator = (Comparator<Purchase>) constructor.newInstance();
        } catch (Exception e) {
            System.err.println("Error creating comparator");
        }
        return comparator;
    }
}
