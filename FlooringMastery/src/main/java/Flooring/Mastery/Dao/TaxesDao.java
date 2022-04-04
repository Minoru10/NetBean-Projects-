
package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Order;
import Flooring.Mastery.ServiceLayer.FlooringPersistenceException;
import java.io.FileNotFoundException;
import java.util.List;


public interface TaxesDao {
      public List<String> getAllStates() throws FlooringPersistenceException, FileNotFoundException;
    
    public Order stateDataEnter(Order order, String state) throws FlooringPersistenceException, FileNotFoundException;
}
