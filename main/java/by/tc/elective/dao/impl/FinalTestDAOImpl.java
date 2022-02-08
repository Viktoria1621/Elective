package by.tc.elective.dao.impl;

import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.FinalTestDAO;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.FinalTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinalTestDAOImpl implements FinalTestDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INIT_REQUEST = "INSERT INTO finaltests (title, description, duration) VALUES (?, ?, ?)";
    private static final String DELETE_TEST_BY_ID_REQUEST = "DELETE FROM finaltests WHERE finalTests_id = ?";
    private static final String FIND_TEST_BY_ID_REQUEST = "SELECT * FROM finaltests WHERE finalTests_id = ?";
    private static final String SELECT_ALL_TESTS_REQUEST = "SELECT * FROM finaltests";
    private static final String UPDATE_TEST_BY_ID_REQUEST = "UPDATE finaltests SET title = ?, description = ?, duration = ? WHERE finalTests_id = ?";


    @Override
    public void initTest(FinalTest newFinalTest) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INIT_REQUEST);

            ps.setString(1, newFinalTest.getFinalTestTitle());
            ps.setString(2, newFinalTest.getFinalTestDescription());
            ps.setTime(3, newFinalTest.getFinalTestDuration());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Final test isn't added.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public void deleteTest(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_TEST_BY_ID_REQUEST);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Final test isn't deleted.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public FinalTest findTestByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        FinalTest test = new FinalTest();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_TEST_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                test.setFinalTestID(rs.getInt("finalTests_id"));
                test.setFinalTestTitle(rs.getString("title"));
                test.setFinalTestDescription(rs.getString("description"));
                test.setFinalTestDuration(rs.getTime("duration"));
            }
        } catch (SQLException e) {
            throw new DAOException("Final test isn't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return test;
    }

    @Override
    public List<FinalTest> showAllTests() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<FinalTest> test = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_TESTS_REQUEST);

            while (rs.next()) {
                FinalTest newTest = new FinalTest();

                newTest.setFinalTestID(rs.getInt("finalTests_id"));
                newTest.setFinalTestTitle(rs.getString("title"));
                newTest.setFinalTestDescription(rs.getString("description"));
                newTest.setFinalTestDuration(rs.getTime("duration"));

                test.add(newTest);
            }
        } catch (SQLException e) {
            throw new DAOException("Final tests aren't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, st, rs);
        }
        return test;
    }

    @Override
    public void updateTest(FinalTest test, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_TEST_BY_ID_REQUEST);

            ps.setString(1, test.getFinalTestTitle());
            ps.setString(2, test.getFinalTestDescription());
            ps.setTime(3, test.getFinalTestDuration());
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Test isn't updated.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
