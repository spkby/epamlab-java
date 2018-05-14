package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;

public class Purchase implements Comparable<Purchase> {

    private String name;
    private Byn price;
    private int number;

    public Purchase(String name, int priceInt, int unitsNumber) {
        this.name = name;
        this.number = unitsNumber;
        this.price = new Byn(priceInt);
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Byn getCost() {
        Byn cost = new Byn(price);
        return cost.mul(number);
    }

    protected String fieldsToString() {
        return name + Constants.DELIMITER + price + Constants.DELIMITER + number;
    }

    @Override
    public String toString() {
        return fieldsToString() + Constants.DELIMITER + getCost();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        return 31 * result + price.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;

        return name.equals(purchase.name) &&
                price.equals(purchase.price);
    }

    public int compareTo(Purchase purchase) {
        return purchase.getCost().compareTo(getCost());
    }


}
