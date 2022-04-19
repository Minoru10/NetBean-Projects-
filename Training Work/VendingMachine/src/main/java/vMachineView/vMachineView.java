package vMachineView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vMachineDTO.Snack;

@Component
public class vMachineView {
    
    @Autowired
    private userIO in;
    
    public vMachineView(userIO in) {
        this.in = in;
    }
    
//All print and display methods.
    public void print(String msg){
        in.print(msg);
    }
    public String getInfo(String prompt){
        return in.readString(prompt);
    }
    public int selection(String prompt, int min, int max){
        return in.readInt(prompt, min, max);
    }
    public String getMoney(String prompt){
        return String.valueOf(in.readBidDecimal(prompt));
    }
    public void message(){
        in.print("\t==== Snacks you can buy with your remaining change =====");
    }
    
//display for the welcome message 
    public void displayWelcomeMessage(){
        in.print("\t====== Hello There! Welcome to AKA's Vending goods ======\t\t");
        in.print("\t\tNAME      |    PRICE   |   Number_In_Stock");
    }
//Format the display for snacks in the machine
    public void displayAllSnacks(Snack snack){
        int stock = Integer.parseInt(snack.getInventory());
        if (stock != 0){
        String show = String.format("%s      %s       %s\n",
                     "\t\t"+snack.getName(), "\t"+snack.getCost(), "\t"+snack.getInventory());
        in.print(show);
        }
    }
}
