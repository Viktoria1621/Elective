package by.tc.elective.service;

import by.tc.elective.bean.Course;

import java.util.List;

public interface CourseService {
    boolean initCourse(Course newCourse) throws ServiceException;

    boolean deleteCourse(int id) throws ServiceException;

    Course findCourseByID(int id) throws ServiceException;

    List<Course> findCourseByTeacher(int id) throws ServiceException;

    List<Course> findCourseByCategory(int id) throws ServiceException;

    List<Course> showAllCourses() throws ServiceException;

    boolean updateCourse(Course course, int id) throws ServiceException;
}
