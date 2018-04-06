package by.gsu.epamlab;

public class Byn implements Comparable<Byn> {

    private int value;

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

    public Byn mul(double k, Round round, int digits) {
        this.value = round.rounding(this.value * k, digits);
        return this;
    }

    public Byn mul(double k, int digits) {
        mul(k, Round.ROUND, digits);
        return this;
    }

    public Byn mul(double k) {
        mul(k, Round.ROUND, 0);
        return this;
    }

    public Byn mul(double k, Round round) {
        mul(k, round, 0);
        return this;
    }

    public Byn round(Round round) {
        round(round,0);
        return this;
    }

    public Byn round(int digits) {
        round(Round.ROUND,digits);
        return this;
    }

    public Byn round() {
        round(Round.ROUND,0);
        return this;
    }

    public Byn round(Round round, int digits) {
        this.value = round.rounding(this.value, digits);
        return this;
    }

    public static enum Round {
        CEIL {
            double roundFunction(double roundingValue) {
                return Math.ceil(roundingValue);
            }
        },
        FLOOR {
            double roundFunction(double roundingValue) {
                return Math.floor(roundingValue);
            }
        },
        ROUND {
            double roundFunction(double roundingValue) {
                return Math.round(roundingValue);
            }
        };

        abstract double roundFunction(double roundingValue);

        private int[] tenPowD = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

        private int rounding(double roundingValue, int digits) {
            return (int) roundFunction(roundingValue / tenPowD[digits]) * tenPowD[digits];
        }
    }
}
