package by.gsu.epamlab;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {

    private final Product product;
    private int numberUnits;

    public AbstractPurchase() {
        this(null,0);
    }

    public AbstractPurchase(Product product, int numberUnits) {
        this.product = product;
        this.numberUnits = numberUnits;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumberUnits() {
        return numberUnits;
    }

    public void setNumberUnits(int numberUnits) {
        this.numberUnits = numberUnits;
    }

    protected abstract Byn calcCost(Byn baseCost);

    public Byn getCost() {
        return calcCost(product.getPrice().mul(numberUnits)).round(Byn.Round.FLOOR, 2);
    }

    protected String fieldsToString() {
        return product + ";" + numberUnits;
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    public int compareTo(AbstractPurchase purchase){
        return purchase.getCost().compareTo(getCost());
    }

}


