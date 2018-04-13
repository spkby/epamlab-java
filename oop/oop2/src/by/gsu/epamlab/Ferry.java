package by.gsu.epamlab;

import java.util.Arrays;

public class Ferry {
    private IMass[] iMasses;
    public static final double CAPASITY=760000000;

    public Ferry(IMass[] iMasses) {
        this.iMasses = iMasses;

    }

    public void setiMasses(IMass[] iMasses) {
        this.iMasses = iMasses;
    }

    public double getTotalMass() {
        double totalMass=0;
        for (IMass mass:iMasses) {
            totalMass+=mass.getMass();
        }
        return totalMass;
    }
    public boolean isTransported(){
        return CAPASITY > getTotalMass();
    }
    public String toString() {
        return Arrays.toString(iMasses)+";"+getTotalMass();
    }
}
