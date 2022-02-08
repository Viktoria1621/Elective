package by.tc.elective.service.impl;

import by.tc.elective.bean.CourseRunUserInfo;
import by.tc.elective.dao.CourseRunUserInfoDAO;
import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.DAOFactory;
import by.tc.elective.service.CourseRunUserInfoService;
import by.tc.elective.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class CourseRunUserInfoServiceImpl implements CourseRunUserInfoService {

    @Override
    public boolean initUserToCourse(CourseRunUserInfo newCourseRunUserInfo) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunUserInfoDAO courseRunUserInfoDAO = factory.getCourseRunUserInfoDAO();

        try {
            courseRunUserInfoDAO.initUserToCourse(newCourseRunUserInfo);
        } catch (DAOException e) {
            throw new ServiceException("Course info isn't added.", e);
        }
        return true;
    }

    @Override
    public boolean deleteUserFromCourse(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunUserInfoDAO courseRunUserInfoDAO = factory.getCourseRunUserInfoDAO();

        try {
            courseRunUserInfoDAO.deleteUserFromCourse(id);
        } catch (DAOException e) {
            throw new ServiceException("Course info isn't deleted.", e);
        }
        return true;
    }

    @Override
    public CourseRunUserInfo findCourseRunUserInfoByID(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunUserInfoDAO courseRunUserInfoDAO = factory.getCourseRunUserInfoDAO();
        CourseRunUserInfo courseRunUserInfo;

        try {
            courseRunUserInfo = courseRunUserInfoDAO.findCourseRunUserInfoByID(id);
        } catch (DAOException e) {
            throw new ServiceException("Course info isn't found.", e);
        }
        return courseRunUserInfo;
    }

    @Override
    public List<CourseRunUserInfo> showAllCourseRunUserInfo() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunUserInfoDAO courseRunUserInfoDAO = factory.getCourseRunUserInfoDAO();
        List<CourseRunUserInfo> courseRunUserInfo;

        try {
            courseRunUserInfo = courseRunUserInfoDAO.showAllCourseRunUserInfo();
        } catch (DAOException e) {
            throw new ServiceException("Course info isn't found.", e);
        }
        return courseRunUserInfo;
    }

    @Override
    public List<Integer> showAllUserCourseRuns(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunUserInfoDAO courseRunUserInfoDAO = factory.getCourseRunUserInfoDAO();
        List<Integer> usersCourseRunID;

        try {
            usersCourseRunID = courseRunUserInfoDAO.showAllUserCourseRuns(id);
        } catch (DAOException e) {
            throw new ServiceException("User course runs aren't found.", e);
        }
        return usersCourseRunID;
    }

    @Override
    public boolean updateCourseRunUserInfo(CourseRunUserInfo courseRunUserInfo, int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunUserInfoDAO courseRunUserInfoDAO = factory.getCourseRunUserInfoDAO();

        try {
            courseRunUserInfoDAO.updateCourseRunUserInfo(courseRunUserInfo, id);
        } catch (DAOException e) {
            throw new ServiceException("Course info isn't updated.", e);
        }
        return true;
    }
}
