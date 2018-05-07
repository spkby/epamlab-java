import by.gsu.epamlab.entity.Purchase;
import by.gsu.epamlab.entity.PurchasesList;
import by.gsu.epamlab.comparators.PurchaseComparatorBuilder;

public class Runner {
    private static final String NEW_LINE = System.getProperty("line.separator");

    public static void main(String[] args) {

        if (args.length < 3) {
            System.err.println("Error arguments");
            return;
        }

        final String inFile = args[0];
        final String addonFile = args[1];

        //create an instance
        PurchaseComparatorBuilder.buildPurchaseComparator(args[2]);
        PurchasesList listIn = new PurchasesList(inFile);

        //print the collection
        System.out.println();
        System.out.println("after creation" + NEW_LINE);
        System.out.println(listIn.toTable());

        //create another instance
        PurchasesList listAddon = new PurchasesList(addonFile);

        //insert the last element of the second collection at the position 0 of the first collection;
        listIn.insert(0, listAddon.getPurchaseByIndex(listAddon.size() - 1));

        //insert the initial element of the second collection at the position 1000 of the first collection;
        listIn.insert(1000, listAddon.getPurchaseByIndex(0));

        //insert the element with index 2 of the second collection at the position 2 of the first collection;
        listIn.insert(2, listAddon.getPurchaseByIndex(2));

        //try to delete elements with indices 3, 10 and –5;
        if (listIn.delete(3) < 0) {
            printErr("Error Deletion: invalid index");
        }
        if (listIn.delete(10) < 0) {
            printErr("Error Deletion: invalid index");
        }
        if (listIn.delete(-5) < 0) {
            printErr("Error Deletion: invalid index");
        }

        //print the first
        System.out.println("before sorting" + NEW_LINE);
        System.out.println(listIn.toTable());

        //sort the first collection;
        listIn.sort();

        //print the first collection
        System.out.println("after sorting" + NEW_LINE);
        System.out.println(listIn.toTable());

        //find the element with index 1 and
        //the element with index 3 of the second collection
        //in the first collection and print obtained results
        System.out.println("search results:");
        System.out.println(search(1, listIn, listAddon));
        System.out.println(search(3, listIn, listAddon));
    }

    private static String search(int index, PurchasesList in, PurchasesList addon) {
        Purchase purchase = addon.getPurchaseByIndex(index);
        int id = in.search(purchase);
        return purchase + (id >= 0 ? " is found at position " + id : " isn't found");
    }

    private static void printErr(String str) {
        System.err.println(str);
    }
}
