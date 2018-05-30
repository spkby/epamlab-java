import by.gsu.epamlab.DAO;
import by.gsu.epamlab.MeanMark;
import by.gsu.epamlab.PrintMark;
import by.gsu.epamlab.loads.Load;
import by.gsu.epamlab.loads.LoadFromXML;

import java.util.List;

public class Runner2 {


    public static void main(String[] args) {

        DAO.buildConnection();

        DAO.prepareDB();

        PrintMark.setPrintType(PrintMark.Type.XML);

        Load xml = new LoadFromXML();
        xml.load();

        List<MeanMark> meanMarks = DAO.selectMeanMarks();

        for (MeanMark meanMark:meanMarks){
            System.out.println(meanMark);
        }

        DAO.close();
    }
}
