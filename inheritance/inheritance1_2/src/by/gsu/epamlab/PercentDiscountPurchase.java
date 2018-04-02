package by.gsu.epamlab;

import java.util.Scanner;

public class PercentDiscountPurchase extends Purchase {

    private double percentDiscount;
    private static int NUMBER_DISCOUNT = 10;

    public PercentDiscountPurchase() {
    }

    public PercentDiscountPurchase(String productName, int price, int numberUnits, double percentDiscount) {
        super(productName, price, numberUnits);
        this.percentDiscount = percentDiscount;
    }

    public PercentDiscountPurchase(Scanner sc) {
        super(sc);
        this.percentDiscount = sc.nextDouble();
    }

    @Override
    protected String fieldsToString() {
        return ";" + percentDiscount;
    }

    @Override
    public Byn getCost() {
        Byn cost = super.getCost();
        if (NUMBER_DISCOUNT < getNumberUnits()) {
            cost = cost.mul(1.0 - percentDiscount / 100.0);
        }
        return cost;
    }
}
