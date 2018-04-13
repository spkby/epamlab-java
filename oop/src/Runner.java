import by.gsu.epamlab.*;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {

        Ferry ferry = new Ferry(new Weight[]{
                new ContainerCargo(100, 20, 20, 20, new Material("steel", 1234)),
                new Passenger(null, 75),
                new Passenger(null, 68),
                new TankCargo(100, 20, 20, new Material("Oil", 234)),
                new Passenger(null, 79),
                new Passenger(null, 56),
                new Passenger(null, 57),
                new PlatformCargo(1000),
                new Passenger(null, 79),
                new Passenger(null, 45)
        });

        printFerry(ferry.getWeights());

        Arrays.sort(ferry.getWeights(), new WeightCompare<>());
        System.out.println();
        printFerry(ferry.getWeights());

        int totalWeight = 0;
        for (Weight weight : ferry.getWeights()) {
            totalWeight += weight.getWeight();
        }

        if (totalWeight > Ferry.MAX_CAPACITY) {
            System.out.println();
            System.out.println("Overloading");
        }
    }

    private static void printFerry(Weight[] weights) {
        for (Weight weight : weights) {
            System.out.println(weight);
        }
    }
}
