package by.tc.elective.dao;

import by.tc.elective.bean.CourseRunUserInfo;

import java.util.List;

public interface CourseRunUserInfoDAO {
    boolean initUserToCourse(CourseRunUserInfo newCourseRunUserInfo) throws DAOException;

    boolean deleteUserFromCourse(int id) throws DAOException;

    CourseRunUserInfo findCourseRunUserInfoByID(int id) throws DAOException;

    CourseRunUserInfo findCourseInfoByUser(int userID, int courseRunID) throws DAOException;

    List<CourseRunUserInfo> showAllCourseRunUserInfo() throws DAOException;

    List<Integer> showAllUserCourseRuns(int id) throws DAOException;

    List<CourseRunUserInfo> selectCourseRunInfo(int id) throws DAOException;

    boolean updateCourseRunUserInfo(CourseRunUserInfo courseRunUserInfo, int id) throws DAOException;
}
