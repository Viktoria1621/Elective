package by.tc.elective.service;

import by.tc.elective.bean.CourseRun;

import java.util.List;

public interface CourseRunService {
    boolean initCourseRun(CourseRun newCourseRun) throws ServiceException;

    boolean deleteCourseRun(int id) throws ServiceException;

    CourseRun findCourseRunByID(int id) throws ServiceException;

    List<CourseRun> showAllCourseRuns() throws ServiceException;

    List<Integer> showCourseRunsByCourse(int id) throws ServiceException;

    List<Integer> showCourseRunCourse(List<Integer> usersCourseRunID) throws ServiceException;

    boolean updateCourseRun(CourseRun courseRun, int id) throws ServiceException;
}
