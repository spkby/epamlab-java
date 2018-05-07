package by.gsu.epamlab.entity;

import by.gsu.epamlab.exceptions.Fields;
import by.gsu.epamlab.exceptions.NonpositiveArgumentException;

import java.util.Formatter;

public class Purchase implements Comparable<Purchase> {

    private static final String DASH = "-";
    private static final String FORMATTER = "%-6s %5s %6s %8s %5s";
    private String product;
    private Byn price;
    private int number;

    public Purchase(String product, int price, int number) {
        this.product = product;
        setPrice(price);
        setNumber(number);
    }

    private void setNumber(int number) {
        if (number <= 0) {
            throw new NonpositiveArgumentException(number, Fields.NUMBER);
        }
        this.number = number;
    }

    private void setPrice(int price) {
        if (price <= 0) {
            throw new NonpositiveArgumentException(price, Fields.PRICE);
        }
        this.price = new Byn(price);
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

    protected String getDiscount() {
        return DASH;
    }


    public String print() {
        Formatter formatter = new Formatter();
        String format = TableRows.NAME.get() +
                TableRows.PRICE.get() +
                TableRows.NUMBER.get() +
                TableRows.DISCOUNT.get() +
                TableRows.COST.get();
        formatter.format(format, getProduct(), getPrice(), getNumber(), getDiscount(), getCost());
        return formatter.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;

        return product.equals(purchase.product) &&
                number == purchase.number &&
                price.equals(purchase.price);
    }

    public int compareTo(Purchase purchase) {
        return purchase.getCost().compareTo(getCost());
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }
}
