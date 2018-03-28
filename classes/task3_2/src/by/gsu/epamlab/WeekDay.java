package by.gsu.epamlab;

public enum WeekDay {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(0);

    private int value;

    WeekDay(int value) {
        this.value = value;
    }

    public static WeekDay valueOf(int weekDay) {

        switch (weekDay){
            case 0: return SUNDAY;
            case 1: return MONDAY;
            case 2: return TUESDAY;
            case 3: return WEDNESDAY;
            case 4: return THURSDAY;
            case 5: return FRIDAY;
        }
        return SATURDAY;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
