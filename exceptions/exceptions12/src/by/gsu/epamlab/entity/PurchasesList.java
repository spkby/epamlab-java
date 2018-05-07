package by.gsu.epamlab.entity;

import by.gsu.epamlab.comparators.PurchaseComparatorBuilder;
import by.gsu.epamlab.exceptions.CsvLineException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchasesList {

    private static final String path = "src/";
    private static final String ext = ".csv";
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String FORMATTER_TOTALCOST = "%30s";
    private static final String TOTAL_COST = "Total cost ";

    private boolean isSorted = false;

    private List<Purchase> purchases;

    private final Comparator<Purchase> comparator;

    {
        this.comparator = PurchaseComparatorBuilder.getPurchaseComparator();
    }

    public PurchasesList() {
        purchases = new ArrayList<>();

    }

    public PurchasesList(String fileName) {
        this();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(path + fileName + ext));

            while (scanner.hasNext()) {
                Purchase purchase = null;
                try {
                    purchase = PurchasesFactory.getPurchaseFromFactory(scanner.next());
                } catch (CsvLineException e) {
                    System.err.println(e);
                }
                if (purchase != null) {
                    purchases.add(purchase);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public int size() {
        return purchases.size();
    }

    public String toTable() {

        Formatter formatter = new Formatter();
        StringBuilder builder = new StringBuilder();

        String format = TableRows.NAME.get() +
                TableRows.PRICE.get() +
                TableRows.NUMBER.get() +
                TableRows.DISCOUNT.get() +
                TableRows.COST.get();
        formatter.format(format, TableRows.NAME, TableRows.PRICE, TableRows.NUMBER,
                TableRows.DISCOUNT, TableRows.COST);
        builder.append(formatter).append(NEW_LINE);

        for (Purchase purchase : purchases) {
            builder.append(purchase.print()).append(NEW_LINE);
        }

        formatter = new Formatter();
        formatter.format(TOTAL_COST + FORMATTER_TOTALCOST + NEW_LINE, getTotalCost());
        builder.append(formatter);

        return builder.toString();
    }

    public Byn getTotalCost() {

        Byn totalCost = new Byn(0);

        for (Purchase purchase : purchases) {
            totalCost.add(purchase.getCost());
        }

        return totalCost;
    }

    public void insert(int index, Purchase purchase) {
        if (purchase == null) {
            return;
        }
        if (isCorrectIndex(index)) {
            purchases.add(index, purchase);
        } else {
            purchases.add(purchase);
        }
        unSorted();
    }

    private boolean isCorrectIndex(int index) {
        return index >= 0 && index < purchases.size();
    }

    public int delete(int index) {
        int result;
        if (isCorrectIndex(index)) {
            purchases.remove(index);
            result = purchases.size();
            unSorted();
        } else {
            result = -1;
        }
        return result;
    }

    public Purchase getPurchaseByIndex(int index) {
        Purchase purchase;
        if (isCorrectIndex(index)) {
            purchase = purchases.get(index);
        } else {
            purchase = null;
        }
        return purchase;
    }

    public void sort() {
        Collections.sort(purchases, comparator);
        isSorted = true;
    }

    public int search(Purchase purchase) {
        if (!isSorted) {
            sort();
        }
        return Collections.binarySearch(purchases, purchase, comparator);
    }

    private void unSorted() {
        isSorted = false;
    }
}
