package by.gsu.epamlab.loads;

import by.gsu.epamlab.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoadFromCSV implements Load {

    @Override
    public void load(String fileName) {

        Scanner sc = null;

        try {

            sc = new Scanner(new FileReader(fileName));

            String login;
            String test;
            java.sql.Date date;
            int mark;

            while (sc.hasNext()) {

                String[] elements = sc.next().split(Constants.DELIMITER);

                login = elements[0];
                test = elements[1];
                date = Utils.parseDate(elements[2]);
                mark = (int) (Constants.DECIMAL * Double.parseDouble(elements[3]));

                DAO.add(new Result(login, test, date, mark));
            }

        } catch (IOException | IllegalStateException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
        finally {
            if(sc != null){
                sc.close();
            }
        }
    }
}
