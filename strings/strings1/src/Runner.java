import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        final String INPUT_CSV = "src/in.csv";
        final String PLUS = " + ";
        final String MINUS = " - ";
        final String DELIMITER = ";";
        final String RESULT_HEAD = "result(";
        final String RESULT_TAIL = ") = ";
        final String ERROR_LINES = "error-lines = ";

        Scanner sc = null;

        try {
            sc = new Scanner(new FileReader(INPUT_CSV));

            StringBuilder resultLine = new StringBuilder();
            double result = 0.0;
            int errorLines = 0;

            while (sc.hasNext()) {

                String line = sc.next();
                String[] elements = line.split(DELIMITER);

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
                StringBuilder element = new StringBuilder();

                if (resultLine.length() > 0) {
                    element.append(number < 0 ? MINUS : PLUS).append(Math.abs(number));
                } else {
                    element.append(number);
                }

                resultLine.append(element);
            }

            System.out.println(RESULT_HEAD + resultLine + RESULT_TAIL + Double.toString(result));
            System.out.println(ERROR_LINES + Integer.toString(errorLines));

        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
