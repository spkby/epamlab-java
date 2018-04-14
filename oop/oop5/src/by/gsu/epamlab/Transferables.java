package by.gsu.epamlab;

import java.util.Comparator;

public class Transferables {

    public enum TransferableEnum {
        PEOPLE(Man.class),
        DRY_CONTAINER(DryContainer.class),
        PLATFORM(Platform.class),
        TANK_CONTAINER(TankContainer.class);

        private Class<Transferable> cls;

        TransferableEnum(Class cls) {
            this.cls = cls;
        }

        static int compare(Transferable o1, Transferable o2) {
            return Integer.compare(extract(o1).ordinal(), extract(o2).ordinal());
        }

        private static TransferableEnum extract(Transferable obj){
            for (TransferableEnum element : TransferableEnum.values()) {
                if (element.cls == obj.getClass()) {
                    return element;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public static class TransferableComparator implements Comparator<Transferable> {
        @Override
        public int compare(Transferable o1, Transferable o2) {
            if (o1 == o2) {
                return 0;
            } else if (o1 == null) {
                return -1;
            } else if (o2 == null) {
                return 1;
            } else if (o1.getClass() != o2.getClass()) {
                return TransferableEnum.compare(o1, o2);
            } else {
                return Double.compare(o1.getWeight(), o2.getWeight());
            }
        }
    }
}
