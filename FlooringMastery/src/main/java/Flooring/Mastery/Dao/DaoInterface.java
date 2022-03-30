package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Order;
import java.io.FileNotFoundException;
import java.util.List;

public interface DaoInterface {
    
    void addOrder(Order order)throws FileNotFoundException;
    
    Order getOrder (String orderDate, int OrderNumber); // method to add a DVD
    
    void removeOrder (Order order)throws FileNotFoundException; // remove an order
    
    List<Order> orderList (String Date)throws FileNotFoundException;
    
}
