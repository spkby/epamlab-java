package by.gsu.epamlab;

public class Utils {

    public static String toRublesWithTwoDigit(int amount) {

        int integer = amount / 100;
        int tenths = (amount % 100) / 10;
        int hundredths = amount % 10;

        return integer + "." + tenths + hundredths;
    }

    public static String toRublesWithThreeDigit(double amount) {

        int cost = (int) (amount * 10.0);

        int integer = cost / 1000;
        int tenths = (cost % 1000) / 100;
        int hundredths = (cost % 100) / 10;
        int thousandths = cost % 10;

        return integer + "." + tenths + hundredths + thousandths;
    }

}
