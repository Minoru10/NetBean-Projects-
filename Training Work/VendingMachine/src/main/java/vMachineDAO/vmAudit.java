package vMachineDAO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class vmAudit implements interfaceAudit{
    
    public static final String AUDIT_FILE = "vmAudit.txt";
    
//Write time and date of all vending to a file
    @Override
    public void writeAuditEntry(String entry) throws AuditException{
        
        PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new AuditException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
        
    }
}
