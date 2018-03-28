import by.gsu.epamlab.BusinessTrip;

public class Runner {

    public static void main(String[] args) {

        BusinessTrip[] businessTrips = {
                new BusinessTrip("Alexandr Alexandrov", 2301, 3),
                new BusinessTrip("Petr Petrov", 1168, 4),
                null,
                new BusinessTrip("Ivan Ivanov", 2301, 5),
                new BusinessTrip()
        };

        for (BusinessTrip trip : businessTrips) {
            if (trip != null) {
                trip.show();
            }
        }

        if (businessTrips[businessTrips.length - 1] != null) {
            businessTrips[businessTrips.length - 1].setEmployee("Nikolay Nokilaev");
            businessTrips[businessTrips.length - 1].setTransportationExpenses(1365);
            businessTrips[businessTrips.length - 1].setDays(2);
        }

        System.out.println("Duration = " + (businessTrips[0].getDays() + businessTrips[1].getDays()));

        for (BusinessTrip trip : businessTrips) {
            System.out.println(trip);
        }
    }
}
