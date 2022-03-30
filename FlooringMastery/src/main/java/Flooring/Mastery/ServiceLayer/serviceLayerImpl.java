package Flooring.Mastery.ServiceLayer;

import Flooring.Mastery.Dao.DaoImpl;
import Flooring.Mastery.Dao.DaoInterface;
import Flooring.Mastery.Dto.Order;
import java.io.FileNotFoundException;

public class serviceLayerImpl implements serviceLayerIO {

    DaoInterface Dao = new DaoImpl();
    
    @Override
    public Order addOrder(Order order) throws FileNotFoundException{
        //if (!Dao.orderList().contains(order))
            Dao.addOrder(order);
        
        return order;
    }
    
    @Override
    public Order editOrder(String orderDate, int orderNumber) {
        Order order = Dao.getOrder(orderDate, orderNumber);
        
        return order;
    }

    @Override
    public Order removeOrder(String orderDate, int orderNumber) throws FileNotFoundException{
        Order order = Dao.getOrder(orderDate, orderNumber);
        Dao.removeOrder(order);
        
        return order;
    }
    
    
}
