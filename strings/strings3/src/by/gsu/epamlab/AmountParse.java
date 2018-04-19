package by.gsu.epamlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmountParse extends AbstractParse {

    public AmountParse(String line) {
        super(line);
    }

    private static final String REGEX_AMOUNT = "\\d+(\\s+\\d{3})*\\s+[b]";
    private static final String REGEX_SPACES = "\\s+";
    private static final String SPACE = " ";
    private static final String NO_SPACE = "";
    private static final String SPACE_B = " b";
    private static final int LENGTH_SPACE_B = SPACE_B.length();

    @Override
    public String parse() {

        String line = getLine().replaceAll(REGEX_SPACES, SPACE);

        Pattern pattern = Pattern.compile(REGEX_AMOUNT);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            StringBuilder oldAmount = new StringBuilder(matcher.group());
            oldAmount.delete(oldAmount.length() - LENGTH_SPACE_B, oldAmount.length());

            String newAmount = Pattern.compile(REGEX_SPACES).matcher(oldAmount).replaceAll(NO_SPACE);
            line = line.replace(oldAmount, newAmount);
        }

        return line;
    }
}
