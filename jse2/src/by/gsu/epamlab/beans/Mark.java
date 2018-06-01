package by.gsu.epamlab.beans;

import by.gsu.epamlab.Constants;

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
                stringBuilder.append(mark / Constants.DECIMAL);
                break;
            case XML:
                stringBuilder.append(mark / Constants.DECIMAL).append(".").append(mark % Constants.DECIMAL);
                break;
            case CSV_DBL:
                if (mark % Constants.DECIMAL > 0) {
                    stringBuilder.append(mark / Constants.DECIMAL).append(".").append(mark % Constants.DECIMAL);
                } else stringBuilder.append(mark / Constants.DECIMAL);
                break;
        }
        return stringBuilder.toString();
    }

}
