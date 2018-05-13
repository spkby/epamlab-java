package by.gsu.epamlab;

import java.util.Comparator;

public class LenComparator implements Comparator<LenNum> {

    @Override
    public int compare(LenNum o1, LenNum o2) {
        return o1.getLen() - o2.getLen();
    }
}
