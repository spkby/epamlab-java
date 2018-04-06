import by.gsu.epamlab.*;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {

        final Product milk = new Product("Milk", new Byn(234));

        AbstractPurchase[] purchases = new AbstractPurchase[]{
                new PercentDiscountPurchase(milk, 15, 2.56),
                new PercentDiscountPurchase(milk, 8, 4.12),

                new PriceDiscountPurchase(milk, 3, 55),
                new PriceDiscountPurchase(milk, 6, 23),

                new TransportExpensesPurchase(milk, 7, 753),
                new TransportExpensesPurchase(milk, 9, 349)
        };

        printPurchases(purchases);

        Arrays.sort(purchases);

        System.out.println();

        printPurchases(purchases);

        System.out.println();

        System.out.println("Minimum cost = "+purchases[purchases.length-1]);

        System.out.println();

        purchaseWithCost(purchases);


        System.out.println();
        milk.getPrice().mul(2);
        System.out.println(purchases[0]);

    }

    private static void printPurchases(AbstractPurchase[] purchases) {
        for (AbstractPurchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    private static void purchaseWithCost(AbstractPurchase[] purchases) {
        int index = Arrays.binarySearch(purchases, new TransportExpensesPurchase(
                new Product(null,new Byn(500)), 1,0));

        if (index < 0) {
            System.out.println("Required purchase is not found");
        } else {
            System.out.println("Required purchase is " + purchases[index]);
        }
    }
}
