package by.gsu.epamlab;

import java.util.Comparator;

public class CargoCompare<Cargo> implements Comparator<Cargo> {

    enum CargoType {
        PASSENGER {
            int getRank() {
                return 1;
            }
        },
        CONTAINER {
            int getRank() {
                return 2;
            }
        },
        PLATFORM {
            int getRank() {
                return 3;
            }
        },
        TANK {
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

/*
    private int getRank(Cargo cargo) {
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
*/
}
