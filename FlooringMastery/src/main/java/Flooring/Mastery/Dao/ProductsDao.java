
package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Order;
import Flooring.Mastery.Dto.Product;
import Flooring.Mastery.ServiceLayer.FlooringPersistenceException;
import java.io.FileNotFoundException;
import java.util.List;


public interface ProductsDao {
    public List<Product> getAllProducts() throws FlooringPersistenceException, FileNotFoundException;
   
    public List<String> getAllProductNames() throws FlooringPersistenceException, FileNotFoundException ;
    
    public Order productDataEnter(Order order, String product) throws FlooringPersistenceException, FileNotFoundException;
}
