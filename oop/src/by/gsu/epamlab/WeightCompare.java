package by.gsu.epamlab;

import java.util.Comparator;

public class WeightCompare<Cargo> implements Comparator<Cargo> {

    enum CargoType {
        PASSENGER,
        CONTAINERCARGO,
        PLATFORMCARGO,
        TANKCARGO
    }

    @Override
    public int compare(Cargo a, Cargo b) {

        return CargoType.valueOf(a.getClass().getSimpleName().toUpperCase()).ordinal()
                - CargoType.valueOf(b.getClass().getSimpleName().toUpperCase()).ordinal();
    }
}
