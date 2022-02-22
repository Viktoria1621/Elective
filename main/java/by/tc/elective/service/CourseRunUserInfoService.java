package by.tc.elective.service;

import by.tc.elective.bean.CourseRunUserInfo;

import java.util.List;

public interface CourseRunUserInfoService {
    boolean initUserToCourse(CourseRunUserInfo newCourseRunUserInfo) throws ServiceException;

    boolean deleteUserFromCourse(int id) throws ServiceException;

    CourseRunUserInfo findCourseRunUserInfoByID(int id) throws ServiceException;

    CourseRunUserInfo findCourseInfoByUser(int userID, int courseRunID) throws ServiceException;

    List<CourseRunUserInfo> showAllCourseRunUserInfo() throws ServiceException;

    List<Integer> showAllUserCourseRuns(int id) throws ServiceException;

    List<CourseRunUserInfo> selectCourseRunInfo(int id) throws ServiceException;

    boolean updateCourseRunUserInfo(CourseRunUserInfo courseRunUserInfo, int id) throws ServiceException;
}
