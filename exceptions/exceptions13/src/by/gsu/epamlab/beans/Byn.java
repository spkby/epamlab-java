package by.gsu.epamlab.beans;

import by.gsu.epamlab.enums.Fields;
import by.gsu.epamlab.exceptions.NegativeArgumentException;

public class Byn implements Comparable<Byn> {

    private int value;

    public Byn() {
        this(0);
    }

    public Byn(int value) {
        if (value < 0) {
            throw new NegativeArgumentException(value, Fields.BYN);
        }
        this.value = value;
    }

    public Byn(int rubs, int coins) {
        if (rubs < 0) {
            throw new NegativeArgumentException(rubs, Fields.BYN);
        }
        if (coins < 0 || coins > 99) {
            throw new NegativeArgumentException(coins, Fields.BYN);
        }
        this.value = rubs * 100 + coins;
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
        if (value < byn.value) {
            throw new NegativeArgumentException(value - byn.value, Fields.BYN);
        }
        this.value -= byn.value;
        return this;
    }

    public Byn mul(int k) {
        if (k < 0) {
            throw new NegativeArgumentException(k, Fields.KOEF);
        }
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
        round(round, 0);
        return this;
    }

    public Byn round(int digits) {
        round(Round.ROUND, digits);
        return this;
    }

    public Byn round(Round round, int digits) {
        this.value = round.rounding(this.value, digits);
        return this;
    }

    public enum Round {
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
