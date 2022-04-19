package vMachineSL;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import vMachineDAO.AuditException;
import vMachineDAO.interfaceAudit;
import vMachineDAO.vmDaoAuditStub;
import vMachineDAO.vmDaoInterface;
import vMachineDAO.vmDaoStub;
import vMachineDTO.Snack;


public class vmServiceLayerTest {
    
    private InterfaceVmServiceLayer vmService;
    Snack snack1, snack2;
    
    public vmServiceLayerTest() {
        vmDaoInterface dao = new vmDaoStub();
        interfaceAudit audit = new vmDaoAuditStub();
        
        vmService = new vmServiceLayer(dao, audit);
        
        snack1 = new Snack("SKITTLES");
        BigDecimal cost = new BigDecimal("1.15");
        snack1.setCost(cost);
        snack1.setInventory("3");
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testVending() {
        
        vmService.addSnack(snack1);
        
         assertEquals(3, Integer.parseInt(snack1.getInventory()), "There should be 3 remaining skittles");
     
        BigDecimal balance=null;
        try {
            balance = vmService.vendItem("2.00", snack1);
        } catch (NoItemInventoryException | InsufficientFundsException | AuditException ex) {
                fail("The test case is a prototype.");
        } 
        BigDecimal expectedbalance = new BigDecimal("0.85");
        assertEquals(2, Integer.parseInt(snack1.getInventory()), "There should be 2 remaining skittles");
        assertTrue(expectedbalance.equals(balance), "Exepected change should match the calculated balance" );
        
        try {
            balance = vmService.vendItem("1.20", snack1);
        } catch (NoItemInventoryException | InsufficientFundsException | AuditException ex) {
                fail("The test case is a prototype.");
        }
        expectedbalance = new BigDecimal("0.05");
        assertEquals(1, Integer.parseInt(snack1.getInventory()), "There should be 1 remaining skittles");
        assertTrue(expectedbalance.equals(balance), "Exepected change should match the calculated balance" );
        
//        try {
//            vmService.vendItem("1.15", snack1);
//        } catch (NoItemInventoryException | InsufficientFundsException | AuditException ex) {
//                fail("The test case is a prototype.");
//        }
//        
//        assertEquals(0, Integer.parseInt(snack1.getInventory()), "There should be 1 remaining skittles");
    }
    @Test
    public void testValidCash(){
        try {
            vmService.validateCash(snack1, "2.00");
        } catch (NoItemInventoryException | InsufficientFundsException | AuditException ex) {
                fail("This failed because cash is less that snack price");
        } 
    }
    @Test
    public void testValidStock(){
        try {
            assertFalse(Integer.parseInt(vmService.validateStock(1).getInventory()) == 0, "should be 1 skittle left in vm");
        } catch (NoItemInventoryException | InsufficientFundsException | AuditException ex) {
                fail(ex.getMessage());
        }
    }
    @Test
    public void testFilterList(){
        BigDecimal cash = new BigDecimal("1.00");
        List<Snack> filter = null;
        try {
            filter = vmService.filteredList(cash);
        } catch (NoItemInventoryException | InsufficientFundsException | AuditException ex) {
            fail("Something went wront");
        }
        for (Snack current: filter){
            assertTrue(current.getCost().compareTo(cash) < 0, "List should be null");
        }
        
    }
    
}
