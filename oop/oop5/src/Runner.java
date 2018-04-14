import by.gsu.epamlab.*;
import by.gsu.epamlab.DryContainer;
import by.gsu.epamlab.Platform;
import by.gsu.epamlab.TankContainer;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {

        Transferable[] array = {
                new DryContainer(2400,
                        new UniformStaff("coal", 1450, 20)),
                new Platform(
                        new Staff("tractor MTZ-80", 3600)),
                new TankContainer(8500,
                        new UniformStaff("oil", 730, 28)),
                new Man("Fat man", 120),
                new Man("Pretty woman", 55),
                new Man("Happy baby", 12)};

        System.out.println(Arrays.toString(array));
        Arrays.sort(array, new Transferables.TransferableComparator());
        System.out.println(Arrays.toString(array));
        Ferry ferry = new Ferry(8_000_000, array);
        System.out.println("Can be moved = " + ferry.checkCapacity());
    }
}
