import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {

    private static final String DATE_DELIMITER = "(\\-|\\/|\\.)";
    private static final String DATE_YEAR_CENTURY = "20";
    private static final String DATE_FULL = "[0-9]{1,2}(\\-|\\/|\\.)[0-9]{1,2}(\\-|\\/|\\.)[0-9]{2,4}";
    private static final String AMOUNT = "(^|\\s)(\\d(\\d|\\s|)+\\d)(\\s+)";
    private static final String AMOUNT_NO_DIGITS = "[^0-9]+";
    private static final String AMOUNT_AFTER = " ";

    private static final String INPUT_FILE = "src/in.txt";

    public static void main(String[] args) {

        Scanner sc = null;

        try {
            sc = new Scanner(new FileReader(INPUT_FILE));

            while (sc.hasNext()) {

                String line = sc.nextLine();

                line = replaceAmount(line);
                line = replaceDate(line);

                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    private static String replaceAmount(String line) {

        Pattern pattern = Pattern.compile(AMOUNT);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String oldAmount = matcher.group(2);
            String oldAfterAmount = matcher.group(4);
            String newAmount = oldAmount.replaceAll(AMOUNT_NO_DIGITS, "");

            line = line.replace(oldAfterAmount, AMOUNT_AFTER);
            line = line.replace(oldAmount, Integer.toString(Integer.parseInt(newAmount)));
        }

        return line;
    }

    private static String replaceDate(String line) {

        Pattern pattern = Pattern.compile(DATE_FULL);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String oldDate = matcher.group();

            String[] strings = oldDate.split(DATE_DELIMITER);
            Month[] months = Month.values();
            String month = months[Integer.parseInt(strings[1]) - 1].toString();
            String day = Integer.toString(Integer.parseInt(strings[0]));
            String year = strings[2].length() > 2 ? strings[2] : DATE_YEAR_CENTURY + strings[2];

            line = line.replace(oldDate, month + " " + day + ", " + year);
        }

        return line;
    }

}
