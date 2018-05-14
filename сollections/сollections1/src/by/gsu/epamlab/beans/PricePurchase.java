package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;

public class PricePurchase extends Purchase {

    private Byn discount;

    public PricePurchase(String productName, int price, int numberUnits, int priceDiscount) {
        super(productName, price, numberUnits);
        this.discount = new Byn(priceDiscount);
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + Constants.DELIMITER + discount;
    }

    @Override
    public Byn getCost() {
        return new Byn(getPrice()).sub(discount).mul(getNumber());
    }
}
