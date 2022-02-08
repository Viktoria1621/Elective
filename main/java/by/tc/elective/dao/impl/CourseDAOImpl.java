package by.tc.elective.dao.impl;

import by.tc.elective.dao.CourseDAO;
import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.Course;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INIT_REQUEST = "INSERT INTO courses (title, description, duration, finalTests_id, categories_id, teachers_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_COURSE_BY_ID_REQUEST = "DELETE FROM courses WHERE courses_id = ?";
    private static final String FIND_COURSE_BY_ID_REQUEST = "SELECT * FROM courses WHERE courses_id = ?";
    private static final String FIND_COURSE_BY_CATEGORY_REQUEST = "SELECT * FROM courses WHERE categories_id = ?";
    private static final String SELECT_ALL_COURSES_REQUEST = "SELECT * FROM courses";
    private static final String UPDATE_COURSE_BY_ID_REQUEST = "UPDATE courses SET title = ?, description = ?, duration = ?, finalTests_id = ?, categories_id = ?, teachers_id = ? WHERE courses_id = ?";


    @Override
    public boolean initCourse(Course newCourse) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INIT_REQUEST);

            ps.setString(1, newCourse.getCourseTitle());
            ps.setString(2, newCourse.getCourseDescription());
            ps.setInt(3, newCourse.getCourseDuration());
            ps.setInt(4, newCourse.getFinalTestID());
            ps.setInt(5, newCourse.getCategoryID());
            ps.setInt(6, newCourse.getTeacherID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Course isn't added.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }

    @Override
    public boolean deleteCourse(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_COURSE_BY_ID_REQUEST);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Course isn't deleted.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }

    @Override
    public Course findCourseByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Course course = new Course();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_COURSE_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                course.setCourseID(rs.getInt("courses_id"));
                course.setCourseTitle(rs.getString("title"));
                course.setCourseDescription(rs.getString("description"));
                course.setCourseDuration(rs.getInt("duration"));
                course.setFinalTestID(rs.getInt("finalTests_id"));
                course.setCategoryID(rs.getInt("categories_id"));
                course.setTeacherID(rs.getInt("teachers_id"));
            }
        } catch (SQLException e) {
            throw new DAOException("Course isn't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return course;
    }

    @Override
    public List<Course> findCourseByCategory(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;

        List<Course> course = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_COURSE_BY_CATEGORY_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Course newCourse = new Course();

                newCourse.setCourseID(rs.getInt("courses_id"));
                newCourse.setCourseTitle(rs.getString("title"));
                newCourse.setCourseDescription(rs.getString("description"));
                newCourse.setCourseDuration(rs.getInt("duration"));
                newCourse.setFinalTestID(rs.getInt("finalTests_id"));
                newCourse.setCategoryID(rs.getInt("categories_id"));
                newCourse.setTeacherID(rs.getInt("teachers_id"));

                course.add(newCourse);
            }
        } catch (SQLException e) {
            throw new DAOException("Courses aren't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
        return course;
    }

    @Override
    public List<Course> showAllCourses() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<Course> course = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_COURSES_REQUEST);

            while (rs.next()) {
                Course newCourse = new Course();

                newCourse.setCourseID(rs.getInt("courses_id"));
                newCourse.setCourseTitle(rs.getString("title"));
                newCourse.setCourseDescription(rs.getString("description"));
                newCourse.setCourseDuration(rs.getInt("duration"));
                newCourse.setFinalTestID(rs.getInt("finalTests_id"));
                newCourse.setCategoryID(rs.getInt("categories_id"));
                newCourse.setTeacherID(rs.getInt("teachers_id"));

                course.add(newCourse);
            }
        } catch (SQLException e) {
            throw new DAOException("Courses aren't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, st, rs);
        }
        return course;
    }

    @Override
    public boolean updateCourse(Course course, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_COURSE_BY_ID_REQUEST);

            ps.setString(1, course.getCourseTitle());
            ps.setString(2, course.getCourseDescription());
            ps.setInt(3, course.getCourseDuration());
            ps.setInt(4, course.getFinalTestID());
            ps.setInt(5, course.getCategoryID());
            ps.setInt(6, course.getTeacherID());
            ps.setInt(7, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Course isn't updated.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }
}
