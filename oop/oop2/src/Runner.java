import by.gsu.epamlab.*;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Runner {
    private static void show(IMass[] cargo){
        for (IMass iMass:cargo){
            System.out.println(iMass);}
    }

    public static void main(String[] args) {
       IMass[] cargo = {
               new Passenger("Ivan Ivanov",77), new Passenger("Petr Petrov",98),
               new Cistern(new Fluid(1040,"Oil")),
               new Cistern(new Fluid(710,"Petrol")),
               new Cargo("Meal",77),new Cargo("Sugar",32),
               new Container(new SolidMaterial(78.5, "Steel")),
               new Container(new SolidMaterial(19.32, "Gold"))

       };
       Ferry ferry = new Ferry(cargo);
       System.out.println("The initial array:");
       show(cargo);
       Arrays.sort(cargo,new OrderComparator());
       System.out.println("The modified array:");
       show(cargo);
       System.out.println("Possibility of ferry:"+ferry.isTransported());

    }
}
