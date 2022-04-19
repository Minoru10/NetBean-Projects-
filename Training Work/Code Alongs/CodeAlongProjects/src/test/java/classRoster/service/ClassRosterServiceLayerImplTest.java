package classRoster.service;

import classRoster.dao.ClassRosterAuditDao;
import classRoster.dao.ClassRosterAuditDaoStubImpl;
import classRoster.dao.ClassRosterDao;
import classRoster.dao.ClassRosterDaoStubImpl;
import classRoster.dao.ClassRosterPersistenceException;
import classroster.dto.Student;
import classroster.service.ClassRosterDataValidationException;
import classroster.service.ClassRosterDuplicateIdException;
import classroster.service.ClassRosterServiceLayer;
import classroster.service.ClassRosterServiceLayerImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClassRosterServiceLayerImplTest {
    
    private ClassRosterServiceLayer testService;
    
    public ClassRosterServiceLayerImplTest() {
        ClassRosterDao dao = new ClassRosterDaoStubImpl();
        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
        
        
        testService = new ClassRosterServiceLayerImpl(dao, auditDao);
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
    public void testSomeMethod() {
        Student student = new Student("0002");
        student.setFirstName("Charles");
        student.setLastName("Babbage");
        student.setCohort(".NET-May-1845");
        
        
        try {
            testService.createStudent(student);
        } catch (ClassRosterDuplicateIdException |
                 ClassRosterDataValidationException |
                 ClassRosterPersistenceException ex) {
            fail("This was the valid test");
        }
        
    }
    
}
