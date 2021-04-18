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

    public List<T> read(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<T> data;
        data = mapper.readValue(new File(filename),
                TypeFactory.defaultInstance().constructCollectionType(List.class, this.type));
        return data;
    };

    @Override
    public void write(List data, String filename) throws IOException {
            (new ObjectMapper())
                    .configure(SerializationFeature.INDENT_OUTPUT, true)
                    .writeValue(new File(filename), data);
    }

}
