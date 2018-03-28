import by.gsu.epamlab.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {

    private static String FILE_PATH = "src/in.txt";

    public static void main(String[] args) {

        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(FILE_PATH));

            // 1. Create an array for PURCHASES_NUMBER purchases.
            final int PURCHASES_NUMBER = sc.nextInt();
            if (PURCHASES_NUMBER > 10 || PURCHASES_NUMBER < 0) {
                throw new RuntimeException("Invalid PURCHASES_NUMBER");
            }
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];

            // 2. Initialize this array by the file data.
            for (int i = 0; i < PURCHASES_NUMBER; i++) {

                int price = sc.nextInt();
                int percent = sc.nextInt();
                int day = sc.nextInt();

                if (day > 6 || day < 0) {
                    throw new RuntimeException("Invalid Weekday");
                }

                purchases[i] = new Purchase(price, percent, WeekDay.valueOf(day));
            }

            // 3. Output the array content to the console
            out(purchases);

            // 4. Calculate the mean cost of all purchases
            meanCost(purchases);
            totalCostOnMonday(purchases);
            dayWithMaxCostPurchase(purchases);

            // 5. Sort the array by the field number in the ascending order
            Arrays.sort(purchases);

            // 6. Output the array content to the console
            out(purchases);

            // 7. Find some purchase with number equaled to 5 by the method binarySearch( ) of the class Arrays and output it.
            purchaseWithNumber(purchases);

        } catch (InputMismatchException e) {
            System.err.println("Invalid input file");
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    private static void purchaseWithNumber(Purchase[] purchases) {
        int index = -1;
        index = Arrays.binarySearch(purchases, new Purchase(5, 0, null));

        if (index > -1) {
            System.out.println("Required purchase is " + purchases[index]);
        } else {
            System.out.println("Required purchase is not found");
        }
    }

    private static void dayWithMaxCostPurchase(Purchase[] purchases) {

        WeekDay weekDay = null;
        int cost = -1;

        for (Purchase purchase : purchases) {
            if (cost < purchase.getCost()) {
                cost = purchase.getCost();
                weekDay = purchase.getWeekDay();
            }
        }
        System.out.println("The day with the maximum cost purchase is " + weekDay);
    }

    private static void totalCostOnMonday(Purchase[] purchases) {
        int cost = 0;

        for (Purchase purchase : purchases) {
            if (purchase.getWeekDay() == WeekDay.MONDAY) {
                cost += purchase.getCost();
            }
        }
        System.out.println("The total cost on Monday = " + Purchase.toRubles(cost));
    }

    private static void meanCost(Purchase[] purchases) {

        if (purchases.length != 0) {
            int cost = 0;

            for (Purchase purchase : purchases) {
                cost += purchase.getCost();
            }
            cost *= 10;
            System.out.println("Mean cost = " + Purchase.toRublesWithThreeDigit(cost / purchases.length));
        } else {
            System.out.println("Mean cost = 0.000");
        }
    }

    private static void out(Purchase[] purchases) {
        if (purchases.length > 0) {
            System.out.println(Purchase.PRODUCT_NAME + " " + Purchase.toRubles(Purchase.PRICE));
            for (Purchase purchase : purchases) {
                System.out.println(purchase);
            }
        }
    }
}
