package Flooring.Mastery.View;

import java.math.BigDecimal;

public interface userIO {
    
    void print(String msg);
    
    String readString(String prompt);
    
    int readInt(String prompt);

    int readInt(String prompt, int min, int max);
    
    BigDecimal readBigDecimal(String prompt);
    
    BigDecimal readBigDecimal(String prompt, int min, int max);
    
    String readDate (String prompt);
    
    String readFutureDate(String prompt);
    
}
