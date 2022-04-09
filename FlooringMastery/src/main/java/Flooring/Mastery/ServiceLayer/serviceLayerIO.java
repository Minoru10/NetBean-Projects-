package Flooring.Mastery.ServiceLayer;

import Flooring.Mastery.Dao.PersistanceException;
import Flooring.Mastery.Dto.Order;
import Flooring.Mastery.Dto.Product;
import Flooring.Mastery.Dto.Tax;
import java.io.FileNotFoundException;
import java.util.List;

public interface serviceLayerIO {
    
    Order addOrder(Order order, String Date) throws ValidationException, PersistanceException, FileNotFoundException;
    
    void removeOrder(Order order, String date) throws ValidationException, PersistanceException, FileNotFoundException;
    
    List<Order> ListAllOrders(String Date) throws ValidationException, PersistanceException, FileNotFoundException;
    
    List<Product> ListProducts() throws ValidationException, PersistanceException, FileNotFoundException;
    
    List<Tax> ListTaxes()throws ValidationException, PersistanceException, FileNotFoundException;
    
    int generateOrderNumber(String date)throws ValidationException, PersistanceException, FileNotFoundException;
    
    void checkForFile(String date) throws ValidationException, PersistanceException, FileNotFoundException;
    
    void editAndSave(Order newOrder, String Date) throws ValidationException, PersistanceException, FileNotFoundException;
    
    void validateOrderData (Order order) throws ValidationException, PersistanceException, FileNotFoundException;
//    
//   public Order orderCalculations(Order order);
//   
//    public List<Order> retrieveAllOrders(String orderText) throws PersistenceException, FileNotFoundException;
//    
//    public List<Product> retrieveAllProducts() throws PersistenceException, FileNotFoundException;
//    
//    public Order addOrder(String orderText,int orderNumber, Order order) throws PersistenceException, FileNotFoundException;
//    
//    public Order retrieveOrder(String orderText, int orderNumber)throws PersistenceException, FileNotFoundException;
//    
//    public Order updateOrder(String orderText, int orderNumber, Order order) throws PersistenceException, FileNotFoundException;
//    
//    public Order deleteOrder(String orderText, int orderNumber) throws PersistenceException, FileNotFoundException;
//    
//    public Order stateInfoEnter(Order order, String state) throws PersistenceException, FileNotFoundException;
//    
//    public Order productInfoEnter(Order order, String product) throws PersistenceException, FileNotFoundException;
//    
//    public boolean orderNumCheck(String orderText,int orderNum) throws PersistenceException, FileNotFoundException;
//    
//    public String makeOrderText(String orderDate);
//    
//    public boolean fileCheck(String orderText);
//    
//    public void fileMake(String orderText)throws PersistenceException, FileNotFoundException ;
//    
//    public void clearHashMap();
//    
//    public Order autoGenOrderNum(List<Order> orderList, Order currentOrder);
//    
//    public List<String> retrieveAllStates() throws PersistenceException, FileNotFoundException;
//    
//     public List<String> retrieveAllProductNames() throws PersistenceException, FileNotFoundException ;
}
