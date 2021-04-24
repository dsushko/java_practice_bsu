package services;

public class ServiceLayerException extends Exception {
    String problem;
    @Override
    public String getMessage() {
        return problem;
    }

    public ServiceLayerException(String message){
        super(message);
        problem = message;
    }
}
