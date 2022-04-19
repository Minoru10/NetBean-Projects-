package vMachineApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vMachineController.vMachineController;
import vMachineDAO.AuditException;
import vMachineDAO.vmAudit;
import vMachineDAO.vmDaoImpl;
import vMachineSL.InsufficientFundsException;
import vMachineSL.NoItemInventoryException;
import vMachineSL.vmServiceLayer;
import vMachineView.userIO;
import vMachineView.userIOimpl;
import vMachineView.vMachineView;

public class App {

    public static void main(String[] args) throws NoItemInventoryException, InsufficientFundsException, AuditException {

    // ================ Hard Coded Dependency Injection ======================
        //userIO in = new userIOimpl();
        //vMachineView view = new vMachineView(in);
        //vmDaoImpl Dao = new vmDaoImpl();
        //vmAudit Audit = new vmAudit();
        //vmServiceLayer service = new vmServiceLayer(Dao, Audit);
        //vMachineController con = new vMachineController(view, service);
        //con.run();

    // ================ XML Dependency Injection ======================
        ApplicationContext appContext = new ClassPathXmlApplicationContext
                  ("classpath:applicationContext.xml");
        vMachineController controller = appContext.getBean("controller", vMachineController.class);
        controller.run();
          
    // ================ Annotation Dependency Injection (Still needs work) ======================    
//        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
//        appContext.scan("vMachineController");
//        appContext.refresh();
//        vMachineController controller = appContext.getBean("controller", vMachineController.class);
//        controller.run();
    }

}
