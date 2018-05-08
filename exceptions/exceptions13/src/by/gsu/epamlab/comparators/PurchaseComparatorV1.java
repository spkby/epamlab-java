package by.gsu.epamlab.comparators;

import by.gsu.epamlab.beans.PriceDiscountPurchase;
import by.gsu.epamlab.beans.Purchase;

public class PurchaseComparatorV1 extends AbstractPurchaseComparator {

    @Override
    protected boolean isSubPurchase(Purchase p) {
        return p instanceof PriceDiscountPurchase;
    }
}
