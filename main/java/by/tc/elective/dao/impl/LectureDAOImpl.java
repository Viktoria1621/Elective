package by.tc.elective.dao.impl;

import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.LectureDAO;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.Lecture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectureDAOImpl implements LectureDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INIT_REQUEST = "INSERT INTO lectures (title, theory_path, courses_id) VALUES (?, ?, ?)";
    private static final String DELETE_LECTURE_BY_ID_REQUEST = "DELETE FROM lectures WHERE lectures_id = ?";
    private static final String FIND_LECTURE_BY_ID_REQUEST = "SELECT * FROM lectures WHERE lectures_id = ?";
    private static final String SELECT_ALL_LECTURES_REQUEST = "SELECT * FROM lectures";
    private static final String UPDATE_LECTURE_BY_ID_REQUEST = "UPDATE lectures SET title = ?, theory_path = ?, courses_id = ? WHERE lectures_id = ?";


    @Override
    public void initLecture(Lecture newLecture) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INIT_REQUEST);

            ps.setString(1, newLecture.getLectureTitle());
            ps.setString(2, newLecture.getTheoryPath());
            ps.setInt(3, newLecture.getCourseID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Lecture isn't added.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public void deleteLecture(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_LECTURE_BY_ID_REQUEST);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Lecture isn't deleted.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public Lecture findLectureByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Lecture lecture = new Lecture();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_LECTURE_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                lecture.setLectureID(rs.getInt("lectures_id"));
                lecture.setLectureTitle(rs.getString("title"));
                lecture.setTheoryPath(rs.getString("theory_path"));
                lecture.setCourseID(rs.getInt("courses_id"));
            }
        } catch (SQLException e) {
            throw new DAOException("Lecture isn't found.", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return lecture;
    }

    @Override
    public List<Lecture> showAllLectures() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<Lecture> lecture = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_LECTURES_REQUEST);

            while (rs.next()) {
                Lecture newLecture = new Lecture();

                newLecture.setLectureID(rs.getInt("lectures_id"));
                newLecture.setLectureTitle(rs.getString("title"));
                newLecture.setTheoryPath(rs.getString("theory_path"));
                newLecture.setCourseID(rs.getInt("courses_id"));

                lecture.add(newLecture);
            }
        } catch (SQLException e) {
            throw new DAOException("Lectures aren't found.", e);
        } finally {
            connectionPool.closeConnection(con, st, rs);
        }
        return lecture;
    }

    @Override
    public void updateLecture(Lecture lecture, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_LECTURE_BY_ID_REQUEST);

            ps.setString(1, lecture.getLectureTitle());
            ps.setString(2, lecture.getTheoryPath());
            ps.setInt(3, lecture.getCourseID());
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Lecture isn't updated.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
