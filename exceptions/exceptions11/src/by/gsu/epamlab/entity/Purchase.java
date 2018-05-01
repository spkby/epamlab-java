package by.gsu.epamlab.entity;

import java.util.Formatter;

public class Purchase {

    public static final String HYPHEN = "-";
    private String product;
    private Byn price;
    private int number;

    public Purchase(String product, int price, int number) {
        this.product = product;
        this.price = new Byn(price);
        this.number = number;
    }

    public String getProduct() {
        return product;
    }

    public Byn getCost() {
        Byn cost = new Byn(price);
        return cost.mul(number);
    }

    protected String fieldsToString() {
        return product + ";" + price + ";" + number;
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    protected String getDiscount(){
        return HYPHEN;
    }

    public String print() {
        Formatter formatter = new Formatter();
        formatter.format("%-6s %5s %6s %8s %5s", getProduct(), getPrice(), getNumber(), getDiscount(), getCost());
        return formatter.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;

        return product.equals(purchase.product) &&
                price.equals(purchase.price);
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }
}
