package by.gsu.epamlab;

import java.util.Comparator;

public class OrderComparator implements Comparator<IMass> {
    @Override
    public int compare(IMass obj1, IMass obj2) {
        return obj1.getOrder() - obj2.getOrder();
    }
}
