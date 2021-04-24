package company;

public class DAOLayerException extends Exception {
    String problem;
    @Override
    public String getMessage() {
        return problem;
    }

    public DAOLayerException(String message){
        super(message);
        problem = message;
    }
}
