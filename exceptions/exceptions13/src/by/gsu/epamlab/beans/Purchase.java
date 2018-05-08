package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.enums.Fields;
import by.gsu.epamlab.enums.TableRows;
import by.gsu.epamlab.exceptions.NonpositiveArgumentException;

import java.util.Formatter;

public class Purchase implements Comparable<Purchase> {

    private String name;
    private Byn price;
    private int number;

    public Purchase() {
        throw new IllegalArgumentException(Constants.ERROR_NULL_NAME);
    }

    public Purchase(String name, Byn price, int unitsNumber) {
        setNameAndNumber(name, unitsNumber);
        setPrice(price);
    }

    public Purchase(String name, int priceInt, int unitsNumber) {
        setNameAndNumber(name, unitsNumber);
        setPrice(priceInt);
    }

    private void setNameAndNumber(String name, int unitsNumber) {
        setName(name);
        setNumber(unitsNumber);
    }

    public void setNumber(int unitsNumber) {
        checkPositive(unitsNumber, Fields.NUMBER);
        this.number = unitsNumber;
    }

    public void setPrice(Byn price) {
        if (price.equals(new Byn())) {
            throw new NonpositiveArgumentException(0, Fields.PRICE);
        }
        this.price = price;
    }

    public void setPrice(int priceInt) {
        checkPositive(priceInt, Fields.PRICE);
        setPrice(new Byn(priceInt));
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(Constants.ERROR_NULL_NAME);
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_EMPTY_NAME);
        }
        this.name = name;
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

    protected void checkPositive(int value, Fields field) {
        if (value <= 0) {
            throw new NonpositiveArgumentException(value, field);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;

        return name.equals(purchase.name) &&
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
