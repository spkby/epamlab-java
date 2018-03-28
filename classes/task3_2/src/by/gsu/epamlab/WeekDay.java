package by.gsu.epamlab;

public enum WeekDay {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

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
