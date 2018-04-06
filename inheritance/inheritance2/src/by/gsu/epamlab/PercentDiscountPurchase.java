package by.gsu.epamlab;

public class PercentDiscountPurchase extends AbstractPurchase {

    private double percentDiscount;
    private static int NUMBER_DISCOUNT = 10;

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
    protected Byn calcCost() {
        Byn cost = super.calcCost();
        if (NUMBER_DISCOUNT < getNumberUnits()) {
            cost = cost.mul(1.0 - percentDiscount / 100.0);
        }
        return cost;
    }
}
