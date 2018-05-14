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
    private static final String PATTERN = "[\\s(;)]+";
    private static final String FILE_NOT_FOUND = "File not found";

    private static final int POSITION_X1 = 1;
    private static final int POSITION_X2 = 2;
    private static final int POSITION_Y1 = 3;
    private static final int POSITION_Y2 = 4;

    public static void main(String[] args) {

        List<LenNum> list = new ArrayList<>();
        Scanner scanner = null;
        try {
            LenNum lenNum;
            scanner = new Scanner(new FileReader(PATH));
            while (scanner.hasNext()) {
                String[] elements = Pattern.compile(PATTERN).split(scanner.nextLine());
                double x1 = Double.parseDouble(elements[POSITION_X1]);
                double x2 = Double.parseDouble(elements[POSITION_X2]);
                double y1 = Double.parseDouble(elements[POSITION_Y1]);
                double y2 = Double.parseDouble(elements[POSITION_Y2]);
                lenNum = new LenNum(Utils.calcLen(x1, x2, y1, y2));

                int index = Collections.binarySearch(list, lenNum);
                if (index >= 0) {
                    list.get(index).incNum();
                } else {
                    list.add(-index - 1, lenNum);
                }
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

    private static void print(List<LenNum> list) {
        list.sort(new NumComparator());
        for (LenNum lenNum : list) {
            System.out.println(lenNum);
        }
    }
}
