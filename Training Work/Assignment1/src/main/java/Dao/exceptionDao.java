package Dao;

public class exceptionDao extends Exception{

    public exceptionDao(String message) {
        super(message);
    }

    public exceptionDao(String message, Throwable cause) {
        super(message, cause);
        
    }
    
}
