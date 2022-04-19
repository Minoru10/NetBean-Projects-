package vMachineDAO;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import vMachineDTO.Snack;

@Component
public class vmDaoImpl implements vmDaoInterface {
    
    List<Snack> ListOfSnacks = new ArrayList<>(); //List to store snacks 
    
// Get all snacks from the list
    @Override
    public List<Snack> allSnacks() {
        return ListOfSnacks; 
    }
// Add a snack to the list
    @Override
    public void addSnack(Snack snack) {
        ListOfSnacks.add(snack); 
    }
    
}
