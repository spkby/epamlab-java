package by.gsu.epamlab.load;

import by.gsu.epamlab.beans.Result;

public interface IResultDAO {
    Result nextResult();

    boolean hasResult();

    void closeReader();
}
