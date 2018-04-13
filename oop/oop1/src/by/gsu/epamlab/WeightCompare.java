package by.gsu.epamlab;

import java.util.Comparator;

public class WeightCompare<Cargo> implements Comparator<Cargo> {

    enum CargoType {
        PASSENGER {
            int getRank() {
                return 1;
            }
        },
        CONTAINERCARGO {
            int getRank() {
                return 2;
            }
        },
        PLATFORMCARGO {
            int getRank() {
                return 3;
            }
        },
        TANKCARGO {
            int getRank() {
                return 4;
            }
        };

        abstract int getRank();
    }

    @Override
    public int compare(Cargo a, Cargo b) {

        return CargoType.valueOf(a.getClass().getSimpleName().toUpperCase()).getRank()
                - CargoType.valueOf(b.getClass().getSimpleName().toUpperCase()).getRank();
    }
}
