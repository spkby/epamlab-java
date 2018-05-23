import by.gsu.epamlab.ResultHandler;
import by.gsu.epamlab.Student;

import java.util.List;

public class Runner {

    private static final String PATH = "src/";
    private static final String FILE_NAME = "results";
    private static final String EXT_XSM = ".xml";

    public static void main(String[] args) {

        try {
            ResultHandler parser = new ResultHandler(PATH + FILE_NAME + EXT_XSM);
            List<Student> students = parser.read();

            for (Student student : students) {
                System.out.print(student);
            }

        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }

}
