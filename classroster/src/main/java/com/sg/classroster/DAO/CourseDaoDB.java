package com.sg.classroster.DAO;

import com.sg.classroster.DAO.StudentDaoDB.StudentMapper;
import com.sg.classroster.DAO.TeacherDaoDB.TeacherMapper;
import com.sg.classroster.DTO.Course;
import com.sg.classroster.DTO.Student;
import com.sg.classroster.DTO.Teacher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoDB implements CourseDao{
    
    @Autowired
   JdbcTemplate jdbc;

//========================================  Get a course from the DB based on the given id  ==================================
    @Override
    public Course getCourseById(int id) {
        
        try {
            final String SELECT_COURSE_BY_ID = "SELECT * FROM course WHERE id = ?";
            Course course = jdbc.queryForObject(SELECT_COURSE_BY_ID, new CourseMapper(), id);
            course.setTeacher(getTeacherForCourse(id));
            course.setStudents(getStudentsForCourse(id));
            return course;
        } catch(DataAccessException ex) {
            return null;
        }
        
    }
    private Teacher getTeacherForCourse(int id) {
        final String SELECT_TEACHER_FOR_COURSE = 
                "SELECT t.* FROM teacher t JOIN course c ON c.teacherId = t.id WHERE c.id = ?";
        
        return jdbc.queryForObject(SELECT_TEACHER_FOR_COURSE, new TeacherMapper(), id);
    }
    private List<Student> getStudentsForCourse(int id) {
        final String SELECT_STUDENTS_FOR_COURSE = 
                "SELECT s.* FROM student s JOIN course_student cs ON cs.studentId = s.id WHERE cs.courseId = ?";
        
        return jdbc.query(SELECT_STUDENTS_FOR_COURSE, new StudentMapper(), id);
    }
    
//===================================  Get a list of all the course in the DB  ====================================
    @Override
    public List<Course> getAllCourses() {
        
        final String SELECT_ALL_COURSES = "SELECT * FROM course";
        List<Course> courses = jdbc.query(SELECT_ALL_COURSES, new CourseMapper());
        associateTeacherAndStudents(courses);
        return courses;
        
    }
    private void associateTeacherAndStudents(List<Course> courses) {
        for (Course course : courses) {
            course.setTeacher(getTeacherForCourse(course.getId()));
            course.setStudents(getStudentsForCourse(course.getId()));
        }
    }
    
//============================================  Add a new course to the DB  ================================= 
    @Override
    public Course addCourse(Course course) {
        
        final String INSERT_COURSE = "INSERT INTO course(name, description, teacherId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_COURSE,
                course.getName(),
                course.getDescription(),
                course.getTeacher().getId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        course.setId(newId);
        insertCourseStudent(course);
        return course;
        
    }
    private void insertCourseStudent(Course course) {
        final String INSERT_COURSE_STUDENT = "INSERT INTO "
                + "course_student(courseId, studentId) VALUES(?,?)";
        for(Student student : course.getStudents()) {
            jdbc.update(INSERT_COURSE_STUDENT, 
                    course.getId(),
                    student.getId());
        }
    }
    
//===============================================  Update an already existing course in DB =========================================
    @Override
    public void updateCourse(Course course) {
        
        final String UPDATE_COURSE = "UPDATE course SET name = ?, description = ?, "
                + "teacherId = ? WHERE id = ?";
        jdbc.update(UPDATE_COURSE, 
                course.getName(), 
                course.getDescription(), 
                course.getTeacher().getId(),
                course.getId());
        
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE courseId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, course.getId());
        insertCourseStudent(course);
        
    }

//============================================  Delete a course from DB based on given id  =====================================
    @Override
    public void deleteCourseById(int id) {
        
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE courseId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, id);
        
        final String DELETE_COURSE = "DELETE FROM course WHERE id = ?";
        jdbc.update(DELETE_COURSE, id);
        
    }

//=======================================  Get all course linked with a teacher from DB  ====================================
    @Override
    public List<Course> getCoursesForTeacher(Teacher teacher) {
        
        final String SELECT_COURSES_FOR_TEACHER = "SELECT * FROM course WHERE teacherId = ?";
        List<Course> courses = jdbc.query(SELECT_COURSES_FOR_TEACHER, 
                new CourseMapper(), teacher.getId());
        associateTeacherAndStudents(courses);
        return courses;
        
    }

//=================================== Get all courses a student is in from DB  ============================================
    @Override
    public List<Course> getCoursesForStudent(Student student) {
        
        final String SELECT_COURSES_FOR_STUDENT = "SELECT c.* FROM course c JOIN "
                + "course_student cs ON cs.courseId = c.Id WHERE cs.studentId = ?";
        List<Course> courses = jdbc.query(SELECT_COURSES_FOR_STUDENT, 
                new CourseMapper(), student.getId());
        associateTeacherAndStudents(courses);
        return courses;
        
    }
    
    
//==============  This is the course Mapper which gets a course from the DB and transelates it to the java course object  ======================
    public static final class CourseMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int index) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setDescription(rs.getString("description"));
            return course;
        }
    }
}
