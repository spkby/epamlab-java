import by.gsu.epamlab.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Runner {

    private static final String PATH = "src/in.txt";
    private static final String PATTERN = "(\\s*\\(\\s*)|(\\s*[;]\\s*)|(\\s*\\)\\s*)";
    private static final String FILE_NOT_FOUND = "File not found";

    private static final int POSITION_X1 = 1;
    private static final int POSITION_X2 = 2;
    private static final int POSITION_Y1 = 4;
    private static final int POSITION_Y2 = 5;

    public static void main(String[] args) {

        List<LenNum> list = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(PATH));
            while (scanner.hasNext()) {
                String[] elements = Pattern.compile(PATTERN).split(scanner.nextLine());
                double x1 = Double.parseDouble(elements[POSITION_X1]);
                double x2 = Double.parseDouble(elements[POSITION_X2]);
                double y1 = Double.parseDouble(elements[POSITION_Y1]);
                double y2 = Double.parseDouble(elements[POSITION_Y2]);
                add(list, Utils.calcLen(x1, x2, y1, y2));
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_NOT_FOUND);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        print(list);
    }

    private static void add(List<LenNum> list, int len) {
        int index = search(list, len);
        if (index >= 0) {
            list.get(index).increment();
        } else {
            list.add(new LenNum(len));
        }
    }

    private static int search(List<LenNum> list, int len) {
        list.sort(new LenComparator());
        return Collections.binarySearch(list, new LenNum(len), new LenComparator());
    }

    private static void print(List<LenNum> list) {
        list.sort(new NumComparator());
        for (LenNum lenNum : list) {
            System.out.println(lenNum);
        }
    }
}
