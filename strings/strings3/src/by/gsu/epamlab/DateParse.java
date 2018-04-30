package by.gsu.epamlab;

import java.util.regex.Matcher;

public class DateParse extends Parse {

    public DateParse() {
        super(REGEX_DATE);
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

    private static final String REGEX_DATE = REGEX_DATE_HYPHEN + "|" + REGEX_DATE_POINT + "|" + REGEX_DATE_SLASH;

    private static final int GROUP = 0;

    @Override
    protected String format(Matcher matcher) {

        String oldDate = matcher.group();
        String[] strings = oldDate.split(REGEX_DATE_DELIMITER);
        String month = months[Integer.parseInt(strings[1])];
        String day = Integer.toString(Integer.parseInt(strings[0]));
        String year = strings[2].length() > 2 ? strings[2] : DATE_YEAR_CENTURY + strings[2];

        return month + " " + day + ", " + year;
    }

    @Override
    protected int group() {
        return GROUP;
    }
}
