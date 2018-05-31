import by.gsu.epamlab.Constants;
import by.gsu.epamlab.DAO;
import by.gsu.epamlab.Results;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.beans.Mark;
import by.gsu.epamlab.beans.MeanMark;
import by.gsu.epamlab.loads.Load;
import by.gsu.epamlab.loads.LoadFromCSV;

import java.util.List;

public class Runner1 {

    public static void main(String[] args) {

        try {
            Mark.Type typeMark = Mark.Type.CSV_INT;
            Mark.setTypeMark(typeMark);

            DAO.buildConnection();
            DAO.prepareDB();

            Results.loadFromFile(new LoadFromCSV(), Constants.PATH + Constants.FILE_NAME + typeMark.ordinal() + Constants.EXT_CSV);

            Results.meanMarks();

            List<Result> list = Results.printTestByCurrentMonth();

            Results.printTestsInTheLatestDay(list);

        } catch (IllegalStateException e) {
            System.err.println(e);
        } finally {
            DAO.close();
        }
    }
}
