package by.gsu.epamlab;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {

    private Product product;
    private int numberUnits;

    public AbstractPurchase() {
    }

    public AbstractPurchase(Product product, int numberUnits) {
        this.product = product;
        this.numberUnits = numberUnits;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumberUnits() {
        return numberUnits;
    }

    public void setNumberUnits(int numberUnits) {
        this.numberUnits = numberUnits;
    }

    protected Byn calcCost(){
        Byn cost = new Byn(product.getPrice());
        return cost.mul(numberUnits);
    }

    public Byn getCost() {
        return calcCost().round(Byn.Round.FLOOR, 2);
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
