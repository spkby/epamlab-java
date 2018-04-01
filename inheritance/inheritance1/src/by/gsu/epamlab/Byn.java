package by.gsu.epamlab;

public class Byn {

    private int amount;

    public Byn(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        int integer = amount / 100;
        int tenths = (amount % 100) / 10;
        int hundredths = amount % 10;

        return integer + "." + tenths + hundredths;
    }

    public int getAmount() {
        return amount;
    }
}
