
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {


    public static void main(String[] args) {

        final String INPUT_CSV = "src/in.csv";
        final String PLUS = " + ";
        final String MINUS = " - ";
        final int PLUS_LENGTH = PLUS.length();

        Scanner sc = null;

        try {
            sc = new Scanner(new FileReader(INPUT_CSV));
            sc.useLocale(Locale.ENGLISH);

            StringBuilder resultLine = new StringBuilder();
            double result = 0.0;
            int errorLines = 0;

            while (sc.hasNext()) {

                String line = sc.next();
                String[] elements = line.split(";");

                String element;
                int index;
                double number;

                try {
                    index = Integer.parseInt(elements[0]);

                    if (index < 0 || index >= elements.length) {
                        errorLines++;
                        continue;
                    }

                    number = Double.parseDouble(elements[index]);
                } catch (Exception e) {
                    errorLines++;
                    continue;
                }

                result += number;

                if (resultLine.length() > 0) {
                    if (number < 0) {
                        element = MINUS + number * -1;
                    } else {
                        element = PLUS + number;
                    }
                } else {
                    element = Double.toString(number);
                }

                resultLine.append(element);
            }

            System.out.println("result(" + resultLine + ") = " + Double.toString(result));
            System.out.println("error-lines = " + Integer.toString(errorLines));

        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
