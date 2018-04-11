import by.gsu.epamlab.*;

public class Runner {

    public static void main(String[] args) {

        Cargo[] ferry = new Cargo[]{
                new Passenger(null,75),
                new Passenger(null,68),
                new Passenger(null,79),
                new Passenger(null,34),
                new Passenger(null,56),
                new Passenger(null,57),
                new Passenger(null,37),
                new Passenger(null,79),
                new Passenger(null,45),
                new Container(100,20,20,20,10),
                new Tank(100,20,20,10),
                new Platform(1000)
        };

        printFerry(ferry);
        
    }

    private static void printFerry(Cargo[] ferry){
        for (Cargo cargo:ferry){
            System.out.println(cargo);
        }
    }
}
