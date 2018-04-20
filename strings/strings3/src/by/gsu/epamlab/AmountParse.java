package by.gsu.epamlab;

import java.util.regex.Matcher;

public class AmountParse extends Parse {

    public AmountParse() {
        super(REGEX_AMOUNT);
    }

    private static final String REGEX_AMOUNT = "(\\d+(\\s+\\d{3})*)\\s+[b]";
    private static final String REGEX_SPACES = "\\s+";
    private static final String NO_SPACE = "";
    private static final int GROUP = 1;

    @Override
    protected String format(Matcher matcher) {
        return Parse.replaceAll(matcher.group(GROUP), REGEX_SPACES, NO_SPACE);
    }

    @Override
    protected int group() {
        return GROUP;
    }
}
