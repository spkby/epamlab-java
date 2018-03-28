import by.gsu.epamlab.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        final String FILE_PATH = "src/in.txt";

        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(FILE_PATH));

            // 1. Create an array for PURCHASES_NUMBER purchases.
            final int PURCHASES_NUMBER = sc.nextInt();
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];

            // 2. Initialize this array by the file data.
            for (int i = 0; i < PURCHASES_NUMBER; i++) {

                int price = sc.nextInt();
                int percent = sc.nextInt();
                int day = sc.nextInt();

                purchases[i] = new Purchase(price, percent, day);
            }

            // 3. Output the array content to the console
            printPurchases(purchases);

            // 4. Calculates
            int totalCost = 0;
            int totalCostMonday = 0;
            int maxCost = 0;
            double meanCost = 0.0;
            WeekDay maxCostDay = null;

            for (Purchase purchase : purchases) {
                if (maxCost < purchase.getCost()) {
                    maxCost = purchase.getCost();
                    maxCostDay = purchase.getWeekDay();
                }
                if (purchase.getWeekDay() == WeekDay.MONDAY) {
                    totalCostMonday += purchase.getCost();
                }
                totalCost += purchase.getCost();
            }

            if (purchases.length > 0) {
                meanCost = ((double) totalCost) / purchases.length;
            }

            System.out.println("Mean cost = " + Utils.toRublesWithThreeDigit(meanCost));
            System.out.println("The total cost on Monday = " + Utils.toRublesWithTwoDigit(totalCostMonday));
            System.out.println("The day with the maximum cost purchase is " + maxCostDay);

            // 5. Sort the array by the field number in the ascending order
            Arrays.sort(purchases);

            // 6. Output the array content to the console
            printPurchases(purchases);

            // 7. Find some purchase with number equaled to 5
            purchaseWithNumber(purchases);

        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    private static void purchaseWithNumber(Purchase[] purchases) {
        int index = Arrays.binarySearch(purchases, new Purchase(5, 0, null));

        if (index < 0) {
            System.out.println("Required purchase is not found");
        } else {
            System.out.println("Required purchase is " + purchases[index]);
        }
    }

    private static void printPurchases(Purchase[] purchases) {
        System.out.println(Purchase.PRODUCT_NAME + " " + Utils.toRublesWithTwoDigit(Purchase.PRICE));
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
    }
}
