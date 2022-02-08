package by.tc.elective.dao.impl;

import by.tc.elective.dao.CourseRunDAO;
import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.CourseRun;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRunDAOImpl implements CourseRunDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INIT_REQUEST = "INSERT INTO courserun (courses_id, startDate, endDate, numberOfStudentsInGroup) VALUES (?, ?, ?, ?)";
    private static final String DELETE_COURSE_RUN_BY_ID_REQUEST = "DELETE FROM courserun WHERE courseRun_id = ?";
    private static final String FIND_COURSE_RUN_BY_ID_REQUEST = "SELECT * FROM courserun WHERE courseRun_id = ?";
    private static final String FIND_COURSE_RUN_COURSE_REQUEST = "SELECT courses_id FROM courserun WHERE courseRun_id = ?";
    private static final String SELECT_ALL_COURSE_RUNS_REQUEST = "SELECT * FROM courserun";
    private static final String UPDATE_COURSE_RUN_BY_ID_REQUEST = "UPDATE courserun SET courses_id = ?, startDate = ?, endDate = ?, numberOfStudentsInGroup = ? WHERE courseRun_id = ?";

    @Override
    public void initCourseRun(CourseRun newCourseRun) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INIT_REQUEST);

            ps.setInt(1, newCourseRun.getCourseID());
            ps.setDate(2, (Date) newCourseRun.getCourseStartDay());
            ps.setDate(3, (Date) newCourseRun.getCourseEndDay());
            ps.setInt(4, newCourseRun.getNumberOfStudentsInGroup());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Course run isn't added.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public void deleteCourseRun(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_COURSE_RUN_BY_ID_REQUEST);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Course run isn't deleted.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public CourseRun findCourseRunByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        CourseRun courseRun = new CourseRun();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_COURSE_RUN_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                courseRun.setCourseRunID(rs.getInt("courseRun_id"));
                courseRun.setCourseStartDay(rs.getDate("startDate"));
                courseRun.setCourseEndDay(rs.getDate("endDate"));
                courseRun.setNumberOfStudentsInGroup(rs.getInt("numberOfStudentsInGroup"));
                courseRun.setCourseID(rs.getInt("courses_id"));
            }
        } catch (SQLException e) {
            throw new DAOException("Course run isn't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return courseRun;
    }

    @Override
    public List<CourseRun> showAllCourseRuns() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<CourseRun> courseRun = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_COURSE_RUNS_REQUEST);

            while (rs.next()) {
                CourseRun newCourseRun = new CourseRun();

                newCourseRun.setCourseRunID(rs.getInt("courseRun_id"));
                newCourseRun.setCourseStartDay(rs.getDate("startDate"));
                newCourseRun.setCourseEndDay(rs.getDate("endDate"));
                newCourseRun.setNumberOfStudentsInGroup(rs.getInt("numberOfStudentsInGroup"));
                newCourseRun.setCourseID(rs.getInt("courses_id"));

                courseRun.add(newCourseRun);
            }
        } catch (SQLException e) {
            throw new DAOException("Course runs aren't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, st, rs);
        }
        return courseRun;
    }

    @Override
    public List<Integer> showCourseRunCourse(List<Integer> usersCourseRunID) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;

        List<Integer> courseRunCoursesID = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_COURSE_RUN_COURSE_REQUEST);

            for (Integer c : usersCourseRunID) {
                ps.setInt(1, c);
                rs = ps.executeQuery();
                while (rs.next()) {
                    courseRunCoursesID.add(rs.getInt("courses_id"));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Course run course isn't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
        return courseRunCoursesID;
    }

    @Override
    public void updateCourseRun(CourseRun courseRun, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_COURSE_RUN_BY_ID_REQUEST);

            ps.setInt(1, courseRun.getCourseID());
            ps.setDate(2, (Date) courseRun.getCourseStartDay());
            ps.setDate(3, (Date) courseRun.getCourseEndDay());
            ps.setInt(4, courseRun.getNumberOfStudentsInGroup());
            ps.setInt(5, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Course run isn't updated.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
