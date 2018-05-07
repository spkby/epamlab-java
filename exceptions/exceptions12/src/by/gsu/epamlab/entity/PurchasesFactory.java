package by.gsu.epamlab.entity;

import java.util.Scanner;

public class PurchasesFactory {

    private static final String DELIMITER = ";";
    private static final String HYPHEN = "\t->\t";

    public static Purchase getPurchaseFromFactory(Scanner scanner) {

        String line = "";

        Purchase purchase = null;

        try {
            line = scanner.next();
            String[] elements = line.split(DELIMITER);

            if (elements.length > 4 || elements.length < 3) {
                throw new IllegalArgumentException("wrong number of arguments");
            }

            String product;
            int price, number, discount;

            if (elements[0].isEmpty()) {
                throw new IllegalArgumentException("empty name");
            } else {
                product = elements[0];
            }

            if (elements[1].isEmpty()) {
                throw new IllegalArgumentException("empty price");
            } else {
                price = Integer.parseInt(elements[1]);
            }

            if (elements[2].isEmpty()) {
                throw new IllegalArgumentException("empty number");
            } else {
                number = Integer.parseInt(elements[2]);
            }

            if (elements.length == 4) {
                if (elements[3].isEmpty()) {
                    throw new IllegalArgumentException("empty discount");
                } else {
                    discount = Integer.parseInt(elements[3]);
                }
                if(price <= discount){
                    throw new IllegalArgumentException("discount more or equal price");
                }
                purchase = new PriceDiscountPurchase(product, price, number, discount);
            } else {
                purchase = new Purchase(product, price, number);
            }

        } catch (IllegalArgumentException e) {
            System.err.println(line + HYPHEN + e.getMessage());
        }

        return purchase;
    }

}
