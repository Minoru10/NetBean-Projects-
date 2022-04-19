package Controller;

import Dao.exceptionDao;
import Dao.interfaceDao;
import Dto.DVD;
import Service.exceptionDuplicateID;
import Service.exceptionInvalidInput;
import Service.implimentationSL;
import Service.interfaceSL;
import UserIO.ViewDVD;

public class control {
    
     ViewDVD view = new ViewDVD();
     interfaceSL service = new implimentationSL();

    
    public void run(){
        
        try{
            create();
        } catch (exceptionDao | exceptionDuplicateID | exceptionInvalidInput e){
            view.print(e.getMessage());
        }
                
    }
    
    private void create() throws exceptionDuplicateID, exceptionInvalidInput, exceptionDao{
        do{
        DVD dvd = view.createDVD();
        service.createDVD(dvd);
        view.displayDVD(dvd);
        } while (view.getStringInfo("create another DVD? Enter y for yes or n for No: ").equals("y"));
    }
    
}
