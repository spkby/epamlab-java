package by.gsu.epamlab;

public class PercentDiscountPurchase extends AbstractPurchase {

    private double percentDiscount;
    private final static int NUMBER_DISCOUNT = 10;

    public PercentDiscountPurchase() {
    }

    public PercentDiscountPurchase(Product productName, int numberUnits, double percentDiscount) {
        super(productName, numberUnits);
        this.percentDiscount = percentDiscount;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + percentDiscount;
    }

    @Override
    protected Byn calcCost(Byn baseCost) {
        if (NUMBER_DISCOUNT < getNumberUnits()) {
            baseCost.mul(1.0 - percentDiscount / 100.0);
        }
        return baseCost;
    }
}
