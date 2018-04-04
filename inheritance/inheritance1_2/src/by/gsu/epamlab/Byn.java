package by.gsu.epamlab;

public class Byn implements Comparable<Byn> {

    private int value;

    private static int[] tenPowD = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    public Byn(int value) {
        this.value = value;
    }

    public Byn(int rubs, int coins) {
        this(rubs * 100 + coins);
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public int getRubs() {
        return this.value / 100;
    }

    public int getCoins() {
        return this.value % 100;
    }

    public Byn add(Byn byn) {
        this.value += byn.value;
        return this;
    }

    public Byn sub(Byn byn) {
        this.value -= byn.value;
        return this;
    }

    public Byn mul(int k) {
        this.value *= k;
        return this;
    }

    private static int rounding(double roundingValue, Round round, int d) {
        return (int) round.roundFunction(roundingValue / tenPowD[d]) * tenPowD[d];
    }

    public Byn mul(double k, Round round, int d) {
        this.value = rounding(this.value * k, round, d);
        return this;
    }

    public Byn mul(double k, int d) {
        mul(k, Round.ROUND, d);
        return this;
    }

    public Byn mul(double k) {
        mul(k, Round.ROUND, 0);
        return this;
    }

    public Byn mul(double k, Round round) {
        this.value = rounding(this.value * k, round, 0);
        return this;
    }

    public Byn round(Round round, int d) {
        this.value = rounding(this.value, round, d);
        return this;
    }

    public Byn round(Round round) {
        this.value = rounding(value, round, 0);
        return this;
    }

    public Byn round(int d) {
        this.value = rounding(this.value, Round.ROUND, d);
        return this;
    }

    public enum Round {
        CEIL {
            double roundFunction(double d) {
                return Math.ceil(d);
            }
        },
        FLOOR {
            double roundFunction(double d) {
                return Math.floor(d);
            }
        },
        ROUND {
            double roundFunction(double d) {
                return Math.round(d);
            }
        };

        abstract double roundFunction(double d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return this.value == byn.value;
    }

    @Override
    public int compareTo(Byn byn) {
        return this.value - byn.value;
    }

    @Override
    public String toString() {
        return getRubs() + "." + value / 10 % 10 + value % 10;
    }
}
