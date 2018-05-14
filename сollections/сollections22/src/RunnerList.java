import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RunnerList {

    private static final String PATH = "src/in.txt";
    private static final String PATTERN = "[\\s(;)]+";
    private static final String FILE_NOT_FOUND = "File not found";

    private static final int POSITION_X1 = 1;
    private static final int POSITION_X2 = 2;
    private static final int POSITION_Y1 = 3;
    private static final int POSITION_Y2 = 4;

    public static void main(String[] args) {

        List<Integer> lenlist = new ArrayList<>();
        List<Integer> numlist = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(PATH));
            while (scanner.hasNext()) {
                String[] elements = Pattern.compile(PATTERN).split(scanner.nextLine());
                double x1 = Double.parseDouble(elements[POSITION_X1]);
                double x2 = Double.parseDouble(elements[POSITION_X2]);
                double y1 = Double.parseDouble(elements[POSITION_Y1]);
                double y2 = Double.parseDouble(elements[POSITION_Y2]);
                int len = (int) Math.round(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));

                int index = Collections.binarySearch(lenlist, len);
                if (index >= 0) {
                    numlist.set(index, numlist.get(index) + 1);
                } else {
                    lenlist.add(-index - 1, len);
                    numlist.add(-index - 1, 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_NOT_FOUND);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        print(lenlist, numlist);
    }

    private static void print(List<Integer> lenlist, List<Integer> numlist) {
        sort(lenlist, numlist);
        for (int i = 0; i < lenlist.size(); i++) {
            System.out.println(lenlist.get(i) + ";" + numlist.get(i));
        }
    }

    private static void sort(List<Integer> lenlist, List<Integer> numlist) {

        for (int i = 0; i < numlist.size(); i++) {
            for (int j = numlist.size() - 1; j > i; j--) {

                if (numlist.get(j) > numlist.get(j - 1)) {
                    int tmp = numlist.get(j);
                    numlist.set(j, numlist.get(j - 1));
                    numlist.set(j - 1, tmp);

                    tmp = lenlist.get(j);
                    lenlist.set(j, lenlist.get(j - 1));
                    lenlist.set(j - 1, tmp);
                }
            }
        }
    }
}
