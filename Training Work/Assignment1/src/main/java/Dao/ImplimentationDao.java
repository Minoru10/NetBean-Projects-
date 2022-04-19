package Dao;

import Dto.DVD;
import java.util.ArrayList;
import java.util.List;

public class ImplimentationDao implements interfaceDao{
    
    List<DVD> listOfDVD = new ArrayList<>();

    @Override
    public void addDvd(DVD dvd) throws exceptionDao{
        listOfDVD.add(dvd);
    }

    @Override
    public int numOfDVDs()throws exceptionDao{
        return listOfDVD.size();
    }
    
    public List<DVD> getlist (){
        return listOfDVD;
    }
    
}
