package by.gsu.epamlab;

public class TransportExpensesPurchase extends AbstractPurchase {

    private Byn transportExpenses;

    public TransportExpensesPurchase() {
    }

    public TransportExpensesPurchase(Product product, int numberUnits, int transportExpenses) {
        super(product, numberUnits);
        this.transportExpenses = new Byn(transportExpenses);
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + transportExpenses;
    }

    @Override
    protected Byn calcCost(Byn baseCost) {
        return baseCost.add(transportExpenses);
    }
}
