package by.gsu.epamlab.exceptions;

import by.gsu.epamlab.Constants;

public class CsvLineException extends Exception {
    private String csvLine;

    public CsvLineException() {
        super();
    }

    public CsvLineException(Exception exception, String csvLine) {
        super(exception);
        this.csvLine = csvLine;
    }

    public CsvLineException(String cause, String csvLine) {
        super(cause);
        this.csvLine = csvLine;
    }

    public String toString() {
        return csvLine + Constants.ERROR_EXCEPTION_DELIMETER + getMessage();
    }
}