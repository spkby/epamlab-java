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

    @Override
    public String toString() {
        return String.format("%d.%02d", value / 100, value % 100);
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

    public Byn mul(double k) {
        this.value = (int) Math.round((double) this.value * k);
        return this;
    }

    public Byn div(double k) {
        return mul(1.0 / k);
    }

    public Byn div(int k) {
        return div((double) k);
    }

    public int getRubs() {
        return value / 100;
    }

    public int getCoins() {
        return value % 100;
    }

    public enum Round {
        TO_UP {
            int round(Byn amount) {
                return roundUp(amount);
            }
        },
        TO_DOWN {
            int round(Byn amount) {
                return roundDown(amount);
            }
        },
        TO_INTEGER {
            int round(Byn amount) {
                return roundInteger(amount);
            }
        };

        abstract int round(Byn amount);
    }

    private static int roundUp(Byn amount) {
        int coins = amount.getCoins() > 0 ? 100 : 0;
        return roundDown(amount) + coins;
    }

    private static int roundDown(Byn amount) {
        return amount.getRubs() * 100;
    }

    private static int roundInteger(Byn amount) {
        int coins = amount.getCoins() >= 50 ? 100 : 0;
        return roundDown(amount) + coins;
    }

    public void round(Round round) {
        this.value = round.round(this);
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
        return value - byn.value;
    }

}
