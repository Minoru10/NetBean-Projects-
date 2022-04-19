package Service;

public class exceptionInvalidInput extends Exception {

    public exceptionInvalidInput(String message) {
        super(message);
    }

    public exceptionInvalidInput(String message, Throwable cause) {
        super(message, cause);
    }
    
}
