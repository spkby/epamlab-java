package by.gsu.epamlab.exceptions;

public class NonpositiveArgumentException extends IllegalArgumentException {

    public static String MESSAGE = "Non positive value";

    private int value;
    private Fields field;

    public int getValue() {
        return value;
    }

    public Fields getField() {
        return field;
    }

    public String getFieldName() {
        return field.name().toLowerCase();
    }

    public NonpositiveArgumentException(int nonpositiveValue, Fields field) {
        value = nonpositiveValue;
        this.field = field;
    }

    public NonpositiveArgumentException(Fields field) {
        this.field = field;
    }
}
