package by.gsu.epamlab.comparators;

import by.gsu.epamlab.beans.Purchase;

import java.util.Comparator;

public abstract class AbstractPurchaseComparator implements Comparator<Purchase> {

    private enum Sign {EQUAL, MORE}

    private int getRank(Purchase p) {
        return isSubPurchase(p) ? Sign.MORE.ordinal() : Sign.EQUAL.ordinal();
    }

    @Override
    public int compare(Purchase o1, Purchase o2) {

        int result = o1.getName().compareTo(o2.getName());

        if (result == 0) {
            result = getRank(o1) - getRank(o2);
            if (result == 0) {
                result = o1.getCost().compareTo(o2.getCost());
            }
        }
        return result;
    }

    protected abstract boolean isSubPurchase(Purchase purchase);
}
