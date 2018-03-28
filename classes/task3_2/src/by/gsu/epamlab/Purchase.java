package by.gsu.epamlab;

public class Purchase implements Comparable<Purchase> {

    public static final String PRODUCT_NAME = "Milk";
    public static final int PRICE = 115;

    private int number;
    private int percent;
    private WeekDay weekDay;

    public Purchase() {}

    public Purchase(int number, int percent, WeekDay weekDay) {
        this.number = number;
        this.percent = percent;
        this.weekDay = weekDay;
    }

    public Purchase(int number, int percent, int weekDay) {
        this(number,percent,WeekDay.valueOf(weekDay));
    }

    public int getCost() {
        return (int) Math.round(PRICE * number * (100.0 - percent) / 100.0);
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
        return number + ";" + percent + ";" + weekDay + ";" + Utils.toRublesWithTwoDigit(getCost());
    }
}
