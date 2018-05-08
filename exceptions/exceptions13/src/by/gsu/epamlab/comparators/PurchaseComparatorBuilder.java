package by.gsu.epamlab.comparators;

import by.gsu.epamlab.beans.Purchase;

import java.util.Comparator;

public class PurchaseComparatorBuilder {

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

        final String FULL_COMPARATOR_NAME =
                PurchaseComparatorBuilder.class.getPackage().getName() + "." + comparatorVersion;

        try {
            Class<AbstractPurchaseComparator> compClass;
            compClass = (Class<AbstractPurchaseComparator>) Class.forName(FULL_COMPARATOR_NAME);
            purchaseComparator = compClass.newInstance();
        } catch (Exception e) {
            //use default class independently on exception
            purchaseComparator = new PurchaseComparatorV1();
        }
    }
}
