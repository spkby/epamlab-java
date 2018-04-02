package by.gsu.epamlab;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {

    private Byn priceDiscount;

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(String productName, int price, int numberUnits, int priceDiscount) {
        super(productName, price, numberUnits);
        this.priceDiscount = new Byn(priceDiscount);
    }

    public PriceDiscountPurchase(Scanner sc) {
        super(sc);
        this.priceDiscount = new Byn(sc.nextInt());
    }

    @Override
    protected String fieldsToString() {
        return ";" + priceDiscount;
    }

    @Override
    public Byn getCost() {
        Byn cost = new Byn(getPrice());
        return cost.sub(priceDiscount).mul(getNumberUnits());
    }
}
