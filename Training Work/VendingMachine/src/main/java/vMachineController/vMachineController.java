package vMachineController;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vMachineDAO.AuditException;
import vMachineDTO.Snack;
import vMachineSL.InsufficientFundsException;
import vMachineSL.NoItemInventoryException;
import vMachineSL.vmServiceLayer;
import vMachineView.vMachineView;

@Component
public class vMachineController {
    
    @Autowired
    private vMachineView view;
    @Autowired
    private vmServiceLayer service;
     
    public vMachineController(vMachineView view, vmServiceLayer service) {
        this.view = view;
        this.service = service;
    }
     
     
     int count;
     String cash;
    public void run() throws NoItemInventoryException, InsufficientFundsException, AuditException{
        
        createSnacks(); //Create and add Snacks to machine
        view.displayWelcomeMessage(); //display vending machine welcome message
        displaysnacks(); //display snacks in the machine
        
        boolean keepGoing = true;
        cash = view.getMoney("Enter your money"); //get money from user for vending
        while (keepGoing) {
            
            displaysnacks();
            int selection = view.selection("select a snack to buy from the list. 1=Doritos, 2=Honey Bun, etc...", 1, 4); //Get user selectes snack
            try{
                switch (selection){
                    case 1:
                        vend(cash, selection);
                        break;
                    case 2:
                        vend(cash, selection);
                        break;
                    case 3:
                        vend(cash, selection);
                        break;
                    case 4:
                        vend(cash, selection);
                        break;
                    default: 
                        vend(cash, selection);
                        break;
               }
            }catch (NoItemInventoryException | InsufficientFundsException | AuditException e){
                    view.print(e.getMessage());
                    }
            String exit = view.getInfo("Press y to make another purchase, or Press any other key to EXIT! "); //Loop till user exits
            if (!exit.equals("y"))
                keepGoing = false;
                count = 1; //variable for numbering the items 1-4.
        } 
    }
//Vend a selected snack
    private void vend(String money, int selection) throws NoItemInventoryException, InsufficientFundsException, AuditException{
        Snack choice = service.validateStock(selection);
        service.validateCash(choice, money);
        BigDecimal change = service.vendItem(money, choice);
        view.displayAllSnacks(choice);
        view.print("Your Balance is: " + change.toString());
        view.print(service.convertToCoin(change.toString()));
        if (!service.filteredList(change).isEmpty()){
            view.message();
            displayFilterList(change.toString());
        }
        cash = change.toString();
    }
//create and add snacks to the vending machine   
    private void createSnacks(){
        Snack snack1 = new Snack("Dorito");
        BigDecimal price1 = new BigDecimal("1.00");
        snack1.setCost(price1);
        snack1.setInventory("1");
        service.addSnack(snack1);
        
        Snack snack2 = new Snack("Honey Bun");
        BigDecimal price2 = new BigDecimal("1.50");
        snack2.setCost(price2);
        snack2.setInventory("5");
        service.addSnack(snack2);
        
        Snack snack3 = new Snack("Skittles");
        BigDecimal price3 = new BigDecimal("0.75");
        snack3.setCost(price3);
        snack3.setInventory("5");
        service.addSnack(snack3);
        
        Snack snack4 = new Snack("Pretzils");
        BigDecimal price4 = new BigDecimal("2.15");
        snack4.setCost(price4);
        snack4.setInventory("5");
        service.addSnack(snack4);
    }
// Display all the snacks in the vending machine
    private void displaysnacks(){
        count = 1;
        for (Snack current: service.allSnacks()){
            if (!current.getInventory().equals("0"))
                view.print(count + ". "); 
            view.displayAllSnacks(current);
            count+=1;
        }
    }
// Display a filtered list of snacks within the user price range.
    private void displayFilterList(String cash){
        BigDecimal cashAmount = new BigDecimal(cash);
        for (Snack current: service.filteredList(cashAmount)){
            view.displayAllSnacks(current);
        }
    }
        
}
    