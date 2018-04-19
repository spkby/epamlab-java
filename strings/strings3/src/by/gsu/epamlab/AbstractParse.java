package by.gsu.epamlab;

abstract public class AbstractParse {

    private final String line;

    public AbstractParse(String line) {
        this.line = line;
    }

    protected String getLine() {
        return line;
    }

    abstract public String parse();

}
