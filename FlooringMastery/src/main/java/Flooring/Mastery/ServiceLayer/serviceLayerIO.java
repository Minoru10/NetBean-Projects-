package Flooring.Mastery.ServiceLayer;

import Flooring.Mastery.Dto.Order;
import java.io.FileNotFoundException;

public interface serviceLayerIO {
    
    Order addOrder(Order order)throws FileNotFoundException;
    
    Order editOrder(String orderDate, int orderNumber);
    
    Order removeOrder(String orderDate, int orderNumber)throws FileNotFoundException;
    
}
