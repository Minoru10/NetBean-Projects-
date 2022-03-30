package Flooring.Mastery.View;

import Flooring.Mastery.Dto.Order;
import java.math.BigDecimal;

public class View {
    
    userIO input = new userIOImpl();
    
    public String getStringInput(String prompt){
        return input.readString(prompt);
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
        String show = String.format("%s %s %s %s %s %s %s %s %s %s %s",
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
                        order.getTotal() );
                        
        input.print(show);
    }
}
