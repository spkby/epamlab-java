package by.gsu.epamlab.entity;

import by.gsu.epamlab.exceptions.CsvLineException;
import by.gsu.epamlab.exceptions.Fields;
import by.gsu.epamlab.exceptions.NonpositiveArgumentException;

public class PurchasesFactory {

    private static final String DELIMITER = ";";


    public static Purchase getPurchaseFromFactory(String csvLine) throws CsvLineException {

        Purchase purchase;

        try {
            String[] elements = csvLine.split(DELIMITER);

            if (elements.length > 4 || elements.length < 3) {
                throw new IllegalArgumentException("wrong number of arguments");
            }

            String product;
            int price, number, discount;

            if (elements[0].isEmpty()) {
                throw new CsvLineException(csvLine, "empty " + Fields.NAME.name().toLowerCase());
            } else {
                product = elements[0];
            }

            if (elements[1].isEmpty()) {
                throw new CsvLineException(csvLine, "empty " + Fields.PRICE.name().toLowerCase());
            } else {
                price = Integer.parseInt(elements[1]);
            }

            if (elements[2].isEmpty()) {
                throw new CsvLineException(csvLine, "empty " + Fields.NUMBER.name().toLowerCase());
            } else {
                number = Integer.parseInt(elements[2]);
            }

            if (elements.length == 4) {
                if (elements[3].isEmpty()) {
                    throw new CsvLineException(csvLine, "empty " + Fields.DISCOUNT.name().toLowerCase());
                } else {
                    discount = Integer.parseInt(elements[3]);
                }
                purchase = new PriceDiscountPurchase(product, price, number, discount);
            } else {
                purchase = new Purchase(product, price, number);
            }

        } catch (NonpositiveArgumentException e) {
            throw new CsvLineException(csvLine, e);
        } catch (IllegalArgumentException e) {
            throw new CsvLineException(csvLine, e.getMessage());
        }

        return purchase;
    }

}
