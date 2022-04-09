package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Order;
import Flooring.Mastery.ServiceLayer.ValidationException;
import java.util.List;

public interface OrderDoa {
    
    void addOrder(Order order)throws ValidationException, PersistanceException;
    
    void removeOrder(Order order, String date) throws ValidationException, PersistanceException;
    
    List<Order> ListAllOrders (String Date) throws ValidationException, PersistanceException;
    
}
