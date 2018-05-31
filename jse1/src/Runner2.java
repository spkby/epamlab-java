import by.gsu.epamlab.Constants;
import by.gsu.epamlab.DAO;
import by.gsu.epamlab.beans.MeanMark;
import by.gsu.epamlab.beans.Mark;
import by.gsu.epamlab.loads.LoadFromXML;

import java.util.List;

public class Runner2 {


    public static void main(String[] args) {

        try {
            DAO.buildConnection();

            DAO.prepareDB();

            Mark.setTypeMark(Mark.Type.XML);

            new LoadFromXML().load(Constants.PATH + Constants.FILE_NAME + Constants.EXT_XML);

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
