package com.sg.classroster.DAO;

import com.sg.classroster.DTO.Course;
import com.sg.classroster.DTO.Student;
import com.sg.classroster.DTO.Teacher;
import java.util.List;

public interface CourseDao {
    
    Course getCourseById(int id);
    
    List<Course> getAllCourses();
    
    Course addCourse(Course course);
    
    void updateCourse(Course course);
    
    void deleteCourseById(int id);
    
    
    List<Course> getCoursesForTeacher(Teacher teacher);
    
    List<Course> getCoursesForStudent(Student student);
    
}
