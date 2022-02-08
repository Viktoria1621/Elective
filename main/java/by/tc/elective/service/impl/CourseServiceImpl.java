package by.tc.elective.service.impl;

import by.tc.elective.dao.CourseDAO;
import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.DAOFactory;
import by.tc.elective.bean.Course;
import by.tc.elective.service.CourseService;
import by.tc.elective.service.ServiceException;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    @Override
    public boolean initCourse(Course newCourse) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseDAO courseDAO = factory.getCourseDAO();

        try {
            courseDAO.initCourse(newCourse);
        } catch (DAOException e) {
            throw new ServiceException("Course isn't added.", e);
        }
        return true;
    }

    @Override
    public boolean deleteCourse(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseDAO courseDAO = factory.getCourseDAO();

        try {
            courseDAO.deleteCourse(id);
        } catch (DAOException e) {
            throw new ServiceException("Course isn't deleted.", e);
        }
        return true;
    }

    @Override
    public Course findCourseByID(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseDAO courseDAO = factory.getCourseDAO();
        Course course;

        try {
            course = courseDAO.findCourseByID(id);
        } catch (DAOException e) {
            throw new ServiceException("Course isn't found.", e);
        }
        return course;
    }

    @Override
    public List<Course> findCourseByTeacher(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseDAO courseDAO = factory.getCourseDAO();
        List<Course> courses;

        try {
            courses = courseDAO.findCourseByTeacher(id);
        } catch (DAOException e) {
            throw new ServiceException("Courses aren't found.", e);
        }
        return courses;
    }

    @Override
    public List<Course> findCourseByCategory(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseDAO courseDAO = factory.getCourseDAO();
        List<Course> courses;

        try {
            courses = courseDAO.findCourseByCategory(id);
        } catch (DAOException e) {
            throw new ServiceException("Courses aren't found.", e);
        }
        return courses;
    }

    @Override
    public List<Course> showAllCourses() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseDAO courseDAO = factory.getCourseDAO();
        List<Course> courses;

        try {
            courses = courseDAO.showAllCourses();
        } catch (DAOException e) {
            throw new ServiceException("Courses aren't found.", e);
        }
        return courses;
    }

    @Override
    public boolean updateCourse(Course course, int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseDAO courseDAO = factory.getCourseDAO();

        try {
            courseDAO.updateCourse(course, id);
        } catch (DAOException e) {
            throw new ServiceException("Course isn't updated.", e);
        }
        return true;
    }
}
