package com.sg.classroster.DAO;

import com.sg.classroster.DTO.Student;
import java.util.List;

public interface StudentDao {
    
    Student getStudentById(int id);
    
    List<Student> getAllStudents();
    
    Student addStudent(Student student);
    
    void updateStudent(Student student);
    
    void deleteStudentById(int id);
    
}
