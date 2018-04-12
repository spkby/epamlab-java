package by.gsu.epamlab;

import java.util.Comparator;

public class CargoCompare<Cargo> implements Comparator<Cargo> {

    @Override
    public int compare(Cargo a, Cargo b) {

        return getCateg(a) - getCateg(b);
    }

    private int getCateg(Cargo cargo) {
        int categ = 0;
        if (cargo instanceof Passenger) {
            categ = 1;
        } else if (cargo instanceof Container) {
            categ = 2;
        } else if (cargo instanceof Platform) {
            categ = 3;
        } else if (cargo instanceof Tank) {
            categ = 4;
        }
        return categ;
    }
}
