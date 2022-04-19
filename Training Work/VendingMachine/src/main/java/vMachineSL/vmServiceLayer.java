package vMachineSL;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vMachineDAO.AuditException;
import vMachineDAO.interfaceAudit;
import vMachineDAO.vmDaoInterface;
import vMachineDTO.Coin;
import vMachineDTO.Snack;

@Component
public class vmServiceLayer implements InterfaceVmServiceLayer {
    
    @Autowired
    private vmDaoInterface Dao;
    @Autowired
    private interfaceAudit Audit;
    
    public vmServiceLayer(vmDaoInterface Dao, interfaceAudit Audit) {
        this.Dao = Dao;
        this.Audit = Audit;
    }
    
//Check that snack selected is in stock and return that snack.
    @Override
    public Snack validateStock(int selectedSnack) throws NoItemInventoryException {
        Snack current = Dao.allSnacks().get(selectedSnack-1);
        int stock = Integer.parseInt(current.getInventory());
        if (stock == 0)
            throw new NoItemInventoryException ("Error NoItemInventoryException: ITEM NOT IN STOCK");
        return current;
    }
//Check that the user has enough money to buy the snack
    @Override
    public void validateCash(Snack snack, String cash)throws InsufficientFundsException{
        BigDecimal money = new BigDecimal(cash);
        if (snack.getCost().compareTo(money) > 0)
            throw new InsufficientFundsException("Error InsufficientFundsException: NOT ENOUGH MONEY TO PURCHACE ITEM\n" + cash);
    }
//Vends the users selected item and returns the users remaining balance
    @Override
    public BigDecimal vendItem(String cash, Snack snack) throws InsufficientFundsException, NoItemInventoryException, AuditException{
        BigDecimal balance = new BigDecimal(cash);
        balance = balance.subtract(snack.getCost());
        int stock = Integer.parseInt(snack.getInventory());
        stock -= 1;
        String newStock = Integer.toString(stock);
        snack.setInventory(newStock);
        Audit.writeAuditEntry("Snack " + snack.getName() + " was vended");
        return balance;
    }
//Converts the users balance to coins using enum class.
    @Override
    public String convertToCoin(String cash){
        int quater=0, dime=0, nickle=0, penny=0;
        
        BigDecimal m = new BigDecimal(cash);
        BigDecimal contant = new BigDecimal("100");
        m = m.multiply(contant);
        
        Coin q = Coin.QUARTER;  Coin d = Coin.DIME;
        Coin n = Coin.NICKLE;   Coin p = Coin.PENNY;
        
        while (m.subtract(q.getValue()).intValue() > 0){
            m = m.subtract(q.getValue()).setScale(2, RoundingMode.HALF_UP); 
            quater+=1;
        }
        while (m.subtract(d.getValue()).intValue() > 0){
            m = m.subtract(d.getValue()).setScale(2, RoundingMode.HALF_UP);
            
            dime+=1;
        }
        while (m.subtract(n.getValue()).intValue() > 0){
            m = m.subtract(n.getValue()).setScale(2, RoundingMode.HALF_UP);
            nickle+=1;
        }
        while (m.subtract(p.getValue()).intValue() > 0){
            m = m.subtract(p.getValue()).setScale(2, RoundingMode.HALF_UP);
            penny+=1;
        }
        return ""+ quater + "quater(s) | " + dime + "dime(s) | " + nickle + "nickle(s) | " + penny + "penny(ies)\n";
    }
// return a list of snacks that cost less than what the user entered. uses lambda
    @Override
    public List<Snack> filteredList (BigDecimal cash){
        List<Snack> newL = Dao.allSnacks().stream()
                           .filter(L -> L.getCost().compareTo(cash) < 0)
                           .collect(Collectors.toList());
        return newL; 
    }
//Adds a snack to the snack list
    //@Override
    public void addSnack(Snack snack){
        Dao.addSnack(snack);
    }
//Get all snacks from the snack list
    public List<Snack> allSnacks(){
        return Dao.allSnacks();
    }
}
