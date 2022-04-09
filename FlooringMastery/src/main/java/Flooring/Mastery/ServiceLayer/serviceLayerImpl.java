package Flooring.Mastery.ServiceLayer;

import Flooring.Mastery.Dao.PersistanceException;
import Flooring.Mastery.Dao.OrderDaoImpl;
import Flooring.Mastery.Dto.Order;
import Flooring.Mastery.Dto.Product;
import Flooring.Mastery.Dto.Tax;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import Flooring.Mastery.Dao.ProductDaoImpl;
import Flooring.Mastery.Dao.TaxDaoImpl;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class serviceLayerImpl implements serviceLayerIO {

    OrderDaoImpl OrderDao;// = new OrderDaoImpl();
    TaxDaoImpl TaxDao;// = new TaxDaoImpl();
    ProductDaoImpl ProductDao;// = new ProductDaoImpl();

    public serviceLayerImpl(OrderDaoImpl OrderDao, TaxDaoImpl TaxDao, ProductDaoImpl ProductDao) {
        this.OrderDao = OrderDao;
        this.TaxDao = TaxDao;
        this.ProductDao = ProductDao;
    }
    
    @Override
    public Order addOrder(Order order, String Date) throws ValidationException, PersistanceException{
        
        LocalDate now = LocalDate.now();
        if (new File(Date).exists())
            OrderDao.addOrder(order);
        if (!new File(Date).exists())
            throw new ValidationException ("Error: You entered a past date!!");
        else 
            try {
                new File(Date).createNewFile();
        } catch (IOException ex) {
            throw new PersistanceException ("Error: Unable to Create the new File!!");
        }
        return order;
    }
    @Override
    public void removeOrder(Order order, String date) throws ValidationException, PersistanceException{
        OrderDao.removeOrder(order, date);
    }
    @Override
    public List<Order> ListAllOrders(String Date) throws ValidationException, PersistanceException{
        return OrderDao.ListAllOrders(Date);
    }
    @Override
    public List<Product> ListProducts() throws ValidationException, PersistanceException {
        return ProductDao.ListAllProducts();
    }
    @Override
    public List<Tax> ListTaxes()throws ValidationException, PersistanceException {
        return TaxDao.ListAllTaxes();
    }
    @Override
    public int generateOrderNumber(String date)throws ValidationException, PersistanceException{
        int num = 0;
        if (new File(date).exists()){
            for (Order current: OrderDao.ListAllOrders(date) ){
                if (num <= current.getOrderNumber())
                    num = current.getOrderNumber()+1;
            }
        }else 
            num = 1;
        return num;
    }
    @Override
    public void checkForFile(String date) throws ValidationException, PersistanceException{
        LocalDate D2 = LocalDate.parse(date);
        if (D2.isBefore(LocalDate.now())){
            DateTimeFormatter formater = DateTimeFormatter.ofPattern("MMddyyyy");
            String Date = formater.format(D2);
            Date = "Orders_"+Date+".txt";
            File temp = new File (Date);
            if (!new File(Date).exists())
                throw new ValidationException ("Error: You entered a past date that doesn't already exist");
        }
    }
    @Override
    public void editAndSave(Order newOrder, String Date) throws ValidationException, PersistanceException {
        for (Order current: OrderDao.ListAllOrders(Date)){
            if (current.getOrderNumber() == newOrder.getOrderNumber()){
                current.setArea(newOrder.getArea());
                current.setCustomerName(newOrder.getCustomerName());
                current.setProductType(newOrder.getProductType());
                current.setState(newOrder.getState());
                current.setCostPerSquareFoot(newOrder.getCostPerSquareFoot());
                current.setLaborCost(newOrder.getLaborCost());
                current.setLaborCostPerSquareFoot(newOrder.getLaborCostPerSquareFoot());
                current.setMaterialCost(newOrder.getMaterialCost());
                current.setTaxRate(newOrder.getTaxRate());
                current.setTax(newOrder.getTax());
                current.setTotal(newOrder.getTotal());
            }
        }
        OrderDao.editAndSave(newOrder);
    }
    @Override
    public void validateOrderData (Order order) throws ValidationException, PersistanceException {
    //Check that a valid name is entered 
        if ( order.getCustomerName().equals(null) || order.getCustomerName().trim().length() == 0 || order.getCustomerName().isBlank() )
            throw new ValidationException( "ERROR: Invalid input for customer name!");
    //Check a valid state is entered
        List<Tax> taxL = TaxDao.ListAllTaxes().stream()
                           .filter(T -> T.getStateAbbrv().equals(order.getState()))
                           .collect(Collectors.toList());
        if (taxL.isEmpty())
            throw new ValidationException ("ERROR: We do not sell in that state!");
        for (Tax current: taxL){
            order.setTaxRate(current.getTaxRate());
        }
   //Check that user inters product from product list
        BigDecimal Rate = order.getTaxRate().divide(new BigDecimal(100));
        List<Product> productL = ProductDao.ListAllProducts().stream()
                           .filter(P -> P.getProductType().equals(order.getProductType()))
                           .collect(Collectors.toList());
        if (productL.isEmpty())
            throw new ValidationException ("ERROR: Product type not in List!");
        for (Product current: productL){ //Does Product Calculations
                order.setCostPerSquareFoot(current.getCostPerSquareFoot());
                order.setLaborCostPerSquareFoot(current.getLaborCostPerSquareFoot());
                order.setMaterialCost( order.getArea().multiply(order.getCostPerSquareFoot()) );
                order.setLaborCost( order.getArea().multiply(order.getLaborCostPerSquareFoot()) );
                order.setTax( order.getMaterialCost().add(order.getLaborCost()).multiply(Rate) );
                order.setTotal( order.getMaterialCost().add(order.getLaborCost().add(order.getTax())) );
        }
    }
}
