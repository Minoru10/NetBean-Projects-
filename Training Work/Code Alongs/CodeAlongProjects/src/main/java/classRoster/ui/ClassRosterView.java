package classroster.ui;

import classroster.dto.Student;
import java.util.List;

/*
This view class is in charge of displaying all information to the user and getting information 
from the user to be stored in other locations in the program.
*/
public class ClassRosterView {
    
    private UserIO io;

    public ClassRosterView(UserIO io) {
        this.io = io;
    }
    /*
    * This method prints a menu of options for the user to pick from
    * Displays the choice onto the screen
    */
    public int printMenuAndGetSelection(){
        // List of menu options
        io.print("Main Menu");
        io.print("1. List Students");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");
        
        return io.readInt("Choose from the menu listed: ", 1, 5);
    }
    /*
    * Getsand stores a the student information from the user and returns that new student.
    */
    public Student getNewStudentInfo() {
        String studentID = io.readString("Enter the new student's ID: ");
        String firstName = io.readString("Enter the new student's fist name: ");
        String lastName = io.readString("Enter the new student's last name: ");
        String cohort = io.readString("Enter the new student's cohort: ");
        Student newStudent = new Student(studentID);
        newStudent.setFirstName(firstName);
        newStudent.setLastName(lastName);
        newStudent.setCohort(cohort);
        
        return newStudent;
    }
    public void displayCreateStudentBanner() {
        io.print("=== Create Student ===");
    }
    public void displayCreateSuccessBanner() {
        io.readString("Student successfully created.  Please hit enter to continue");
    }
    /*
    * Lets the user view a specific students based on their student id.
    * checks that the given studentID is valid.
    */
    public void displayDisplayStudentBanner () {
        io.print("=== Display Student ===");
    }
    public String getStudentchoiceID(){
       return io.readString("Enter the Students ID: ");
    }
    public void displayStudentInfo(Student currentStudent){
        String studentInfo = String.format( "#%s : %s %s",
                                  currentStudent.getStudentId(),
                                  currentStudent.getFirstName(),
                                  currentStudent.getLastName());
            io.print(studentInfo);
    }
    /*
    * Displays all the students from our list
    */
    public void displayDisplayAllBanner () {
        io.print("=== Display All Student ===");
    }
    public void displayStudentList(List<Student> studentList){
        for (Student currentStudent: studentList){
            String studentInfo = String.format( "#%s : %s %s",
                                  currentStudent.getStudentId(),
                                  currentStudent.getFirstName(),
                                  currentStudent.getLastName());
            io.print(studentInfo);
        }
        io.readString("Hit enter to continue");
    }

    
    public void displayRemoveStudentBanner () {
        io.print("=== Remove Student ===");
    }
    public void displayRemoveResult(Student studentRecord) {
        if(studentRecord != null){
            io.print("Student successfully removed.");
        }else {
            io.print("No such student.");
        }
            io.readString("Please hit enter to continue.");
    }
    
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
