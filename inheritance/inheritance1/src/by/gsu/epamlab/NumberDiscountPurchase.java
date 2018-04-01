package by.gsu.epamlab;

import java.util.Scanner;

public class NumberDiscountPurchase extends Purchase {

    private double percentDiscount;
    private static int NUMBER_DISCOUNT = 10;

    public NumberDiscountPurchase() {}

    public NumberDiscountPurchase(String productName, int price, int numberUnits, double percentDiscount) {
        super(productName,price,numberUnits);
        this.percentDiscount = percentDiscount;
    }

    public NumberDiscountPurchase(Scanner sc) {
        super(sc);
        this.percentDiscount = sc.nextDouble();
    }

    @Override
    protected String fieldsToString() {
        return ";" + percentDiscount;
    }

    @Override
    public Byn getCost() {
        if (NUMBER_DISCOUNT < numberUnits) {
            return new Byn((int) Math.round(price.getAmount() *
                    numberUnits * (1.0 - percentDiscount / 100.0)));
        } else {
            return super.getCost();
        }
    }
}
