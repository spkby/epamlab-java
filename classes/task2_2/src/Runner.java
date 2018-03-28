import by.gsu.epamlab.Material;
import by.gsu.epamlab.Subject;

public class Runner {

    public static void main(String[] args) {

        Subject wire = new Subject("Wire",Material.STEEL, 0.03);

        System.out.println("Subject:");
        System.out.println(wire);

        wire.setMaterial(Material.COPPER);

        System.out.printf("The wire mass is %.1f kg.", wire.getMass());
    }
}
