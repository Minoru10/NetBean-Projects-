package classRoster;

import classroster.controller.ClassRosterController;
import classRoster.dao.ClassRosterAuditDao;
import classRoster.dao.ClassRosterAuditDaoFileImpl;
import classRoster.dao.ClassRosterDaoFileImpl;
import classroster.service.ClassRosterServiceLayer;
import classroster.service.ClassRosterServiceLayerImpl;
import classroster.ui.ClassRosterView;
import classroster.ui.UserIOConsoleImpl;
import classroster.ui.UserIO;

public class App {
    public static void main (String[] args){
        
        UserIO myIO = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIO);
        ClassRosterDaoFileImpl myDAO = new ClassRosterDaoFileImpl();
        ClassRosterAuditDao myAuditDAO = new ClassRosterAuditDaoFileImpl();
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDAO, myAuditDAO);
        ClassRosterController controller = new ClassRosterController(myView, myService);
        
        controller.run();
    }
}
