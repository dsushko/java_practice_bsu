package company;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlDAO<T> extends AbstractDAO<T> {

    public XmlDAO(Class<T> type){
        super(type);
    }

    public List<T> read(String filename) throws DAOLayerException {
        try {
            XmlMapper mapper = new XmlMapper();
            List<T> data;
            data = mapper.readValue(new File(filename),
                    TypeFactory.defaultInstance().constructCollectionType(List.class, this.type));
            return data;
        } catch (IOException e){
            throw new DAOLayerException("Couldn't read XML");
        }
    };

    @Override
    public void write(List data, String filename) throws DAOLayerException {
        try {
            (new XmlMapper())
                    .configure(SerializationFeature.INDENT_OUTPUT, true)
                    .writeValue(new File(filename), data);
        } catch (IOException e){
            throw new DAOLayerException("Couldn't write XML");
        }
    }

}
