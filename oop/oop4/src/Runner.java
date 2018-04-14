import by.gsu.epamlab.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        final ContainerTypeTransportation containerStandart = new ContainerTypeTransportation(100, new TareCargo(),5, 6, 3);
        final TankTypeTransportation tankStandart = new TankTypeTransportation(120, new TareCargo(), 5, 3);

        Trip[] trips = {new Passenger("Tom", 80),
                        new Passenger("Tom", 90),
                        new PlatformTypeTransportation(new PieceCargo("Table", 50.5)),
                        new ContainerTypeTransportation(containerStandart, new TareCargo("Macadam 5*20", 1360)),
                        new TankTypeTransportation(tankStandart, new TareCargo("Petrol-92", 746)),
                        new ContainerTypeTransportation(containerStandart, new TareCargo("Macadam 20*40", 1390)),
                        new TankTypeTransportation(tankStandart, new TareCargo("Petrol-95", 750)),
                        new PlatformTypeTransportation(new PieceCargo("Car", 2130)),
                        new Passenger("Jack", 60.8),
        };

        System.out.println("Список, перевозимых людей и грузов");
        print(trips);


        Arrays.sort(trips,new TripCompare<Trip>());
        System.out.println("\nОтсортированный список, перевозимых людей и грузов");
        print(trips);

        Ferry ferry = new Ferry(500000, trips);


        System.out.println(ferry.isTrip() ? "\nПаром с грузоподъемностью " + ferry.getCarrying() + " может перевезти заданные грузы и людей с общим весом " + ferry.sumWeight() :
                                            "\nПаром с грузоподъемностью " + ferry.getCarrying() + " не может перевести заданные грузы и людей с общим весом " + ferry.sumWeight());


    }


    private static void print (Trip [] trips){
        for (Trip trip:trips) {
            System.out.println(trip);

        }

    }
}
