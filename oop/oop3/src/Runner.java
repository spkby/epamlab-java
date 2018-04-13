import by.gsu.epamlab.Cargo.*;
import by.gsu.epamlab.Container.Container;
import by.gsu.epamlab.Container.Сistern;
import by.gsu.epamlab.Ferry;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {

        Ferry ferry = new Ferry(12000000,
                new TransportableObject[]{
                        new ContainerRectangular(140, new Container(12, 4, 7, 600)),
                        new ContainerRectangular(128, new Container(21, 5, 6, 900)),
                        new People(80000000, "Jack Wolf"),
                        new People(100, "Dmitry Zaycev"),
                        new People(160, "Kate Csalkova"),
                        new Platform(2500),
                        new Platform(3200),
                        new CisternCylindrical(243, new Сistern(500, 20, 650)),
                        new CisternCylindrical(243, new Сistern(640, 15, 950)),
                });

        printArray(ferry.getTransportableObjects());

        Arrays.sort(ferry.getTransportableObjects(), new CompareCargo());

        System.out.println();
        printArray(ferry.getTransportableObjects());

        int totalFerryMass = 0;
        for (TransportableObject transportableObject : ferry.getTransportableObjects()){
            totalFerryMass +=transportableObject.getMass();
        }


        if (totalFerryMass > ferry.getBearingСapacity()){
            System.out.println("\nSorry, Jack is very heavy, he must leave us.");
        } else if (totalFerryMass == ferry.getBearingСapacity()){
            System.out.println("\nDo not feed Jack!");
        } else if (totalFerryMass < ferry.getBearingСapacity()){
            System.out.println("\nUnmoor!");
        }

    }

    private static void printArray(Object[] objects){
        for (Object object : objects){
            System.out.println(object);
        }
    }
}
