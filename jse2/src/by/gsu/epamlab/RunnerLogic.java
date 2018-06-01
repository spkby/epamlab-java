package by.gsu.epamlab;

import by.gsu.epamlab.beans.MeanMark;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.load.IResultDAO;

import java.util.List;

public class RunnerLogic {

    public static void init() {
        DAO.buildConnection();
        DAO.prepareDB();
    }

    public static void load(IResultDAO res) {
        ResultsLoader.loadResults(res);
    }

    public static void meanMarks() {
        try {
            List<MeanMark> meanMarks = DAO.selectMeanMarks();
            System.out.println("Mean marks");
            for (MeanMark meanMark : meanMarks) {
                System.out.println(meanMark);
            }
            System.out.println();
        } catch (IllegalStateException e) {
            System.err.println(e);
        }
    }

    public static List<Result> printTestByCurrentMonth() {
        List<Result> list;

        try {
            list = DAO.selectByMonthYear();

            System.out.println("Tests results for the current month sorting by a date ascending");
            for (Result result : list) {
                System.out.println(result);
            }
            System.out.println();
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e);
        }

        return list;
    }

    public static void printTestsInTheLatestDay(List<Result> list) {
        if (list == null || list.size() == 0) return;

        java.sql.Date lastDate = list.get(list.size() - 1).getDate();

        System.out.println("Tests results in the latest day of the current month");
        for (int i = list.size(); i > 0; i--) {
            if (lastDate.equals(list.get(i - 1).getDate())) {
                System.out.println(list.get(i - 1));
            } else {
                return;
            }
        }
    }

}
