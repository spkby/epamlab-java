import by.gsu.epamlab.*;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {

        // 1. Create the unique product for purchasing.
        final Product milk = new Product("Milk", new Byn(234));

        // 2. Create an array for 6 objects
        AbstractPurchase[] purchases = new AbstractPurchase[]{
                new TransportExpensesPurchase(milk, 7, 753),
                new PercentDiscountPurchase(milk, 8, 4.12),
                new PriceDiscountPurchase(milk, 6, 23),
                new PercentDiscountPurchase(milk, 15, 2.56),
                new TransportExpensesPurchase(milk, 1, 123),
                new PriceDiscountPurchase(milk, 3, 55)
        };

        // 3. Print the array content to the console
        printPurchases(purchases);

        // 4. Sort an array by the cost
        Arrays.sort(purchases);

        // 5. Print the array content to the console
        printPurchases(purchases);

        // 6. Print the minimum cost of purchase
        System.out.println("Minimum cost = " + purchases[purchases.length - 1]);

        // 7. Find some purchase with cost equaled to 5.00 BYN
        purchaseWithCost(purchases);
    }

    private static void printPurchases(AbstractPurchase[] purchases) {
        for (AbstractPurchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    private static void purchaseWithCost(AbstractPurchase[] purchases) {
        int index = Arrays.binarySearch(purchases, new TransportExpensesPurchase(
                new Product(null, new Byn(500)), 1, 0));

        if (index < 0) {
            System.out.println("Required purchase is not found");
        } else {
            System.out.println("Required purchase is " + purchases[index]);
        }
    }
}
