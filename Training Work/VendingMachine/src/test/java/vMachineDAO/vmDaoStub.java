package vMachineDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import vMachineDTO.Snack;

public class vmDaoStub implements vmDaoInterface{

    List<Snack> listOfSnacks = new ArrayList<>();
    public Snack onlySnack;

    public vmDaoStub(){
        onlySnack = new Snack("Twix");
        BigDecimal cost = new BigDecimal("1.15");
        onlySnack.setCost(cost);
        onlySnack.setInventory("3");
    }
    
    public vmDaoStub(Snack onlySnack) {
        this.onlySnack = onlySnack;
    }
    
    @Override
    public List<Snack> allSnacks() {
        listOfSnacks.add(onlySnack);
        return listOfSnacks;
    }

    @Override
    public void addSnack(Snack snack) {
        listOfSnacks.add(snack);
    }
    
}
