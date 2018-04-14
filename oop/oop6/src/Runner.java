import by.gsu.epamlab.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {

        String cargoName1 = "Moto";
        double cargoWeight1 = 0.5;
        String cargoName2 = "Auto";
        double cargoWeight2 = 2.3;
        String cargoName3 = "Bus";
        double cargoWeight3 = 5.8;

        ContainerCargo container1 = new ContainerCargo(SolidContent.COMPUTER, ContainerKid.FOOT_20);
        ContainerCargo container2 = new ContainerCargo(SolidContent.MOTHERBOARD, ContainerKid.FOOT_40);
        ContainerCargo container3 = new ContainerCargo(SolidContent.VIDEO_CARD, ContainerKid.FOOT_45);
        CisternCargo cistern1 = new CisternCargo(LiquidContent.DIESEL, CisternKid.MODEL_15_144);
        CisternCargo cistern2 = new CisternCargo(LiquidContent.GASOLINE, CisternKid.MODEL_15_156);
        CisternCargo cistern3 = new CisternCargo(LiquidContent.KEROSENE, CisternKid.MODEL_15_811);
        PlatformCargo platform1 = new PlatformCargo(cargoName1, cargoWeight1);
        PlatformCargo platform2 = new PlatformCargo(cargoName2, cargoWeight2);
        PlatformCargo platform3 = new PlatformCargo(cargoName3, cargoWeight3);
        Person person1 = new Person("Mike", 0.08);
        Person person2 = new Person("Fred", 0.11);
        Person person3 = new Person("Jes", 0.05);

        Trip[] trips = {container1, container2, container3,
                        cistern1, cistern2, cistern3,
                        platform1, platform2, platform3,
                        person1, person2, person3};

        printInfo(trips);
        System.out.println("***********************************");

        Ferry ferry = new Ferry(trips);
        System.out.println("ferry can transfer all cargo and people? " + ferry.isCarry());
        System.out.println("***********************************");

        Arrays.sort(trips, new TripCompare());
        printInfo(trips);



    }
    public static void printInfo(Trip[] trips){
        for(Trip trip: trips){
            System.out.println(trip);
        }

    }
}
