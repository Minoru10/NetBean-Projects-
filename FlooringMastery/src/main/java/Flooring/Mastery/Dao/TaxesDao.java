
package Flooring.Mastery.Dao;

import Flooring.Mastery.Dto.Tax;
import Flooring.Mastery.ServiceLayer.ValidationException;
import java.util.List;


public interface TaxesDao {
    
     List<Tax> ListAllTaxes() throws ValidationException, PersistanceException;
}
