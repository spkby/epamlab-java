public enum Month {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;

    private String getName() {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }

    public String toString() {
        return getName();
    }
}