package vMachineDAO;

import java.util.List;
import vMachineDTO.Snack;

public interface vmDaoInterface {
    
    List<Snack> allSnacks();
    
    void addSnack(Snack snack);
    
}
