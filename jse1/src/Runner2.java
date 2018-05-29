import by.gsu.epamlab.DB;
import by.gsu.epamlab.loads.Load;
import by.gsu.epamlab.loads.LoadFromXML;

public class Runner2 {


    public static void main(String[] args) {

        DB.buildConnection();
        DB.prepareDB();


        Load xml = new LoadFromXML();
        xml.load();

        DB.close();
    }
}
