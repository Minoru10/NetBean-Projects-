package Flooring.Mastery.View;

import Flooring.Mastery.Dto.Order;
import java.math.BigDecimal;

public class View {
    
    userIO input;// = new userIOImpl();

    public View(userIO input) {
        this.input = input;
    }
    
    public int printMenu() {
        input.print("   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        input.print("   * <<Flooring Program>>");
        input.print("   * 1. Display Orders");
        input.print("   * 2. Add an Order");
        input.print("   * 3. Edit an Order");
        input.print("   * 4. Remove an Order");
        input.print("   * 5. Export All Data");
        input.print("   * 6. Quit");
        input.print("   *");
        input.print("   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        return input.readInt("Please select from the choices above", 1, 6);
    }
    
    public String getStringInput(String prompt){
        return input.readString(prompt);
    }
    public String getDateInput(String prompt) {
        return input.readDate(prompt);
    }
    public int getIntInput(String prompt){
        return input.readInt(prompt);
    }
    public int getIntInput(String prompt, int min, int max){
        return input.readInt(prompt, min, max);
    }
    public BigDecimal getDecimalValue(String prompt){
        return input.readBigDecimal(prompt);
    }
    public BigDecimal getDecimalValueWithinRange(String prompt, int min, int max){
        return input.readBigDecimal(prompt, min, max);
    }
    
    public void printMessage(String message){
        input.print(message);
    }
    public void displayMessage(String prompt){
        input.print(prompt);
    }
    public void displayOrderInfo(Order order) {
        String show = String.format("%s %s %s %s %s %s %s %s %s %s %s %s %s",
                        order.getOrderNumber(),
                        order.getCustomerName(),
                        order.getState(),
                        order.getTaxRate(),
                        order.getProductType(),
                        order.getArea(),
                        order.getCostPerSquareFoot(),
                        order.getLaborCostPerSquareFoot(),
                        order.getMaterialCost(),
                        order.getLaborCost(),
                        order.getTax(),
                        order.getTotal(),
                        order.getDate());
                        
        input.print(show);
    }
    
}
