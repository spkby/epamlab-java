import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;

public class RunnerMap {

    private static final String PATH = "src/in.txt";
    private static final String PATTERN = "[\\s(;)]+";
    private static final String FILE_NOT_FOUND = "File not found";

    private static final int POSITION_X1 = 1;
    private static final int POSITION_X2 = 2;
    private static final int POSITION_Y1 = 3;
    private static final int POSITION_Y2 = 4;

    public static void main(String[] args) {

        Map<Integer, Integer> lenNumMap = new TreeMap<>();
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

                if (lenNumMap.containsKey(len)) {
                    lenNumMap.put(len, lenNumMap.get(len) + 1);
                } else {
                    lenNumMap.put(len, 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_NOT_FOUND);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        print(lenNumMap);
    }

    private static void print(Map<Integer, Integer> lenNumMap) {
        lenNumMap = sortMapByValue(lenNumMap);

        for (Map.Entry<Integer, Integer> entry : lenNumMap.entrySet()) {
            System.out.println(entry.getKey() + ";" + entry.getValue());
        }
    }

    public static Map sortMapByValue(Map map) {
        Comparator<Integer> comparator = new ValueComparator(map);
        Map<Integer, Integer> result = new TreeMap<>(comparator);
        result.putAll(map);
        return result;
    }

    private static class ValueComparator implements Comparator<Integer> {
        Map<Integer, Integer> map = new TreeMap<>();

        public ValueComparator(Map map) {
            this.map.putAll(map);
        }

        @Override
        public int compare(Integer a, Integer b) {
            int result = map.get(b) - map.get(a);
            if (result == 0) result++;
            return result;
        }
    }
}
