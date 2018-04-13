package by.gsu.epamlab;

public class Ferry {
    public static final int MAX_CAPACITY = 100000;

    final private Weight[] weights;

    public Ferry(Weight[] weights) {
        this.weights = weights;
    }

    public Weight[] getWeights() {
        return weights;
    }
}

