package by.gsu.epamlab;

public enum  SolidContent {
    COMPUTER(0.08),
    MOTHERBOARD(0.03),
    VIDEO_CARD(0.02);

    private final double density;

    SolidContent(double density) {
        this.density = density;
    }

    public double getDensity() {
        return density;
    }

    public String getName(){
        return this.name();
    }

    @Override
    public String toString() {
        return getName();
    }
}
