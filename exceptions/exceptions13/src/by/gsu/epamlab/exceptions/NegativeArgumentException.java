package by.gsu.epamlab.exceptions;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.enums.Fields;

public class NegativeArgumentException extends NonpositiveArgumentException {


    public NegativeArgumentException() {
    }

    public NegativeArgumentException(int value, Fields field) {
        super(value, field);
    }

    public String getHead() {
        return Constants.ERROR_EXCEPTION_NEGATIVE_HEAD;
    }

}
