package by.gsu.epamlab.comparators;

import by.gsu.epamlab.entity.Purchase;

import java.util.Comparator;

public abstract class AbstractPurchaseComparator implements Comparator<Purchase> {

    protected final int PURCHASE_ID = 1;
    protected final int PRICE_DISCOUNT_PURCHASE_ID = 2;

    @Override
    public int compare(Purchase o1, Purchase o2) {

        int result = o1.getProduct().compareTo(o2.getProduct());

        if (result == 0) {
            result = getId(o1) - getId(o2);
            if (result == 0) {
                result = o1.getCost().compareTo(o2.getCost());
            }
        }

        return result;
    }

    protected abstract int getId(Purchase purchase);
}
