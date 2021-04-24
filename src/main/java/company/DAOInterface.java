package company;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface DAOInterface<T> {
    List<T> read(String filename) throws DAOLayerException;

    void write(List<T> data, String filename) throws DAOLayerException;

}
