import by.gsu.epamlab.Constants;
import by.gsu.epamlab.DAO;
import by.gsu.epamlab.beans.Mark;
import by.gsu.epamlab.beans.MeanMark;
import by.gsu.epamlab.loads.LoadFromCSV;

import java.util.List;

public class Runner1 {

    public static void main(String[] args) {

        try {
            Mark.Type typeMark = Mark.Type.CSV_INT;

            DAO.buildConnection();

            DAO.prepareDB();

            Mark.setTypeMark(typeMark);

            new LoadFromCSV().load(Constants.PATH + Constants.FILE_NAME + typeMark.ordinal() + Constants.EXT_CSV);

            List<MeanMark> meanMarks = DAO.selectMeanMarks();

            for (MeanMark meanMark : meanMarks) {
                System.out.println(meanMark);
            }

        } catch (IllegalStateException e) {
            System.err.println(e);
        } finally {
            DAO.close();
        }
    }
}
