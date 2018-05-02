import by.gsu.epamlab.entity.Purchase;
import by.gsu.epamlab.entity.PurchasesList;
import by.gsu.epamlab.comparators.ComparatorFactory;

public class Runner {

    public static void main(String[] args) {

        final String inFile = args[0];
        final String addonFile = args[1];
        final String comparatorVersion = args[2];

        //create an instance
        PurchasesList listIn = new PurchasesList(inFile);

        //print the collection
        System.out.println();
        System.out.println("after creation");
        System.out.println();
        listIn.printList();

        //create another instance
        PurchasesList listAddon = new PurchasesList(addonFile);

        //insert the last element of the second collection at the position 0 of the first collection;
        listIn.insert(0, listAddon.getPurchaseByIndex(listAddon.size() - 1));

        //insert the initial element of the second collection at the position 1000 of the first collection;
        listIn.insert(1000, listAddon.getPurchaseByIndex(0));

        //insert the element with index 2 of the second collection at the position 2 of the first collection;
        listIn.insert(2, listAddon.getPurchaseByIndex(2));

        //try to delete elements with indices 3, 10 and –5;
        listIn.delete(3);
        listIn.delete(10);
        listIn.delete(-5);

        //print the first
        System.out.println();
        System.out.println("before sorting");
        System.out.println();
        listIn.printList();

        //sort the first collection;
        listIn.sort(ComparatorFactory.getComparator(comparatorVersion));

        //print the first collection
        System.out.println();
        System.out.println("after sorting");
        System.out.println();
        listIn.printList();

        //find the element with index 1 and
        //the element with index 3 of the second collection
        //in the first collection and print obtained results
        System.out.println();
        System.out.println("search results:");
        System.out.println(search(1,listIn,listAddon));
        System.out.println(search(3,listIn,listAddon));

    }

    private static String search(int index, PurchasesList in, PurchasesList addon) {

        int id = in.search(addon.getPurchaseByIndex(index));

        return addon.getPurchaseByIndex(index) + (id >= 0 ? " is found at position " + id : " isn't found");
    }
}
