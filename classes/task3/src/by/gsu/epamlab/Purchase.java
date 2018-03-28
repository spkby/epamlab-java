package by.gsu.epamlab;

public class Purchase implements Comparable<Purchase> {

    public static final String PRODUCT_NAME = "Milk";
    public static final int PRICE = 115;

    private int number;
    private int percent;
    private WeekDay weekDay;

    public Purchase() {
    }

    public Purchase(int number, int percent, WeekDay weekDay) {
        this.number = number;
        this.percent = percent;
        this.weekDay = weekDay;
    }

    public int getCost() {
        return PRICE * number * (100 - percent) / 100;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    @Override
    public int compareTo(Purchase purchase) {
        return this.number - purchase.number;
    }

    @Override
    public String toString() {
        return number + ";" + percent + ";" + weekDay + ";" + toRubles(getCost());
    }

    public static String toRubles(int amount) {

        int integer = amount / 100;
        int tenths = (amount % 100) / 10;
        int hundredths = amount % 10;

        return integer + "." + tenths + hundredths;
    }

    public static String toRublesWithThreeDigit(int amount) {

        int integer = amount / 1000;
        int tenths = (amount % 1000) / 100;
        int hundredths = (amount % 100) / 10;
        int thousandths = amount % 10;

        return integer + "." + tenths + hundredths + thousandths;
    }

    public int getNumber() {
        return number;
    }
}
