import by.gsu.epamlab.*;
import by.gsu.epamlab.beans.Mark;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.load.ResultImplXml;

import java.util.List;

public class RunnerXml {

    public static void main(String[] args) {

        try {
            Mark.Type typeMark = Mark.Type.XML;
            Mark.setTypeMark(typeMark);
            String fileName = Constants.PATH + Constants.FILE_NAME;

            RunnerLogic.init();

            RunnerLogic.load(new ResultImplXml(fileName));

            RunnerLogic.meanMarks();

            List<Result> list = RunnerLogic.printTestByCurrentMonth();

            RunnerLogic.printTestsInTheLatestDay(list);

        } catch (IllegalStateException e) {
            System.err.println(e);
        } finally {
            DAO.close();
        }
    }
}
