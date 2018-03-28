package by.gsu.epamlab;

public class BusinessTrip {

    private static final int RATE = 700;
    private String employee;
    private int transportationExpenses;
    private int days;

    public BusinessTrip() {
    }

    public BusinessTrip(String employee, int transportationExpenses, int days) {
        setTransportationExpenses(transportationExpenses);
        this.employee = employee;
        this.days = days;
    }

    public void setTransportationExpenses(int transportationExpenses) {
        this.transportationExpenses = (transportationExpenses);
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getTotal() {
        return transportationExpenses + RATE * days;
    }

    public void show() {
        System.out.println("rate = " + toRubles(RATE));
        System.out.println("account = " + employee);
        System.out.println("transport = " + toRubles(transportationExpenses));
        System.out.println("days = " + days);
        System.out.println("total = " + toRubles(getTotal()));
    }

    @Override
    public String toString() {
        return employee + ";" + toRubles(transportationExpenses) + ";" + days + ";" + toRubles(getTotal());
    }

    private static String toRubles(int amount) {

        int integer = amount / 100;
        int tenths = (amount % 100) / 10;
        int hundredths = amount % 10;

        return integer + "." + tenths + hundredths;
    }
}