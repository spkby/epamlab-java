package by.gsu.epamlab;

import by.gsu.epamlab.entity.Byn;
import by.gsu.epamlab.entity.Purchase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchasesList {

    private static final String path = "src/";
    private static final String ext = ".csv";

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

    public void printList() {

        System.out.printf("%-6s %5s %5s %5s %5s\n", "Name", "Price", "Number", "Discount", "Cost");
        for (Purchase purchase : purchases) {
            System.out.println(purchase.print());
        }
        System.out.printf("Total cost %23s\n", getTotalCost());
    }

    public Byn getTotalCost() {

        Byn totalCost = new Byn(0);

        for (Purchase purchase : purchases) {
            totalCost.add(purchase.getCost());
        }

        return totalCost;
    }

    public void insert(int index, Purchase purchase) {
        if (isIndex(index)) {
            index = purchases.size() - 1;
        }
        purchases.add(index, purchase);
    }

    private boolean isIndex(int index) {
        return index < 0 || index > purchases.size();
    }

    public void delete(int index) {
        if (isIndex(index)) {
            return;
        }
        purchases.remove(index);
    }

    public void sort(Comparator<Purchase> comparator){
        purchases.sort(comparator);
    }

    public int search(){

        //Collections.binarySearch(purchases,new Purchase(null,1,1));

        return 0;
    }
}
