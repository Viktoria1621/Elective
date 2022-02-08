package by.tc.elective.dao;


import by.tc.elective.bean.Course;

import java.util.List;

public interface CourseDAO {
    boolean initCourse(Course newCourse) throws DAOException;
    boolean deleteCourse(int id) throws DAOException;
    Course findCourseByID(int id) throws DAOException;
    List<Course> findCourseByCategory(int id) throws DAOException;
    List<Course> showAllCourses() throws DAOException;
    boolean updateCourse(Course course, int id) throws DAOException;
}
