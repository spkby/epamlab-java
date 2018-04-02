package by.gsu.epamlab;

public class Byn implements Comparable<Byn> {

    private int value;

    public Byn(int value) {
        this.value = value;
    }

    public Byn(int rubs, int coins) {
        this(rubs * 100 + coins);
    }

    public Byn(Byn value) {
        this(value.getValue());
    }

    @Override
    public String toString() {
        int integer = value / 100;
        int tenths = (value % 100) / 10;
        int hundredths = value % 10;

        return integer + "." + tenths + hundredths;
    }

    public Byn add(Byn byn) {
        this.value += byn.getValue();
        return this;
    }

    public Byn sub(Byn byn) {
        this.value -= byn.getValue();
        return this;
    }

    public Byn mul(int k) {
        this.value *= k;
        return this;
    }

    public Byn mul(double k){
        this.value = (int)Math.round((double)this.value* k);
        return this;
    }

    public Byn div(double k){
        return mul(1.0/k);
    }

    public Byn div(int k){
        return div((double) k);
    }

    public int getRubs() {
        return value / 100;
    }

    public int getCoins() {
        return value % 100;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return value == byn.value;
    }

    @Override
    public int compareTo(Byn byn) {
        return value - byn.getValue();
    }

}
