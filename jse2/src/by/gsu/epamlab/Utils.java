package by.gsu.epamlab;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    public static java.sql.Date parseDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        return sqlStartDate;
    }
}
