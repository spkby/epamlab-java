import by.gsu.epamlab.Constants;
import by.gsu.epamlab.DAO;
import by.gsu.epamlab.Results;
import by.gsu.epamlab.beans.Mark;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.loads.LoadFromXML;

import java.util.List;

public class Runner2 {

    public static void main(String[] args) {

        try {
            DAO.buildConnection();

            DAO.prepareDB();

            Mark.setTypeMark(Mark.Type.XML);

            Results.loadFromFile(new LoadFromXML(), Constants.PATH + Constants.FILE_NAME + Constants.EXT_XML);

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
