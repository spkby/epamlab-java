import by.gsu.epamlab.PurchasesList;
import by.gsu.epamlab.comparators.ComparatorFactory;

public class Runner {

    public static void main(String[] args) {

        final String inFile = args[0];
        final String addonFile = args[1];
        final String comparatorVersion = args[2];

        PurchasesList listIn = new PurchasesList(inFile);

        System.out.println();
        System.out.println("after creation");
        System.out.println();
        listIn.printList();
        System.out.println();
        System.out.println("before sorting");

        PurchasesList listAddon = new PurchasesList(addonFile);

        listAddon.printList();

        listIn.sort(ComparatorFactory.getComparator(comparatorVersion));

    }
}
