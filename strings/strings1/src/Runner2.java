import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Runner2 {

    public static void main(String[] args) {

        Scanner scanner = null;

        try {
            final String INPUT_CSV = "src/in.csv";
            final String BEFORE_SIGN = " ";
            final String AFTER_SIGN = " ";
            final String PLUS = BEFORE_SIGN + "+" + AFTER_SIGN;
            final String MINUS = BEFORE_SIGN + "-" + AFTER_SIGN;
            final String DELIMITER = ";";
            final String RESULT_HEAD = "result(";
            final String RESULT_TAIL = ") = ";
            final String ERROR_LINES = "error-lines = ";

            scanner = new Scanner(new FileReader(INPUT_CSV));

            StringBuilder strResult = new StringBuilder();
            double numResult = 0;
            int errorLines = 0;

            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().split(DELIMITER);
                try {
                    //get index
                    int index = Integer.parseInt(words[0]);
                    //get number
                    double x = Double.parseDouble(words[index]);
                    //change numeric result
                    numResult += x;
                    //change string result
                    strResult.append(x < 0 ? MINUS : PLUS).append(Math.abs(x));
                } catch (Exception e) {
                    errorLines++;
                }
            }

            //minus sign can be surrounded by any number of spaces on both as left as right
            //or spaces to the left, tab to the right, etc
            //further code will not change!
            //if something is not clear, use the debugger.
            if (strResult.length() > 0) {
                final int SIGN_LENGTH = MINUS.length();
                final char CHAR_MINUS = '-';
                final int SIGN_POS = MINUS.indexOf(CHAR_MINUS);
                char symbol = strResult.charAt(SIGN_POS);
                strResult.delete(0, SIGN_LENGTH);
                if (symbol == CHAR_MINUS) {
                    strResult.insert(0, CHAR_MINUS);
                }
            }

            //output
            System.out.println(RESULT_HEAD + strResult + RESULT_TAIL + numResult);
            System.out.println(ERROR_LINES + errorLines);

        } catch (FileNotFoundException e) {
            System.out.println("No input file...");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
