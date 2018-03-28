package by.gsu.epamlab;

public enum WeekDay {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);

    private int value;

    WeekDay(int value) {
        this.value = value;
    }

    public static WeekDay valueOf(int weekDay) {

        WeekDay[] weekDays = new WeekDay[]{
                SUNDAY,
                MONDAY,
                TUESDAY,
                WEDNESDAY,
                THURSDAY,
                FRIDAY,
                SATURDAY};

        return weekDays[weekDay];
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
