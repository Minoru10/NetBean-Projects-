package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaoImpl implements DaoInterface {

    List<Order> orderList = new ArrayList<>();
    
    @Override
    public void addOrder(Order order) throws FileNotFoundException{
        readOrderFile(order.getDate());
        orderList.add(order);
        writeToDvdFile(order.getDate());
    }
    @Override
    public Order getOrder(String orderDate, int orderNumber) {
        Order order = null;
        for (Order current: orderList){
            if (current.getDate().equals(orderDate) && current.getOrderNumber() == orderNumber)
                order = current;
        }
        return order;
    }
    @Override
    public void removeOrder(Order order)throws FileNotFoundException {
        readOrderFile(order.getDate());
        orderList.remove(order);
        writeToDvdFile(order.getDate());
    }
    @Override
    public List<Order> orderList(String Date) throws FileNotFoundException{
        readOrderFile(Date);
        return orderList;
    }
    
    public static final String DELIMITER = ",";
    
    //This reads a file and stores each line as a DVD.
    private Order unmarshalTxt(String Text){
        
        String[] dividedTxt = Text.split(DELIMITER); //Array which stores the strings seperated by "::"
        String orderID = dividedTxt[0]; //Saves the first index value in the array as the DVD ID
        Order orderFromFile = new Order(Integer.parseInt(orderID)); //Since we have an ID, we can now create a DVD
        
        //These lines set the different attributes of our new DVD using the rest of the values in the array of strings from the file
        orderFromFile.setCustomerName(dividedTxt[1]);
        orderFromFile.setState(dividedTxt[2]);
        orderFromFile.setTaxRate(dividedTxt[3]);
        orderFromFile.setProductType(dividedTxt[4]);
        orderFromFile.setArea(dividedTxt[5]);
        orderFromFile.setCostPerSquareFoot(dividedTxt[6]);
        orderFromFile.setLaborCostPerSquareFoot(dividedTxt[7]);
        orderFromFile.setMaterialCost(dividedTxt[8]);
        orderFromFile.setLaborCost(dividedTxt[9]);
        orderFromFile.setTax(dividedTxt[10]);
        orderFromFile.setTotal(dividedTxt[11]);
        
        return orderFromFile; //returns the new DVD
    }
    private void readOrderFile(String Date) throws FileNotFoundException {
        
        Scanner scanner = null;
        
        try {
            scanner = new Scanner (new BufferedReader(new FileReader("Orders_"+Date+".txt") ) );
        } catch (FileNotFoundException e) {
            e.addSuppressed(e);
        }
        
        String currentLine; //store each file line as a string
        Order currentOrder; //each line in the file transelates to a DVD. So we need to declare an empty DVD variable.
        orderList.removeAll(orderList); //clear the list, we dont want to add DVDs comming from the file with already existing DVDs in the list.
        while(scanner.hasNext()){ //loops through the entire file to read all lines
            currentLine = scanner.nextLine(); //stores the line file is currently reading
            currentOrder = unmarshalTxt(currentLine); //uses the unmarshal method to make sense of the line and create a new DVD
            orderList.add(currentOrder); //Add that DVD to a list
        }
        scanner.close();
    }
    private String marshalTxt(Order order){
        
        String OrderTxt = order.getOrderNumber() + DELIMITER;  //Get the current dvd id and concactinate with a delimiter(::).
        
        //Adds the rest of the dvd attributes to our first string that has the dvd id and the delimiter(::)
        OrderTxt += order.getCustomerName() + DELIMITER;
        OrderTxt += order.getState() + DELIMITER;
        OrderTxt += order.getTaxRate() + DELIMITER;
        OrderTxt += order.getProductType() + DELIMITER;
        OrderTxt += order.getArea() + DELIMITER;
        OrderTxt += order.getCostPerSquareFoot() + DELIMITER;
        OrderTxt += order.getLaborCostPerSquareFoot() + DELIMITER;
        OrderTxt += order.getMaterialCost() + DELIMITER;
        OrderTxt += order.getLaborCost() + DELIMITER;
        OrderTxt += order.getTax() + DELIMITER;
        OrderTxt += order.getTotal() + DELIMITER;
        
        return OrderTxt; //returns an entire string of text holding all attributes of a dvd
    }
    private void writeToDvdFile(String Date) throws FileNotFoundException {
        
        //Find the file and enter into it
        PrintWriter out = null;
        try{
            out = new PrintWriter( new FileWriter("Orders_"+Date+".txt"));
        } catch (IOException e) {
            e.addSuppressed(e);
        }
        
        String OrderAsTxt;
        for (Order current: orderList){ // for every dvd in our list
            OrderAsTxt = marshalTxt(current); //format each dvd with the delimiters(::) using the marshal method and save is in the string variable
            out.println(OrderAsTxt); //print that string variable to the file
            out.flush();
        }
        
        out.close();
    }
}
