package classRoster.dao;

import classroster.dto.Student;
import java.util.List;

public interface ClassRosterDao {
    
    Student addStudent (String studentID, Student student)throws ClassRosterPersistenceException;
    
    List<Student> getAllStudents()throws ClassRosterPersistenceException;
    
    Student getStudent (String studentID)throws ClassRosterPersistenceException;
    
    Student removeStudent (String studentID)throws ClassRosterPersistenceException;
}
