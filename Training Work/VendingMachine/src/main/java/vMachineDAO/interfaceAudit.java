package vMachineDAO;

public interface interfaceAudit {
    
    public void writeAuditEntry(String entry) throws AuditException;
    
}
