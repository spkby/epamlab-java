import by.gsu.epamlab.Constants;
import by.gsu.epamlab.beans.Purchase;
import by.gsu.epamlab.beans.PurchasesList;
import by.gsu.epamlab.comparators.PurchaseComparatorBuilder;

public class Runner {

    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("command line: java Runner main additional comparator");
        } else {

            final int MAIN = 0;
            final int ADDITIONAL = 1;
            final int COMPARATOR = 2;

            final String inFile = args[MAIN];
            final String addonFile = args[ADDITIONAL];

            //create an instance
            PurchaseComparatorBuilder.buildPurchaseComparator(args[COMPARATOR]);
            final PurchasesList mainList = new PurchasesList(inFile);

            //print the collection
            printList(mainList, "after creation");

            //create another instance
            final PurchasesList addonList = new PurchasesList(addonFile);

            //insert the last element of the second collection at the position 0 of the first collection;
            mainList.insert(0, addonList.getPurchaseByIndex(addonList.size() - 1));

            //insert the initial element of the second collection at the position 1000 of the first collection;
            mainList.insert(1000, addonList.getPurchaseByIndex(0));

            //insert the element with index 2 of the second collection at the position 2 of the first collection;
            mainList.insert(2, addonList.getPurchaseByIndex(2));

            //try to delete elements with indices 3, 10 and –5;
            if (mainList.delete(3) < 0) {
                printErr("Error Deletion: invalid index");
            }
            if (mainList.delete(10) < 0) {
                printErr("Error Deletion: invalid index");
            }
            if (mainList.delete(-5) < 0) {
                printErr("Error Deletion: invalid index");
            }

            //print the first
            printList(mainList, "before sorting");

            //sort the first collection;
            mainList.sort();

            //print the first collection
            printList(mainList, "after sorting");

            //find the element with index 1 and
            //the element with index 3 of the second collection
            //in the first collection and print obtained results
            search(mainList, addonList, 1);
            search(mainList, addonList, 3);
        }
    }

    private static void printList(PurchasesList list, String header) {
        System.out.println(header);
        System.out.println(list.toTable());
        System.out.println();
    }

    private static void search(PurchasesList in, PurchasesList addon, int index) {
        Purchase purchase = addon.getPurchaseByIndex(index);
        int id = in.search(purchase);
        System.out.println("Purchase " + purchase + (id >= 0 ? " is found at position " + id : " isn't found"));
    }

    private static void printErr(String str) {
        System.err.println(str);
    }
}
