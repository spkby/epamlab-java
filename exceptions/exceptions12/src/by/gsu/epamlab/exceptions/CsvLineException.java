package by.gsu.epamlab.exceptions;

public class CsvLineException extends Throwable {

    private static final String HYPHEN = "\t->\t";

    private String csvLine;
    private IllegalArgumentException exception;
    private String message;

    public CsvLineException(String csvLine, String message) {
        this.csvLine = csvLine;
        this.message = message;
    }

    public CsvLineException(String csvLine, NonpositiveArgumentException exception) {
        this.csvLine = csvLine;
        this.exception = exception;
        message = NonpositiveArgumentException.MESSAGE + " " + exception.getValue() + " " + exception.getFieldName();
    }

    @Override
    public String getMessage() {
        return csvLine + HYPHEN + message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
