
package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Order;
import Flooring.Mastery.ServiceLayer.FlooringPersistenceException;
import java.io.FileNotFoundException;
import java.util.List;

public interface FlooringMasteryDao {
      List<Order> getAllOrders(String orderText)throws FlooringPersistenceException, FileNotFoundException ;
    
    Order addOrder(String orderText,int orderNumber, Order order)throws FlooringPersistenceException, FileNotFoundException ;
    
    Order getOrder(String orderText, int orderNumber)throws FlooringPersistenceException, FileNotFoundException ;
    
    Order editOrder(String orderText, int orderNumber, Order order)throws FlooringPersistenceException, FileNotFoundException ;
    
    Order removeOrder(String orderText, int orderNumber)throws FlooringPersistenceException, FileNotFoundException ; 
    
    public List<Integer> getOrderNumList(String orderText) throws FlooringPersistenceException, FileNotFoundException;

    public boolean fileExist(String orderText);
    
    public void clearHash();
    
    public void fileCreator(String orderText)throws FlooringPersistenceException, FileNotFoundException;
}
