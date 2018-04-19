package by.gsu.epamlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmountParse extends AbstractParse {

    public AmountParse(String line) {
        super(line);
    }

    private static final String REGEX_AMOUNT = "(^|\\s)(\\d(\\d|\\s|)+\\d)(\\s+[b])";
    private static final String REGEX_SPACES = "\\s+";
    private static final String SPACE = " ";
    private static final String NO_SPACE = "";

    @Override
    public String parse() {

        String line = getLine().replaceAll(REGEX_SPACES, SPACE);

        Pattern pattern = Pattern.compile(REGEX_AMOUNT);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String oldAmount = matcher.group(2);
            String newAmount = oldAmount.replaceAll(REGEX_SPACES, NO_SPACE);
            line = line.replace(oldAmount, newAmount);
        }

        return line;
    }
}
