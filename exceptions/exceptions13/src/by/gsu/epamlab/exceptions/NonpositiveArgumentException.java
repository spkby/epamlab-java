package by.gsu.epamlab.exceptions;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.enums.Fields;

public class NonpositiveArgumentException extends IllegalArgumentException {


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

    public NonpositiveArgumentException() {
        super();
    }

    public NonpositiveArgumentException(int nonpositiveValue, Fields field) {
        value = nonpositiveValue;
        this.field = field;
    }

    public NonpositiveArgumentException(Fields field) {
        this.field = field;
    }

    public String getHead() {
        return Constants.ERROR_EXCEPTION_NONPOSITIVE_HEAD;
    }

    @Override
    public String toString() {
        return getHead() + value
                + Constants.ERROR_EXCEPTION_NONPOSITIVE_BODY + field
                + Constants.ERROR_EXCEPTION_NONPOSITIVE_FOOT;
    }
}
