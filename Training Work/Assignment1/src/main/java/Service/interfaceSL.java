package Service;

import Dao.exceptionDao;
import Dto.DVD;

public interface interfaceSL {
    
    void createDVD(DVD dvd) throws exceptionDuplicateID, exceptionInvalidInput, exceptionDao;
    
}
