import by.gsu.epamlab.*;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {

        Cargo[] ferry = new Cargo[]{
                new Container(100, 20, 20, 20, 10),
                new Passenger(null, 75),
                new Passenger(null, 68),
                new Tank(100, 20, 20, 10),
                new Passenger(null, 79),
                new Passenger(null, 34),
                new Passenger(null, 56),
                new Passenger(null, 57),
                new Platform(1000),
                new Passenger(null, 37),
                new Passenger(null, 79),
                new Passenger(null, 45)
        };

        printFerry(ferry);

        Arrays.sort(ferry, new CargoCompare<>());
        System.out.println();
        printFerry(ferry);


        int mass = 0;
        for (Cargo cargo : ferry) {
            mass += cargo.getMass();
        }

        if(mass > Ferry.MAX_CAPACITY){
            System.out.println();
            System.out.println("Overloading");
        }

    }

    private static void printFerry(Cargo[] ferry) {
        for (Cargo cargo : ferry) {
            System.out.println(cargo);
        }
    }
}
