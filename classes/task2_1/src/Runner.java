import by.gsu.epamlab.Material;
import by.gsu.epamlab.Subject;

public class Runner {

    public static void main(String[] args) {

        final Material STEEL = new Material("Steel",7850.0);

        Subject wire = new Subject("Wire",STEEL, 0.03);

        System.out.println("Subject:");
        System.out.println(wire);

        final Material COOPER = new Material("Cooper", 8500.0);
        wire.setMaterial(COOPER);

        System.out.printf("The wire mass is %.1f kg.", wire.getMass());
    }
}
