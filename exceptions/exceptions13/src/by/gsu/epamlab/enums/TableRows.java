package by.gsu.epamlab.enums;

public enum TableRows {

    NAME("%-10s"), PRICE("%7s"), NUMBER("%7s"), DISCOUNT("%9s"), COST("%8s");

    private final String value;

    TableRows(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    public String toString() {
        return name().substring(0, 1) + name().toLowerCase().substring(1);
    }
}
