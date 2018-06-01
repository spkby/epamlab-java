package by.gsu.epamlab;

import by.gsu.epamlab.load.IResultDAO;

public class ResultsLoader {

    public static void loadResults(IResultDAO reader) {
        try {
            while (reader.hasResult()) {
                DAO.add(reader.nextResult());
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e);
        } finally {
            reader.closeReader();
        }
    }
}
