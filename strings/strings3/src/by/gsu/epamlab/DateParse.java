package by.gsu.epamlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParse extends AbstractParse {

    public DateParse(String line) {
        super(line);
    }

    private static final String[] months = new String[]{
            null, "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    private static final String REGEX_DATE_DELIMITER = "(\\-|\\/|\\.)";
    private static final String DATE_YEAR_CENTURY = "20";

    private static final String REGEX_DATE_POINT = "([0-3]?[0-9]\\.[0-1]?[0-9]\\.(?:[0-9]{2})?[0-9]{2})";
    private static final String REGEX_DATE_SLASH = "([0-3]?[0-9]\\/[0-1]?[0-9]\\/(?:[0-9]{2})?[0-9]{2})";
    private static final String REGEX_DATE_HYPHEN = "([0-3]?[0-9]\\-[0-1]?[0-9]\\-(?:[0-9]{2})?[0-9]{2})";

    @Override
    public String parse() {

        String line = getLine();

        Pattern pattern = Pattern.compile(REGEX_DATE_HYPHEN + "|" + REGEX_DATE_POINT + "|" + REGEX_DATE_SLASH);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String oldDate = matcher.group();

            String[] strings = oldDate.split(REGEX_DATE_DELIMITER);
            String month = months[Integer.parseInt(strings[1])];
            String day = Integer.toString(Integer.parseInt(strings[0]));
            String year = strings[2].length() > 2 ? strings[2] : DATE_YEAR_CENTURY + strings[2];

            line = getLine().replace(oldDate, month + " " + day + ", " + year);
        }

        return line;
    }
}
