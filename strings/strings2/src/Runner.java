import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {

    public static void main(String[] args) {

        double result = 0.0;
        int errors = 0;

        final String KEY_INDEX = "index";
        final String KEY_VALUE = "value";
        final int KEY_INDEX_LENGTH = KEY_INDEX.length();

        Pattern patternValidation = Pattern.compile("(^[0]|[^0-9]|^$)");
        Pattern patternIndex = Pattern.compile("[0-9]+");
        Matcher matcher;

        try {
            ResourceBundle rb = ResourceBundle.getBundle("in");
            Enumeration<String> keys = rb.getKeys();
            String key;
            while (keys.hasMoreElements()) {
                key = keys.nextElement();

                if (key.substring(0, KEY_INDEX_LENGTH).equals(KEY_INDEX)) {

                    matcher = patternValidation.matcher(key.substring(KEY_INDEX_LENGTH));
                    if (matcher.find()) {
                        errors++;
                        continue;
                    }

                    matcher = patternValidation.matcher(rb.getString(key));
                    if (matcher.find()) {
                        errors++;
                        continue;
                    }

                    StringBuilder index = new StringBuilder();
                    matcher = patternIndex.matcher(key + rb.getString(key));
                    while (matcher.find()) {
                        index.append(matcher.group());
                    }

                    try {
                        String number = rb.getString(KEY_VALUE + index.toString()).trim();
                        result += Double.parseDouble(number);
                    } catch (Exception e) {
                        errors++;
                    }
                }
            }

            System.out.println("sum = " + result);
            System.out.println("error-lines = " + errors);

        } catch (MissingResourceException e) {
            System.out.println("Resource not found");
        }
    }
}
