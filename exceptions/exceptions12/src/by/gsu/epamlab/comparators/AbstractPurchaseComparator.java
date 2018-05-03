package by.gsu.epamlab.comparators;

import by.gsu.epamlab.entity.Purchase;

import java.util.Comparator;

public abstract class AbstractPurchaseComparator implements Comparator<Purchase> {

    protected final int PURCHASE_ID = 1;
    protected final int PRICE_DISCOUNT_PURCHASE_ID = 2;

    @Override
    public int compare(Purchase o1, Purchase o2) {
        int result;
        if (o2.getProduct().equals(o1.getProduct())) {
            result = getId(o2) - getId(o1);
        } else {
            result = o1.getProduct().compareTo(o2.getProduct());
        }
        if (result == 0) {
            result = o1.getCost().compareTo(o2.getCost());
        }
        return result;
    }

    protected abstract int getId(Purchase purchase);
}

   /* public int compare(Purchase o1, Purchase o2) {
        if (o2.getName().equals(o1.getName())) {
            if (getId(o1) != getId(o2)) {
                return getId(o1) - getId(o2);
            } else {
                return o1.getCost().getRubs() + o1.getCost().getCoins() - o2.getCost().getRubs() + o2.getCost().getCoins();
            }
        } else {

            return o1.getName().compareTo(o2.getName());
        }
    }*/
