package by.gsu.epamlab;

import java.util.Scanner;

public class PriceDiscountPurchase extends AbstractPurchase {

    private Byn priceDiscount;

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(Product product, int numberUnits, int priceDiscount) {
        super(product, numberUnits);
        this.priceDiscount = new Byn(priceDiscount);
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + priceDiscount;
    }

    @Override
    protected Byn calcCost() {
        Byn cost = new Byn(getProduct().getPrice());
        return cost.sub(priceDiscount).mul(getNumberUnits());
    }
}
