package by.gsu.epamlab.load;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.beans.Result;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ResultImplCsv implements IResultDAO {

    private final Scanner sc;

    public ResultImplCsv(String fileName) {
        try {
            this.sc = new Scanner(new FileReader(fileName + Constants.EXT_CSV));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Result nextResult() {

        String login, test;
        java.sql.Date date;
        int mark;

        String[] elements = sc.next().split(Constants.DELIMITER);
        try {
            login = elements[0];
            test = elements[1];
            date = java.sql.Date.valueOf(elements[2]);
            mark = (int) (Constants.DECIMAL * Double.parseDouble(elements[3]));
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new IllegalStateException(e);
        }
        return new Result(login, test, date, mark);
    }

    @Override
    public boolean hasResult() {
        return sc.hasNext();
    }

    @Override
    public void closeReader() {
        if (sc != null) {
            sc.close();
        }
    }
}
