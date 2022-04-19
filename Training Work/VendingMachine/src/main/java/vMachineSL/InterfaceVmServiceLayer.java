package vMachineSL;

import java.math.BigDecimal;
import java.util.List;
import vMachineDAO.AuditException;
import vMachineDTO.Snack;

public interface InterfaceVmServiceLayer {
    
    void addSnack(Snack snack);
    
    void validateCash(Snack snack, String cash) throws NoItemInventoryException, InsufficientFundsException, AuditException;
    
    Snack validateStock(int selection) throws NoItemInventoryException, InsufficientFundsException, AuditException;
    
    BigDecimal vendItem(String cash, Snack snack) throws InsufficientFundsException, NoItemInventoryException, AuditException;
    
    public String convertToCoin(String cash) throws InsufficientFundsException, NoItemInventoryException, AuditException;
    
    public List<Snack> filteredList (BigDecimal cash) throws InsufficientFundsException, NoItemInventoryException, AuditException;
    
}
