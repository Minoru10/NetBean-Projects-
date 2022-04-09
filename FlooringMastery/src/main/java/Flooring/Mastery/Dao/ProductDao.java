
package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Order;
import Flooring.Mastery.Dto.Product;
import Flooring.Mastery.ServiceLayer.ValidationException;
import java.util.List;


public interface ProductDao {
    
    List<Product> ListAllProducts() throws ValidationException, PersistanceException;
   
}
