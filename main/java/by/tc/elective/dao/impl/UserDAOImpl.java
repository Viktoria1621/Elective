package by.tc.elective.dao.impl;

import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.UserDAO;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String REGISTRATION_REQUEST = "INSERT INTO users (login, password, name, roles_id) VALUES (?, ?, ?, ?)";
    private static final String SING_IN_REQUEST = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String DELETE_USER_BY_ID_REQUEST = "DELETE FROM users WHERE users_id = ?";
    private static final String FIND_USER_BY_ID_REQUEST = "SELECT * FROM users WHERE users_id = ?";
    private static final String SELECT_ALL_USERS_REQUEST = "SELECT * FROM users";
    private static final String UPDATE_USER_BY_ID_REQUEST = "UPDATE users SET login = ?, password = ?, name = ?, roles_id = ? WHERE users_id = ?";

    @Override
    public boolean registration(User newUser) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(REGISTRATION_REQUEST);

            ps.setString(1, newUser.getLogin());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getName());
            ps.setInt(4, newUser.getRoleID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("User isn't added.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }

    @Override
    public User signIn(String login, String password) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs;

        User user = new User();
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SING_IN_REQUEST);

            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                user.setUserID(rs.getInt("users_id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setRoleID(rs.getInt("roles_id"));
            }

        } catch (SQLException e) {
            throw new DAOException("User isn't found.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return user;
    }

    @Override
    public boolean deleteUser(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_USER_BY_ID_REQUEST);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("User isn't deleted.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }

    @Override
    public User findUserByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = new User();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_USER_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                user.setUserID(rs.getInt("users_id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setRoleID(rs.getInt("roles_id"));
            }
        } catch (SQLException e) {
            throw new DAOException("User isn't found.", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return user;
    }

    @Override
    public List<User> showAllUsers() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<User> user = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_USERS_REQUEST);

            while (rs.next()) {
                User newUser = new User();

                newUser.setUserID(rs.getInt("users_id"));
                newUser.setLogin(rs.getString("login"));
                newUser.setPassword(rs.getString("password"));
                newUser.setName(rs.getString("name"));
                newUser.setRoleID(rs.getInt("roles_id"));

                user.add(newUser);
            }
        } catch (SQLException e) {
            throw new DAOException("Users aren't found.", e);
        } finally {
            connectionPool.closeConnection(con, st, rs);
        }
        return user;
    }

    @Override
    public boolean updateUser(User user, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_USER_BY_ID_REQUEST);

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setInt(4, user.getRoleID());
            ps.setInt(5, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("User isn't updated.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }
}
