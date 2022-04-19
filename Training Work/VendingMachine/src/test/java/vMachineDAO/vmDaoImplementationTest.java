package vMachineDAO;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import vMachineDTO.Snack;

public class vmDaoImplementationTest {
    
    private vmDaoImpl testDao = new vmDaoImpl();
    
    public vmDaoImplementationTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        this.testDao = testDao;    
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetAllSnacks() {
        String snackName = "Twix";
        Snack snack1 = new Snack(snackName);
        BigDecimal cost = new BigDecimal("1.15");
        snack1.setCost(cost);
        snack1.setInventory("3");
        
        String snackName2 = "kitkat";
        Snack snack2 = new Snack(snackName2);
        BigDecimal cost2 = new BigDecimal("1.50");
        snack2.setCost(cost2);
        snack2.setInventory("3");
        
        String snackName3 = "skittles";
        Snack snack3 = new Snack(snackName3);
        BigDecimal cost3 = new BigDecimal("2.00");
        snack3.setCost(cost3);
        snack3.setInventory("3");
        
        List<Snack> all = testDao.allSnacks();
        
        assertEquals(0, all.size(), "List should have no items yet");
        testDao.addSnack(snack1); //Add snack to Dao
        assertEquals(1, all.size(), "List should have 1 item");
        testDao.addSnack(snack2);
        assertEquals(2, all.size(),"List should have 2 items");
        testDao.addSnack(snack3);
        assertEquals(3, all.size(),"List should have 3 items");
        
        assertNotNull(all, "The list of snacks must not be null"); //Check List is Not null
        
        assertTrue(testDao.allSnacks().contains(snack1), "The list should have Twix.");
        assertTrue(testDao.allSnacks().contains(snack2), "The list should have Kitkat.");
        assertTrue(testDao.allSnacks().contains(snack3), "The list should have skittles.");
    }
}
