package by.gsu.epamlab;

public class Purchase implements Comparable<Purchase> {

    public static final String PRODUCT_NAME = "Milk";
    public static final int PRICE = 115;

    private int number;
    private double percent;
    private WeekDay weekDay;

    public Purchase() {
    }

    public Purchase(int number, double percent, WeekDay weekDay) {
        this.number = number;
        this.percent = percent;
        this.weekDay = weekDay;
    }

    public Purchase(int number, double percent, int weekDay) {
        this(number, percent, WeekDay.valueOf(weekDay));
    }

    public int getCost() {
        double cost = PRICE * number * ((100.0 - percent) / 100.0);
        return (int) (Math.round(cost / 100) * 100);
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
        return number + ";" + percent + ";" + weekDay + ";" + toRublesWithTwoDigit(getCost());
    }

    public static String toRublesWithTwoDigit(int amount) {

        int integer = amount / 100;
        int tenths = (amount % 100) / 10;
        int hundredths = amount % 10;

        return integer + "." + tenths + hundredths;
    }
}
