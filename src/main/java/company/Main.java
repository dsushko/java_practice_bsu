package company;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {

        DAOInterface<RockSong> json = (DAOInterface<RockSong>) Proxy.newProxyInstance(JsonDAO.class.getClassLoader(),
                new Class[]{
                        DAOInterface.class
            }, new LoggingProxyHandler<>(new JsonDAO<>(RockSong.class)));

        DAOInterface<RockSong> xml = (DAOInterface<RockSong>) Proxy.newProxyInstance(XmlDAO.class.getClassLoader(),
                new Class[]{
                        DAOInterface.class
                }, new LoggingProxyHandler<>(new JsonDAO<>(RockSong.class)));

        DAOInterface<RockSong> csv = (DAOInterface<RockSong>) Proxy.newProxyInstance(CsvDAO.class.getClassLoader(),
                new Class[]{
                        DAOInterface.class
                }, new LoggingProxyHandler<>(new JsonDAO<>(RockSong.class)));

        List<RockSong> list = json.read("output.json");

        json.write(list, "output1.json");
        xml.write(list, "output1.xml");
        csv.write(list, "output1.csv");

    }
}
