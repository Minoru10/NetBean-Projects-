package vMachineSL;

public class NoItemInventoryException extends Exception {

    public NoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoItemInventoryException(Throwable cause) {
        super(cause);
    }

    public NoItemInventoryException(String message) {
        super(message);
    }
    
}
