
package Flooring.Mastery.ServiceLayer;


public class NoOrderException extends Exception{
    
    public NoOrderException(String message) {
        super(message);
    }
    
    public NoOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
