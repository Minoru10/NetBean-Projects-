package classroster.service;

import classRoster.dao.ClassRosterAuditDao;
import classRoster.dao.ClassRosterPersistenceException;
import classroster.dto.Student;
import java.util.List;
import classRoster.dao.ClassRosterDao;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer{
    
    ClassRosterDao dataAccess;
    ClassRosterAuditDao auditDao;

    public ClassRosterServiceLayerImpl(ClassRosterDao dataAccess, ClassRosterAuditDao auditDao) {
        this.dataAccess = dataAccess;
        this.auditDao = auditDao;
    }
    
    private void validateStudentData (Student student) throws ClassRosterDataValidationException {
        if (student.getFirstName().equals(null) || student.getFirstName().trim().length()==0 ||
            student.getLastName().equals(null) || student.getLastName().trim().length()==0 ||
            student.getCohort().equals(null) || student.getCohort().trim().length()==0 )
                
            throw new ClassRosterDataValidationException( "ERROR: All Fields Need DATA!");
           
        
    }
    

    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        
        if (dataAccess.getStudent(student.getStudentId()) != null){
            throw new ClassRosterDuplicateIdException (
            "Error: ID already in use");
        }
        validateStudentData(student);
        dataAccess.addStudent(student.getStudentId(), student);
        auditDao.writeAuditEntry("Student " + student.getFirstName() + " Created");
        
    }

    @Override
    public List<Student> getAllStudent() throws ClassRosterPersistenceException {
        return dataAccess.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dataAccess.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student student = dataAccess.removeStudent(studentId);
        //write to file as student is removed
        auditDao.writeAuditEntry("Student " + student.getFirstName() + " Created");
        return student;
    }
    
    
    
}
