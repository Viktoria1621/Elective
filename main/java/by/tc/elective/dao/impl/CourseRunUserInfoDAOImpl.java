package by.tc.elective.dao.impl;

import by.tc.elective.dao.CourseRunUserInfoDAO;
import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.CourseRunUserInfo;

import java.rmi.registry.RegistryHandler;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRunUserInfoDAOImpl implements CourseRunUserInfoDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INIT_REQUEST = "INSERT INTO courserun_has_users (users_id, status, usersScore, teachersComment, courseRun_id) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_COURSE_RUN_USER_INFO_BY_ID_REQUEST = "DELETE FROM courserun_has_users WHERE courseRun_has_users_id = ?";
    private static final String FIND_COURSE_RUN_USER_INFO_BY_ID_REQUEST = "SELECT * FROM courserun_has_users WHERE courseRun_has_users_id = ?";
    private static final String FIND_USER_COURSE_RUN_BY_ID_REQUEST = "SELECT courseRun_id FROM courserun_has_users WHERE users_id = ?";
    private static final String SELECT_COURSE_RUN_BY_ID_REQUEST = "SELECT * FROM courserun_has_users WHERE users_id = ?";
    private static final String SELECT_COURSE_INFO_BY_USER_REQUEST = "SELECT * FROM courserun_has_users WHERE users_id = ? AND courseRun_id = ?";
    private static final String SELECT_ALL_COURSE_RUN_USER_INFO_REQUEST = "SELECT * FROM courserun_has_users";
    private static final String UPDATE_COURSE_RUN_USER_INFO_BY_ID_REQUEST = "UPDATE courserun_has_users SET users_id = ?, status = ?, usersScore = ?, teachersComment = ?,courseRun_id = ? WHERE courseRun_has_users_id = ?";

    @Override
    public boolean initUserToCourse(CourseRunUserInfo newCourseRunUserInfo) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INIT_REQUEST);

            ps.setInt(1, newCourseRunUserInfo.getUserID());
            ps.setString(2, newCourseRunUserInfo.getCourseStatus());
            ps.setInt(3, newCourseRunUserInfo.getUserScore());
            ps.setString(4, newCourseRunUserInfo.getTeacherComment());
            ps.setInt(5, newCourseRunUserInfo.getCourseRunID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("User course info isn't added.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }

    @Override
    public boolean deleteUserFromCourse(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_COURSE_RUN_USER_INFO_BY_ID_REQUEST);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("User course info isn't deleted.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }

    @Override
    public CourseRunUserInfo findCourseRunUserInfoByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;

        CourseRunUserInfo courseRunUserInfo = new CourseRunUserInfo();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_COURSE_RUN_USER_INFO_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                courseRunUserInfo.setCourseRunUserInfoID(rs.getInt("courseRun_has_users_id"));
                courseRunUserInfo.setUserID(rs.getInt("users_id"));
                courseRunUserInfo.setCourseStatus(rs.getString("status"));
                courseRunUserInfo.setUserScore(rs.getInt("usersScore"));
                courseRunUserInfo.setTeacherComment(rs.getString("teachersComment"));
                courseRunUserInfo.setCourseRunID(rs.getInt("courseRun_id"));
            }
        } catch (SQLException e) {
            throw new DAOException("User course info isn't found.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return courseRunUserInfo;
    }

    @Override
    public CourseRunUserInfo findCourseInfoByUser(int userID, int courseRunID) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;

        CourseRunUserInfo courseRunUserInfo = new CourseRunUserInfo();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SELECT_COURSE_INFO_BY_USER_REQUEST);

            ps.setInt(1, userID);
            ps.setInt(2, courseRunID);
            rs = ps.executeQuery();

            while (rs.next()) {
                courseRunUserInfo.setCourseRunUserInfoID(rs.getInt("courseRun_has_users_id"));
                courseRunUserInfo.setUserID(rs.getInt("users_id"));
                courseRunUserInfo.setCourseStatus(rs.getString("status"));
                courseRunUserInfo.setUserScore(rs.getInt("usersScore"));
                courseRunUserInfo.setTeacherComment(rs.getString("teachersComment"));
                courseRunUserInfo.setCourseRunID(rs.getInt("courseRun_id"));
            }
        } catch (SQLException e) {
            throw new DAOException("User course info isn't found.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return courseRunUserInfo;
    }

    @Override
    public List<CourseRunUserInfo> showAllCourseRunUserInfo() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<CourseRunUserInfo> courseRunUserInfo = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_COURSE_RUN_USER_INFO_REQUEST);

            while (rs.next()) {
                CourseRunUserInfo newCourseRunUserInfo = new CourseRunUserInfo();

                newCourseRunUserInfo.setCourseRunUserInfoID(rs.getInt("courseRun_has_users_id"));
                newCourseRunUserInfo.setUserID(rs.getInt("users_id"));
                newCourseRunUserInfo.setCourseStatus(rs.getString("status"));
                newCourseRunUserInfo.setUserScore(rs.getInt("usersScore"));
                newCourseRunUserInfo.setTeacherComment(rs.getString("teachersComment"));
                newCourseRunUserInfo.setCourseRunID(rs.getInt("courseRun_id"));

                courseRunUserInfo.add(newCourseRunUserInfo);
            }
        } catch (SQLException e) {
            throw new DAOException("User course info aren't found.", e);
        } finally {
            connectionPool.closeConnection(con, st, rs);
        }
        return courseRunUserInfo;
    }

    @Override
    public List<Integer> showAllUserCourseRuns(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;

        List<Integer> usersCourseRunID = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_USER_COURSE_RUN_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                usersCourseRunID.add(rs.getInt("courseRun_id"));
            }
        } catch (SQLException e) {
            throw new DAOException("User course info isn't found.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return usersCourseRunID;
    }

    @Override
    public List<CourseRunUserInfo> selectCourseRunInfo(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;

        List<CourseRunUserInfo> courseRunUserInfo = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SELECT_COURSE_RUN_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                CourseRunUserInfo newCourseRunUserInfo = new CourseRunUserInfo();

                newCourseRunUserInfo.setCourseRunUserInfoID(rs.getInt("courseRun_has_users_id"));
                newCourseRunUserInfo.setUserID(rs.getInt("users_id"));
                newCourseRunUserInfo.setCourseStatus(rs.getString("status"));
                newCourseRunUserInfo.setUserScore(rs.getInt("usersScore"));
                newCourseRunUserInfo.setTeacherComment(rs.getString("teachersComment"));
                newCourseRunUserInfo.setCourseRunID(rs.getInt("courseRun_id"));

                courseRunUserInfo.add(newCourseRunUserInfo);
            }
        } catch (SQLException e) {
            throw new DAOException("User course info isn't found.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return courseRunUserInfo;
    }

    @Override
    public boolean updateCourseRunUserInfo(CourseRunUserInfo courseRunUserInfo, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_COURSE_RUN_USER_INFO_BY_ID_REQUEST);

            ps.setInt(1, courseRunUserInfo.getUserID());
            ps.setString(2, courseRunUserInfo.getCourseStatus());
            ps.setInt(3, courseRunUserInfo.getUserScore());
            ps.setString(4, courseRunUserInfo.getTeacherComment());
            ps.setInt(5, courseRunUserInfo.getCourseRunID());
            ps.setInt(6, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("User course info isn't updated.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }
}
