package by.tc.elective.dao;

import by.tc.elective.bean.CourseRun;

import java.util.List;

public interface CourseRunDAO {
    void initCourseRun(CourseRun newCourseRun) throws DAOException;

    void deleteCourseRun(int id) throws DAOException;

    CourseRun findCourseRunByID(int id) throws DAOException;

    List<Integer> findCourseRunIDByCourse(List<Integer> id) throws DAOException;

    List<CourseRun> showAllCourseRuns() throws DAOException;

    List<Integer> showCourseRunsByCourse(int id) throws DAOException;

    List<Integer> showCourseRunCourse(List<Integer> usersCourseRunID) throws DAOException;

    void updateCourseRun(CourseRun courseRun, int id) throws DAOException;
}
