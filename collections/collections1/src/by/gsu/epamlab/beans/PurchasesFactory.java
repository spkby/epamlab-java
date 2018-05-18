package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.enums.Fields;

public class PurchasesFactory {

    private final static int PURCHASE_FIELDS_NUMBER = Purchase.class.getDeclaredFields().length;
    private final static int DISCOUNT_PURCHASE_FIELDS_NUMBER = PURCHASE_FIELDS_NUMBER
            + PricePurchase.class.getDeclaredFields().length;


    public static Purchase getPurchaseFromFactory(String csvLine) {

        Purchase purchase;
        String[] elements = csvLine.split(Constants.DELIMITER);

        String name = elements[Fields.NAME.ordinal()];
        int price = Integer.parseInt(elements[Fields.PRICE.ordinal()]);
        int number = Integer.parseInt(elements[Fields.NUMBER.ordinal()]);

        if (elements.length == PURCHASE_FIELDS_NUMBER) {
            purchase = new Purchase(name, price, number);
        } else {
            int discount = Integer.parseInt(elements[Fields.DISCOUNT.ordinal()]);
            purchase = new PricePurchase(name, price, number, discount);
        }

        return purchase;
    }
}
