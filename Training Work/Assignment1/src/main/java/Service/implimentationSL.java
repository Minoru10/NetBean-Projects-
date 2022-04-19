package Service;

import Dao.ImplimentationDao;
import Dao.exceptionDao;
import Dto.DVD;
import java.util.List;

public class implimentationSL implements interfaceSL{
    
    ImplimentationDao Dao = new ImplimentationDao();

    @Override
    public void createDVD( DVD dvd) throws exceptionDuplicateID, exceptionInvalidInput, exceptionDao {
        
        
        for (DVD current: Dao.getlist()){
            if (dvd.getID().equals(current.getID()))
                throw new exceptionDuplicateID("Error: ID already in use");
        }
        if (validate(dvd))
            throw new exceptionInvalidInput ("Error: Must enter a value for all fields");
        Dao.addDvd(dvd);
    }
    
    public boolean validate(DVD dvd) {
        
        boolean result = false;
        if (dvd.getTitle().equals(null) || dvd.getTitle().trim().length()==0 ||
            dvd.getReleaseDate().equals(null) || dvd.getReleaseDate().trim().length()==0){
            result = true;
        }
        return result;
    }
    
}
