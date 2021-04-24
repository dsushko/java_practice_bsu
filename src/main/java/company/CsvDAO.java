package company;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CsvDAO<T> extends AbstractDAO<T> {
    private PrintWriter printWriter;
    private Scanner lineScanner;

    public CsvDAO(Class<T> type) {
        super(type);
    }

    private void writeNativeObject(Object object, Class<?> type) {
        printWriter.print(type.getCanonicalName());
        printWriter.print(";");
        printWriter.print(object);
    }

    private Object readNativeObject(Class<?> type) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return type.getConstructor(String.class).newInstance(lineScanner.next());
    }

    private void writeDate(Date date) {
        printWriter.print(Date.class.getCanonicalName());
        printWriter.print(";");
        printWriter.print(date.getTime());
    }

    private Date readDate() {
        return new Date(lineScanner.nextLong());
    }

    private void writeObject(Object object, Class<?> type) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        if (type.getPackage().equals(Integer.class.getPackage())) {
            writeNativeObject(object, type);
            return;
        }
        if (type.equals(Date.class)) {
            writeDate((Date) object);
            return;
        }
        printWriter.print(type.getCanonicalName());
        if (!type.getSuperclass().equals(Object.class)) {
            printWriter.print(";");
            writeObject(object, type.getSuperclass());
        }
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            printWriter.print(";");
            String fieldName = field.getName();
            StringBuilder getterName = new StringBuilder();
            if (field.getType().equals(Boolean.class) || field.getType().getName().equals("boolean")) {
                getterName.append("is");
            } else {
                getterName.append("get");
            }
            getterName.append(Character.toUpperCase(fieldName.charAt(0))).append(fieldName.substring(1));
            Method getter = type.getMethod(getterName.toString());
            Object getterResult = getter.invoke(object);
            writeObject(getterResult, getterResult.getClass());
        }
    }

    private Object readObject(Object object) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> type = Class.forName(lineScanner.next());
        if (type.getPackage().equals(Integer.class.getPackage())) {
            return readNativeObject(type);
        }
        if (type.equals(Date.class)) {
            return readDate();
        }
        if (object == null) {
            object = type.getConstructor().newInstance();
        }
        if (!type.getSuperclass().equals(Object.class)) {
            readObject(object);
        }
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            StringBuilder setterName = new StringBuilder().append("set")
                    .append(Character.toUpperCase(fieldName.charAt(0)))
                    .append(fieldName.substring(1));
            Method setter = type.getMethod(setterName.toString(), field.getType());
            setter.invoke(object, readObject(null));
        }
        return object;
    }

    @Override
    public void write(List<T> list, String filename) throws DAOLayerException {
        try {
            printWriter = new PrintWriter(filename);
            for (T object : list) {
                writeObject(object, object.getClass());
                printWriter.print("\n");
            }
            printWriter.close();
        } catch (Exception e){
            throw new DAOLayerException("Couldn't write CSV");
        }
    }

    @Override
    public List<T> read(String filename) throws  DAOLayerException {
        try{
        List<T> list = new ArrayList<>();
        Scanner scanner = new Scanner(filename);
        while (scanner.hasNextLine()) {
            lineScanner = new Scanner(scanner.nextLine()).useDelimiter(";");
            list.add((T) readObject(null));
        }
        return list;
        } catch (Exception e){
            throw new DAOLayerException("Couldn't read CSV");
        }
    }
}

