package vMachineSL;

public class InsufficientFundsException extends Exception{

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientFundsException(Throwable cause) {
        super(cause);
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
    
}
