import by.gsu.epamlab.Byn;
import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        final String FILE_PATH = "src/in.txt";
        final int PURCHASES_NUMBER = 6;

        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(FILE_PATH));
            sc.useLocale(Locale.ENGLISH);

            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];

            int maxId = 0;
            Byn maxCost = new Byn(0);
            boolean areEqual = true;

            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(sc);
                System.out.println(purchases[i]);
                Byn cost = purchases[i].getCost();
                if (maxCost.compareTo(cost) < 0) {
                    maxCost = cost;
                    maxId = i;
                }

                if (areEqual) {
                    areEqual = purchases[i].equals(purchases[0]);
                }
            }

            System.out.println("The purchase with maximum cost: " + purchases[maxId]);

            if (areEqual) {
                System.out.println("Whether all purchases are equal");
            } else {
                System.out.println("Not all purchases are equal");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
