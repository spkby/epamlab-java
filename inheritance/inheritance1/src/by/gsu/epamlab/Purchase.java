package by.gsu.epamlab;

import java.util.Scanner;

public class Purchase {

    protected String productName;
    protected Byn price;
    protected int numberUnits;

    public Purchase() {
    }

    public Purchase(String productName, int price, int numberUnits) {
        this.productName = productName;
        this.price = new Byn(price);
        this.numberUnits = numberUnits;
    }

    public Purchase(Scanner sc) {
        this.productName = sc.next();
        this.price = new Byn(sc.nextInt());
        this.numberUnits = sc.nextInt();
    }

    public Byn getCost() {
        return new Byn(price.getAmount() * numberUnits);
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
                price.getAmount()== purchase.price.getAmount();
    }

}
