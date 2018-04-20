import by.gsu.epamlab.Parse;
import by.gsu.epamlab.AmountParse;
import by.gsu.epamlab.DateParse;

import java.io.*;

public class Runner {

    private static final String INPUT_FILE = "src/in.txt";
    private static final String OUTPUT_FILE = "src/out.txt";

    private static final String REGEX_SPACES = "\\s+";
    private static final String SPACE = " ";


    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {
                Parse amount = new AmountParse();
                Parse date = new DateParse();

                line = Parse.replaceAll(line, REGEX_SPACES, SPACE);

                line = date.format(amount.format(line));

                writer.write(line + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            System.err.println("Error work this file");
        }
    }
}
