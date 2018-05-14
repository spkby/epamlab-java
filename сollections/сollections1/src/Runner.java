import by.gsu.epamlab.Constants;
import by.gsu.epamlab.beans.Byn;
import by.gsu.epamlab.beans.PricePurchase;
import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.beans.PurchasesFactory;
import by.gsu.epamlab.enums.Weekdays;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {


    public static void main(String[] args) {
        final String path = "src/in.csv";
        Map<Purchase, Weekdays> firstMap = new HashMap<>();
        Map<Purchase, Weekdays> lastMap = new HashMap<>();
        Map<Weekdays, List<Purchase>> enumMap = new HashMap<>();
        List<PricePurchase> listDiscount = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(path));
            while (scanner.hasNext()) {

                Purchase p = PurchasesFactory.getPurchaseFromFactory(scanner.nextLine());
                Weekdays w = Weekdays.valueOf(scanner.nextLine());

                lastMap.put(p, w);

                if (!firstMap.containsKey(p)) {
                    firstMap.put(p, w);
                }

                if (!enumMap.containsKey(w)) {
                    enumMap.put(w, new ArrayList<Purchase>());
                }
                enumMap.get(w).add(p);

                if (p instanceof PricePurchase) {
                    listDiscount.add((PricePurchase) p);
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println(Constants.FILE_NOT_FOUND);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        System.out.println("print the map");
        print(firstMap);
        print(lastMap);

        System.out.println("find the first and the last weekdays for bread with price 1.55");
        System.out.println("first: " + search(firstMap, new Purchase("bread", 155, 1)));
        System.out.println("last: " + search(lastMap, new Purchase("bread", 155, 1)));
        System.out.print("find the first weekday for bread with price 1.70 ");
        System.out.println(search(firstMap, new Purchase("bread", 170, 1)));
        System.out.println();

        remove(firstMap, new Purchase("meat", 0, 0));
        remove(firstMap, Weekdays.FRIDAY);

        System.out.println("print the map");
        print(firstMap);
        print(lastMap);

        System.out.println(Constants.TOTAL_COST + "PricePurchase " + calcTotalCost(listDiscount));
        System.out.println();

        System.out.println("print the map");
        print(enumMap);
        for (Weekdays w : enumMap.keySet()) {
            System.out.println(Constants.TOTAL_COST + "in " + w + " " + calcTotalCost(enumMap.get(w)));
        }
        System.out.println(search(enumMap, Weekdays.MONDAY));

        System.out.println();
    }

    private static <K, V> void print(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + Constants.DASH + entry.getValue());
        }
        System.out.println();
    }

    private static <K, V, T> V search(Map<K, V> map, T t) {
        return map.get(t);
    }

    private static <T extends Purchase> Byn calcTotalCost(List<T> list) {
        Byn totalCost = new Byn(0);
        for (Purchase p : list) {
            totalCost.add(p.getCost());
        }
        return totalCost;
    }

    private static <K, V, T> void remove(Map<K, V> map, T t) {
    // TODO
    }
}
