package classroster.controller;

import classRoster.dao.ClassRosterPersistenceException;
import classRoster.dao.ClassRosterDaoFileImpl;
import classroster.dto.Student;
import classroster.service.ClassRosterDuplicateIdException;
import classroster.service.ClassRosterDataValidationException;
import classroster.service.ClassRosterServiceLayer;
import classroster.ui.ClassRosterView;
import java.util.List;

public class ClassRosterController {
    
    private ClassRosterView view;
    //private ClassRosterDaoFileImpl dataAccess;
    private ClassRosterServiceLayer service;
    

    public ClassRosterController(ClassRosterView view, ClassRosterServiceLayer service) {
        this.view = view;
        this.service = service;
    }
    /*
    * Displays a menu and runs users picked choice
    */
    public void run(){
        boolean keepGoing = true; //Boolean to keep the while loop going
        try{
            while (keepGoing){
                
                int menuSelection = getAndPrintMenuSelection(); //Stores user choice from the menu list
                
                switch(menuSelection){ 
                  case 1:
                        displayAllStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        ViewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                       unknownCommand();
                }
            }
            exitMessage();
        } catch(ClassRosterPersistenceException e){
            view.displayErrorMessage(e.getMessage());
          }
    }
    
    //Displays menu options from view class.
    private int getAndPrintMenuSelection(){
         return view.printMenuAndGetSelection();
    }
    //Create student using view and dao
    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        
        do{
            Student newStudent = view.getNewStudentInfo();
            
            try {
                service.createStudent(newStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
        
        //dataAccess.addStudent(newStudent.getStudentId(), newStudent);
        
        
    }
    //displays a list of students in the list using view and dao
    private void displayAllStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        List<Student> allStudents = service.getAllStudent();
        view.displayStudentList(allStudents);
    }
    //display a current students information given their student id
    private void ViewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
        String ID = view.getStudentchoiceID();
        Student currentStudent = service.getStudent(ID);
        view.displayStudentInfo(currentStudent);
    }
    // remove a student from the list given their student id
    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String ID = view.getStudentchoiceID();
        Student currentStudent = service.removeStudent(ID);
        view.displayRemoveResult(currentStudent);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
 
