import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner2 {

    public static void main(String[] args) {

        double result = 0.0;
        int errors = 0;

        try {
            ResourceBundle rb = ResourceBundle.getBundle("in");
            Enumeration<String> keys = rb.getKeys();
            final String KEY_REG_EXP = "index(.*)";
            final String NUM_REG_EXP = "[1-9]\\d*";

            //compile patterns for above expressions here
            final int TAIL_INDEX = 1;
            final String VALUE = "value";
            String key;

            //other declarations

            while (keys.hasMoreElements()) {
                key = keys.nextElement();

                //create keyMatcher for key on KEY_REG_EXP
                Pattern pattern = Pattern.compile(KEY_REG_EXP);
                Matcher keyMatcher = pattern.matcher(key);

                if (keyMatcher.matches()) {
                    String iStr = keyMatcher.group(TAIL_INDEX);
                    String jStr = rb.getString(key).trim();

                    //create iMather for iStr, jMather for jStr on NUM_REG_EXP
                    pattern = Pattern.compile(NUM_REG_EXP);
                    Matcher iMatcher = pattern.matcher(iStr);
                    pattern = Pattern.compile(NUM_REG_EXP);
                    Matcher jMatcher = pattern.matcher(jStr);


                    if (iMatcher.matches() && jMatcher.matches()) {
                        String valueIJ = VALUE + iStr + jStr;
                        try {
                            //read value on the key valueIJ
                            //increase sum on the value
                            String number = rb.getString(valueIJ).trim();
                            result += Double.parseDouble(number);

                        } catch (Exception e) {
                            errors++;
                        }
                    } else {
                        errors++;
                    }
                }
            }

            //output results
            System.out.println("sum = " + result);
            System.out.println("error-lines = " + errors);

        } catch (MissingResourceException e) {
            System.out.println("No input file...");
        }


    }
}
