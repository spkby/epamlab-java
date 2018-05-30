package by.gsu.epamlab;

public class PrintMark {

    private PrintMark() {
    }

    private static Type typePrn;

    public enum Type {
        CSV_INT, XML, CSV_DBL
    }

    public static void setPrintType(Type type) {
        if (typePrn != null) {
            return;
        }
        typePrn = type;
    }

    public static String print(int mark) {
        StringBuilder stringBuilder = new StringBuilder();

        switch (typePrn) {
            case CSV_INT:
                stringBuilder.append(mark);
                break;
            case XML:
                stringBuilder.append(mark / 10).append(".").append(mark % 10);
            case CSV_DBL:
                if (mark % 10 > 0) {
                    stringBuilder.append(mark / 10).append(".").append(mark % 10);
                } else stringBuilder.append(mark / 10);
        }
        return stringBuilder.toString();
    }

}
