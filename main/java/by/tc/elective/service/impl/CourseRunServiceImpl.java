package by.tc.elective.service.impl;

import by.tc.elective.bean.CourseRun;
import by.tc.elective.dao.CourseRunDAO;
import by.tc.elective.dao.CourseRunUserInfoDAO;
import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.DAOFactory;
import by.tc.elective.service.CourseRunService;
import by.tc.elective.service.ServiceException;
import by.tc.elective.service.Validator;

import java.util.List;

public class CourseRunServiceImpl implements CourseRunService {

    @Override
    public boolean initCourseRun(CourseRun newCourseRun) throws ServiceException {

        // валидатор

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
        return false;
    }

    @Override
    public CourseRun findCourseRunByID(int id) throws ServiceException {
        return null;
    }

    @Override
    public List<CourseRun> showAllCourseRuns() throws ServiceException {
        return null;
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
        return false;
    }
}
