package by.gsu.epamlab;

import by.gsu.epamlab.beans.MeanMark;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.loads.Load;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class Results {

    public static void loadFromFile(Load load, String fileName) {
        load.load(fileName);
    }

    public static void meanMarks() {
        List<MeanMark> meanMarks = DAO.selectMeanMarks();

        System.out.println("Mean marks");
        for (MeanMark meanMark : meanMarks) {
            System.out.println(meanMark);
        }
        System.out.println();
    }

    public static List<Result> printTestByCurrentMonth() {
        List<Result> list = DAO.selectByMonthYear();

        System.out.println("Tests results for the current month sorting by a date ascending");
        for (Result result : list) {
            System.out.println(result);
        }
        System.out.println();

        return list;
    }

    public static void printTestsInTheLatestDay(List<Result> list) {
        Calendar day1 = Calendar.getInstance();
        Calendar day2 = Calendar.getInstance();
        day1.setTime(list.get(list.size() - 1).getDate());

        System.out.println("Tests results in the latest day of the current month");
        for (int i = list.size(); i > 0; i--) {

            day2.setTime(list.get(i - 1).getDate());

            boolean sameDay = day1.get(Calendar.YEAR) == day2.get(Calendar.YEAR) &&
                    day1.get(Calendar.DAY_OF_YEAR) == day2.get(Calendar.DAY_OF_YEAR);

            if (sameDay) {
                System.out.println(list.get(i - 1));
            } else {
                return;
            }
        }
    }


}
