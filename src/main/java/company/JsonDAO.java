package company;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonDAO<T> extends AbstractDAO<T> {

    public JsonDAO(Class<T> type){
        super(type);
    }

    public List<T> read(String filename) throws DAOLayerException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<T> data;
            data = mapper.readValue(new File(filename),
                    TypeFactory.defaultInstance().constructCollectionType(List.class, this.type));
            return data;
        } catch (IOException e){
            throw new DAOLayerException("Couldn't read JSON");
        }
    };

    @Override
    public void write(List data, String filename) throws DAOLayerException {
        try{
            (new ObjectMapper())
                    .configure(SerializationFeature.INDENT_OUTPUT, true)
                    .writeValue(new File(filename), data);
        } catch (IOException e){
            throw new DAOLayerException("Couldn't write JSON");
        }
    }

}
