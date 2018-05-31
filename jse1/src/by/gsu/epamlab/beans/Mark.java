package by.gsu.epamlab.beans;

public class Mark {

    private Mark() {
    }

    private static Type typePrn;

    public enum Type {
        XML, CSV_INT, CSV_DBL
    }

    public static void setTypeMark(Type type) {
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
