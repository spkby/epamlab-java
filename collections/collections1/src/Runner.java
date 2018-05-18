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
        Map<Purchase, Weekdays> purchaseWeekdaysFirstMap = new HashMap<>();
        Map<Purchase, Weekdays> purchaseWeekdaysLastMap = new HashMap<>();
        Map<Weekdays, List<Purchase>> weekdaysPurchasesMap = new HashMap<>();
        List<PricePurchase> pricePurchasesList = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(path));
            while (scanner.hasNext()) {

                Purchase p = PurchasesFactory.getPurchaseFromFactory(scanner.nextLine());
                Weekdays w = Weekdays.valueOf(scanner.nextLine());

                purchaseWeekdaysLastMap.put(p, w);

                if (!purchaseWeekdaysFirstMap.containsKey(p)) {
                    purchaseWeekdaysFirstMap.put(p, w);
                }

                if (!weekdaysPurchasesMap.containsKey(w)) {
                    weekdaysPurchasesMap.put(w, new ArrayList<Purchase>());
                }
                weekdaysPurchasesMap.get(w).add(p);

                if (p.getClass() == PricePurchase.class) {
                    pricePurchasesList.add((PricePurchase) p);
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println(Constants.FILE_NOT_FOUND);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        System.out.println(Constants.PRINT_THE_MAP);
        print(purchaseWeekdaysFirstMap);
        print(purchaseWeekdaysLastMap);

        System.out.println("find the first and the last weekdays for bread with price 1.55");
        System.out.println("first: " + search(purchaseWeekdaysFirstMap, new Purchase("bread", 155, 1)));
        System.out.println("last: " + search(purchaseWeekdaysLastMap, new Purchase("bread", 155, 1)));
        System.out.print("find the first weekday for bread with price 1.70 ");
        System.out.println(search(purchaseWeekdaysFirstMap, new Purchase("bread", 170, 1)));
        System.out.println();

        remove(purchaseWeekdaysFirstMap, new Purchase("meat", 0, 0));
        remove(purchaseWeekdaysLastMap, Weekdays.FRIDAY);

        System.out.println(Constants.PRINT_THE_MAP);
        print(purchaseWeekdaysFirstMap);
        print(purchaseWeekdaysLastMap);

        System.out.println(Constants.TOTAL_COST + "PricePurchase " + calcTotalCost(pricePurchasesList));
        System.out.println();

        System.out.println(Constants.PRINT_THE_MAP);
        print(weekdaysPurchasesMap);
        for (Weekdays w : weekdaysPurchasesMap.keySet()) {
            System.out.println(Constants.TOTAL_COST + "in " + w + " " + calcTotalCost(weekdaysPurchasesMap.get(w)));
        }

        System.out.print("All purchases in " + Weekdays.MONDAY + ": ");
        System.out.println(search(weekdaysPurchasesMap, Weekdays.MONDAY));

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
        if (t instanceof Purchase) {
            for (Iterator<K> itK = map.keySet().iterator(); itK.hasNext(); ) {
                if (((Purchase) itK.next()).getName().equals(((Purchase) t).getName())) {
                    itK.remove();
                }
            }
        }

        for (Iterator<V> itV = map.values().iterator(); itV.hasNext(); ) {
            if (itV.next().equals(t)) {
                itV.remove();
            }
        }

    }
}
