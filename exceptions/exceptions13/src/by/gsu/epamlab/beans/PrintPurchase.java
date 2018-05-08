package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.enums.TableRows;

import java.util.Formatter;

public class PrintPurchase {

    public static String print(Purchase purchase) {

        Formatter formatter = new Formatter();
        String format = TableRows.NAME.get() +
                TableRows.PRICE.get() +
                TableRows.NUMBER.get() +
                TableRows.DISCOUNT.get() +
                TableRows.COST.get();
        formatter.format(format, purchase.getName(), purchase.getPrice(), purchase.getNumber(),
                getDiscount(purchase), purchase.getCost());
        return formatter.toString();

    }

    private static String getDiscount(Purchase purchase) {
        return purchase.getClass() == PriceDiscountPurchase.class ?
                ((PriceDiscountPurchase) purchase).getDiscount().toString() : Constants.NULL_DISCOUNT;
    }

}
