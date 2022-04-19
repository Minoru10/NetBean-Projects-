
package Dao;

import Dto.DVD;
import java.util.List;

public interface interfaceDao {
    
    void addDvd(DVD dvd) throws exceptionDao;
    
    int numOfDVDs () throws exceptionDao;
    
}
