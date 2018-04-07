package by.gsu.epamlab;

public class Byn implements Comparable<Byn> {

    private final int value;

    public Byn(int value) {
        this.value = value;
    }

    public Byn(int rubs, int coins) {
        this(rubs * 100 + coins);
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
        return new Byn(this.value + byn.value);
    }

    public Byn sub(Byn byn) {
        return new Byn(this.value - byn.value);
    }

    public Byn mul(int k) {
        return new Byn(this.value * k);
    }

    public Byn mul(double k, Round round, int digits) {
        return new Byn(round.rounding(this.value * k, digits));
    }

    public Byn mul(double k, int digits) {
        return mul(k, Round.ROUND, digits);
    }

    public Byn mul(double k) {
        return mul(k, Round.ROUND, 0);
    }

    public Byn mul(double k, Round round) {
        return mul(k, round, 0);
    }

    public Byn round(Round round) {
        return round(round,0);
    }

    public Byn round(int digits) {
        return round(Round.ROUND,digits);
    }

    public Byn round() {
        return round(Round.ROUND,0);
    }

    public Byn round(Round round, int digits) {
        return new Byn(round.rounding(this.value, digits));
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

        private int rounding(double roundingValue, int digits) {
            int[] tenPowD = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
            return (int) roundFunction(roundingValue / tenPowD[digits]) * tenPowD[digits];
        }
    }
}
