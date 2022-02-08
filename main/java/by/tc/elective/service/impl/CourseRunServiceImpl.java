package by.tc.elective.service.impl;

import by.tc.elective.bean.CourseRun;
import by.tc.elective.dao.CourseRunDAO;
import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.DAOFactory;
import by.tc.elective.service.CourseRunService;
import by.tc.elective.service.ServiceException;

import java.util.List;

public class CourseRunServiceImpl implements CourseRunService {

    @Override
    public boolean initCourseRun(CourseRun newCourseRun) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunDAO courseRunDAO = factory.getCourseRunDAO();

        try {
            courseRunDAO.initCourseRun(newCourseRun);
        } catch (DAOException e) {
            throw new ServiceException("Course run isn't added.", e);
        }
        return true;
    }

    @Override
    public boolean deleteCourseRun(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunDAO courseRunDAO = factory.getCourseRunDAO();

        try {
            courseRunDAO.deleteCourseRun(id);
        } catch (DAOException e) {
            throw new ServiceException("Course run isn't added.", e);
        }
        return true;
    }

    @Override
    public CourseRun findCourseRunByID(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunDAO courseRunDAO = factory.getCourseRunDAO();

        CourseRun courseRun;

        try {
            courseRun = courseRunDAO.findCourseRunByID(id);
        } catch (DAOException e) {
            throw new ServiceException("Course run isn't found.", e);
        }
        return courseRun;
    }

    @Override
    public List<CourseRun> showAllCourseRuns() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunDAO courseRunDAO = factory.getCourseRunDAO();
        List<CourseRun> courseRuns;

        try {
            courseRuns = courseRunDAO.showAllCourseRuns();
        } catch (DAOException e) {
            throw new ServiceException("Courses aren't found.", e);
        }
        return courseRuns;
    }

    @Override
    public List<Integer> showCourseRunsByCourse(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunDAO courseRunDAO = factory.getCourseRunDAO();
        List<Integer> courseRunsByCourse;

        try {
            courseRunsByCourse = courseRunDAO.showCourseRunsByCourse(id);
        } catch (DAOException e) {
            throw new ServiceException("Courses aren't found.", e);
        }
        return courseRunsByCourse;
    }

    @Override
    public List<Integer> showCourseRunCourse(List<Integer> usersCourseRunID) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunDAO courseRunDAO = factory.getCourseRunDAO();
        List<Integer> courseRunCoursesID;

        try {
            courseRunCoursesID = courseRunDAO.showCourseRunCourse(usersCourseRunID);
        } catch (DAOException e) {
            throw new ServiceException("Courses aren't found.", e);
        }
        return courseRunCoursesID;
    }

    @Override
    public boolean updateCourseRun(CourseRun courseRun, int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunDAO courseRunDAO = factory.getCourseRunDAO();

        try {
            courseRunDAO.updateCourseRun(courseRun, id);
        } catch (DAOException e) {
            throw new ServiceException("Course run isn't updated.", e);
        }
        return true;
    }
}
