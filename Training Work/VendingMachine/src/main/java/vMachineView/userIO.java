package vMachineView;

import java.math.BigDecimal;

public interface userIO {
    
    void print(String msg);
    
    BigDecimal readBidDecimal(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    String readString(String prompt);
}
