import by.gsu.epamlab.AbstractParse;
import by.gsu.epamlab.AmountParse;
import by.gsu.epamlab.DateParse;

import java.io.*;

public class Runner {

    private static final String INPUT_FILE = "src/in.txt";
    private static final String OUTPUT_FILE = "src/out.txt";
    private static final String END_LINE = "\n";

    public static void main(String[] args) {

        StringBuilder outLines = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {
                AbstractParse amount = new AmountParse(line);
                line = amount.parse();

                AbstractParse date = new DateParse(line);
                line = date.parse();

                outLines.append(line).append(END_LINE);
            }
        } catch (IOException e) {
            System.err.println("Error read from file");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            writer.write(outLines.toString());
        } catch (IOException e) {
            System.err.println("Error write to file");
        }
    }
}
