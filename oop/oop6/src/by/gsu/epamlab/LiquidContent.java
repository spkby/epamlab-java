package by.gsu.epamlab;

public enum  LiquidContent {
    DIESEL(0.85),
    GASOLINE(0.75),
    KEROSENE(0.8);

    private final double density;


    LiquidContent(double density) {
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
