package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Order;
import Flooring.Mastery.ServiceLayer.ValidationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDaoImpl implements OrderDoa {

    List<Order> orderList = new ArrayList<>();
    
    @Override
    public void addOrder(Order order) throws ValidationException, PersistanceException{
        //readOrderFile(order.getDate());
        orderList.add(order);
        writeToOrderFile(order.getDate());
    }
    @Override
    public void removeOrder(Order order, String Date)throws ValidationException, PersistanceException{
        //readOrderFile(Date);
        orderList.remove(order);
        writeToOrderFile(Date);
    }
    @Override
    public List<Order> ListAllOrders(String Date) throws ValidationException, PersistanceException{
        readOrderFile(Date);
        return orderList;
    }
    public void editAndSave(Order newOrder) throws PersistanceException {
        writeToOrderFile(newOrder.getDate());
    }
    
    
    public static final String DELIMITER = ",";
    private Order unmarshalTxt(String Text){
        
        String[] dividedTxt = Text.split(DELIMITER);
        String orderID = dividedTxt[0];
        Order orderFromFile = new Order(Integer.parseInt(orderID));
        
        orderFromFile.setCustomerName(dividedTxt[1]);
        orderFromFile.setState(dividedTxt[2]);
        orderFromFile.setTaxRate(new BigDecimal(dividedTxt[3]));
        orderFromFile.setProductType(dividedTxt[4]);
        orderFromFile.setArea(new BigDecimal(dividedTxt[5]));
        orderFromFile.setCostPerSquareFoot(new BigDecimal (dividedTxt[6]));
        orderFromFile.setLaborCostPerSquareFoot(new BigDecimal (dividedTxt[7]));
        orderFromFile.setMaterialCost(new BigDecimal (dividedTxt[8]));
        orderFromFile.setLaborCost(new BigDecimal (dividedTxt[9]));
        orderFromFile.setTax(new BigDecimal (dividedTxt[10]));
        orderFromFile.setTotal(new BigDecimal (dividedTxt[11]));
        
        return orderFromFile;
    }
    private void readOrderFile(String Date) throws PersistanceException {
        
        Scanner scanner = null;
        
        try {
            scanner = new Scanner (new BufferedReader(new FileReader(Date) ) );
        } catch (IOException e) {
            throw new PersistanceException("Could not load Order File in memory.", e);
        }
        
        String currentLine;
        Order currentOrder; 
        orderList.removeAll(orderList);
        scanner.nextLine();
        while(scanner.hasNext()){ 
            currentLine = scanner.nextLine();
            currentOrder = unmarshalTxt(currentLine);
            orderList.add(currentOrder);
        }
        scanner.close();
    }
    private String marshalTxt(Order order){
        
        String OrderTxt = order.getOrderNumber() + DELIMITER; 
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
        
        return OrderTxt;
    }
    private void writeToOrderFile(String Date) throws PersistanceException {
        
        PrintWriter out = null;
        
        try{
            out = new PrintWriter( new FileWriter(Date));
        } catch (IOException e) {
            throw new PersistanceException("Could not write to Order File", e);
        }
        out.println
        ("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        String OrderAsTxt;
        for (Order current: orderList){
            OrderAsTxt = marshalTxt(current);
            out.println(OrderAsTxt);
            out.flush();
        }
        
        out.close();
    }
}
