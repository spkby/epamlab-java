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

        Scanner scanner = null;
        try {

            Map<Purchase, Weekdays> purchaseWeekdaysFirstMap = new HashMap<>();
            Map<Purchase, Weekdays> purchaseWeekdaysLastMap = new HashMap<>();
            Map<Weekdays, List<Purchase>> weekdaysPurchasesMap = new EnumMap<>(Weekdays.class);
            List<PricePurchase> pricePurchasesList = new ArrayList<>();

            scanner = new Scanner(new FileReader(path));
            while (scanner.hasNext()) {

                Purchase p = PurchasesFactory.getPurchaseFromFactory(scanner.nextLine());
                Weekdays w = Weekdays.valueOf(scanner.nextLine());

                purchaseWeekdaysLastMap.put(p, w);

                if (!purchaseWeekdaysFirstMap.containsKey(p)) {
                    purchaseWeekdaysFirstMap.put(p, w);
                }

                List<Purchase> l = weekdaysPurchasesMap.get(w);
                if (l == null)
                    weekdaysPurchasesMap.put(w, l = new ArrayList<>());
                l.add(p);

                if (p.getClass() == PricePurchase.class) {
                    pricePurchasesList.add((PricePurchase) p);
                }
            }

            print(Constants.PURCHASE_WEEKDAY_FIRST_MAP, purchaseWeekdaysFirstMap);
            print(Constants.PURCHASE_WEEKDAY_LAST_MAP, purchaseWeekdaysLastMap);

            Purchase searchPurchase = new Purchase("bread", 155, 1);

            search(purchaseWeekdaysFirstMap, searchPurchase);
            search(purchaseWeekdaysLastMap, searchPurchase);
            searchPurchase.getPrice().add(new Byn(15));
            search(purchaseWeekdaysFirstMap, searchPurchase);

            remove(purchaseWeekdaysFirstMap, new EntryChecker<Purchase, Weekdays>() {
                @Override
                public boolean check(Map.Entry<Purchase, Weekdays> entry) {
                    Purchase purchaseCheck = new Purchase("meat", 0, 0);
                    return entry.getKey().getName().equals(purchaseCheck.getName());
                }
            });

            remove(purchaseWeekdaysLastMap, new EntryChecker<Purchase, Weekdays>() {
                @Override
                public boolean check(Map.Entry entry) {
                    Weekdays weekdaysCheck = Weekdays.FRIDAY;
                    return entry.getKey() == weekdaysCheck;
                }
            });

            print(Constants.PURCHASE_WEEKDAY_FIRST_MAP, purchaseWeekdaysFirstMap);
            print(Constants.PURCHASE_WEEKDAY_LAST_MAP, purchaseWeekdaysLastMap);

            System.out.println(Constants.TOTAL_COST + "PricePurchase is " + calcTotalCost(pricePurchasesList));

            print(Constants.WEEKDAY_PURCHASES_ENUM_MAP, weekdaysPurchasesMap);
            for (Map.Entry<Weekdays, List<Purchase>> entry : weekdaysPurchasesMap.entrySet()) {
                System.out.println(Constants.TOTAL_COST + "in " + entry.getKey() + " is " + calcTotalCost(entry.getValue()));
            }

            System.out.print("All purchases in " + Weekdays.MONDAY + ": ");
            search(weekdaysPurchasesMap, Weekdays.MONDAY);

            remove(weekdaysPurchasesMap, new EntryChecker<Weekdays, List<Purchase>>() {
                @Override
                public boolean check(Map.Entry<Weekdays, List<Purchase>> entry) {
                    Purchase purchaseCheck = new Purchase("milk", 0, 0);
                    List<Purchase> pList = entry.getValue();
                    for (Purchase p : pList) {
                        if (p.getName().equals(purchaseCheck.getName())) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            print(Constants.WEEKDAY_PURCHASES_ENUM_MAP, weekdaysPurchasesMap);

        } catch (FileNotFoundException e) {
            System.err.println(Constants.FILE_NOT_FOUND);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static <K, V> void print(String header, Map<K, V> map) {
        System.out.println();
        System.out.println(Constants.PRINT_THE_MAP + " " + header);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + Constants.DASH + entry.getValue());
        }
        System.out.println();
    }

    private static <K, V> void search(Map<K, V> map, K key) {
        V value = map.get(key);
        System.out.println("Value " + key + " is " + (value != null ? value : "not found"));
    }

    private static Byn calcTotalCost(List<? extends Purchase> list) {
        Byn totalCost = new Byn(0);
        for (Purchase p : list) {
            totalCost.add(p.getCost());
        }
        return totalCost;
    }

    public interface EntryChecker<K, V> {
        boolean check(Map.Entry<K, V> entry);
    }

    private static <K, V> void remove(Map<K, V> map, EntryChecker checker) {
        for (Iterator<Map.Entry<K, V>> itr = map.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry<K, V> entry = itr.next();
            if (checker.check(entry)) {
                itr.remove();
            }
        }
    }

}