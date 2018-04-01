import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        final String FILE_PATH = "src/in.txt";

        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(FILE_PATH));
            sc.useLocale(Locale.ENGLISH);

            Purchase[] purchases = new Purchase[6];

            int maxId = 0;
            int maxCost = 0;
            boolean isEquals = true;

            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(sc);
                System.out.println(purchases[i]);
                int cost = purchases[i].getCost().getAmount();
                if (maxCost < cost) {
                    maxCost = cost;
                    maxId = i;
                }
                if (!purchases[i].equals(purchases[0])) {
                    isEquals = false;
                }
            }

            System.out.println("The purchase with maximum cost: " + purchases[maxId]);

            if (isEquals) {
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
