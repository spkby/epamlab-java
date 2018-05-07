package by.gsu.epamlab.entity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchasesList {

    private static final String path = "src/";
    private static final String ext = ".csv";
    private static final String FORMATTER_HEAD = "%-6s %5s %5s %5s %5s\n";
    private static final String FORMATTER_TOTALCOST = "%23s";

    private List<Purchase> purchases;

    public PurchasesList() {
        purchases = new ArrayList<>();
    }

    public PurchasesList(String fileName) {
        this();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(path + fileName + ext));

            while (scanner.hasNext()) {
                Purchase purchase = PurchasesFactory.getPurchaseFromFactory(scanner);
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

    public void printList() {

        System.out.printf(FORMATTER_HEAD, "Name", "Price", "Number", "Discount", "Cost");
        for (Purchase purchase : purchases) {
            System.out.println(purchase.print());
        }
        System.out.printf("Total cost " + FORMATTER_TOTALCOST + "\n", getTotalCost());
    }

    public Byn getTotalCost() {

        Byn totalCost = new Byn(0);

        for (Purchase purchase : purchases) {
            totalCost.add(purchase.getCost());
        }

        return totalCost;
    }

    public void insert(int index, Purchase purchase) {
        if (isInvalidIndex(index)) {
            index = purchases.size() - 1;
        }
        purchases.add(index, purchase);
    }

    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= purchases.size();
    }

    public void delete(int index) {
        if (isInvalidIndex(index)) {
            System.err.println("Error Deletion: invalid index");
            return;
        }
        purchases.remove(index);
    }

    public Purchase getPurchaseByIndex(int index) {

        if (isInvalidIndex(index)) {
            System.err.println("Error get Purchase: invalid index");
            return null;
        }

        return purchases.get(index);
    }

    public void sort(Comparator<Purchase> comparator) {
        purchases.sort(comparator);
    }

    public int search(String productName, Byn price, int numberUnits) {
        return search(productName, price, numberUnits, null);
    }

    public int search(Purchase purchase) {
        return Collections.binarySearch(purchases, purchase, Collections.reverseOrder());
    }

    public int search(String productName, Byn price, int numberUnits, Byn discount) {

        int priceCoins = price.getRubs() * 100 + price.getCoins();

        Purchase purchase;

        if (discount != null) {
            int discountCoins = discount.getRubs() * 100 + discount.getCoins();
            purchase = new PriceDiscountPurchase(productName, priceCoins, numberUnits, discountCoins);
        } else {
            purchase = new Purchase(productName, priceCoins, numberUnits);
        }

        return search(purchase);
    }
}
