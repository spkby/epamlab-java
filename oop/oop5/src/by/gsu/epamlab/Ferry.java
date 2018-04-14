package by.gsu.epamlab;

public class Ferry {

    private double capacity;
    private Transferable[] content;

    public Ferry(double capacity, Transferable[] content) {
        this.capacity = capacity;
        this.content = content;
    }

    public boolean checkCapacity() {
        double totalWeight = 0;
        for (Transferable el : content) {
            totalWeight += el.getWeight();
        }
        return totalWeight <= capacity;
    }
}
