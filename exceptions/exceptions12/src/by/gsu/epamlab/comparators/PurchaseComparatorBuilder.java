package by.gsu.epamlab.comparators;

import by.gsu.epamlab.entity.Purchase;

import java.lang.reflect.Constructor;
import java.util.Comparator;

public class PurchaseComparatorBuilder {

    private static final String PACKAGE = "by.gsu.epamlab.comparators";

    private static Comparator<Purchase> purchaseComparator;

    private PurchaseComparatorBuilder() {
    }

    public static Comparator<Purchase> getPurchaseComparator() {
        return purchaseComparator;
    }

    public static void buildPurchaseComparator(String comparatorVersion) {

        if (purchaseComparator != null) {
            return;
        }

        Comparator<Purchase> comparator = null;

        try {
            Class cls = Class.forName(PACKAGE + "." + comparatorVersion);
            Constructor constructor = cls.getConstructor();
            comparator = (Comparator<Purchase>) constructor.newInstance();
        } catch (Exception e) {
            purchaseComparator = new PurchaseComparatorV1();
        }
    }
}
