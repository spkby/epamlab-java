package by.gsu.epamlab.entity;

import by.gsu.epamlab.exceptions.NonpositiveArgumentException;

public class PriceDiscountPurchase extends Purchase {

    private Byn priceDiscount;

    public PriceDiscountPurchase(String productName, int price, int numberUnits, int priceDiscount) {
        super(productName, price, numberUnits);
        setPriceDiscount(priceDiscount);
    }

    private void setPriceDiscount(int priceDiscount) {
        setDiscount(priceDiscount);
    }

    private void setDiscount(int priceDiscount) {
        if (priceDiscount <= 0) {
            throw new NonpositiveArgumentException(priceDiscount, "discount");
        }
        this.priceDiscount = new Byn(priceDiscount);
    }

    public Byn getPriceDiscount() {
        return priceDiscount;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + priceDiscount;
    }

    @Override
    protected String getDiscount() {
        return getPriceDiscount().toString();
    }

    @Override
    public Byn getCost() {
        Byn cost = new Byn(getPrice());
        return cost.sub(priceDiscount).mul(getNumber());
    }
}
