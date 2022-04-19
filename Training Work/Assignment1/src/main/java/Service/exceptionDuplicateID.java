package Service;

public class exceptionDuplicateID extends Exception{

    public exceptionDuplicateID(String message) {
        super(message);
    }

    public exceptionDuplicateID(String message, Throwable cause) {
        super(message, cause);
    }
    
}
