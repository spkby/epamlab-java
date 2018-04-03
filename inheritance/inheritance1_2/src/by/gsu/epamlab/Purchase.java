package by.gsu.epamlab;

import java.util.Scanner;

public class Purchase {

    private String productName;
    private Byn price;
    private int numberUnits;

    public Purchase() {
    }

    public Purchase(String productName, int price, int numberUnits) {
        this.productName = productName;
        this.price = new Byn(price);
        this.numberUnits = numberUnits;
    }

    public Purchase(Scanner sc) {
        this(sc.next(), sc.nextInt(), sc.nextInt());
    }

    public Byn getCost() {
        Byn cost = new Byn(price);
        return cost.mul(numberUnits);
    }

    protected String fieldsToString() {
        return "";
    }

    @Override
    public String toString() {
        return productName + ";" + price + ";" + numberUnits + fieldsToString() + ";" + getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;

        return productName.equals(purchase.productName) &&
                price.equals(purchase.price);
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumberUnits() {
        return numberUnits;
    }
}
