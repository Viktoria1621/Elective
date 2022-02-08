package by.tc.elective.dao.impl;

import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.RoleDAO;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INIT_REQUEST = "INSERT INTO roles SET title = ?";
    private static final String DELETE_ROLE_BY_ID_REQUEST = "DELETE FROM roles WHERE roles_id = ?";
    private static final String FIND_ROLE_BY_ID_REQUEST = "SELECT * FROM roles WHERE roles_id = ?";
    private static final String SELECT_ALL_ROLES_REQUEST = "SELECT * FROM roles";
    private static final String UPDATE_ROLE_BY_ID_REQUEST = "UPDATE roles SET title = ? WHERE roles_id = ?";

    @Override
    public void initRole(Role newRole) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INIT_REQUEST);

            ps.setString(1, newRole.getRoleTitle());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Role isn't added.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public void deleteRole(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_ROLE_BY_ID_REQUEST);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Role isn't deleted.",e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public Role findRoleByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Role role = new Role();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_ROLE_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                role.setRoleID(rs.getInt("roles_id"));
                role.setRoleTitle(rs.getString("title"));
            }
        } catch (SQLException e) {
            throw new DAOException("Role isn't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return role;
    }

    @Override
    public List<Role> showAllRoles() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<Role> role = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_ROLES_REQUEST);

            while (rs.next()) {
                Role newRole = new Role();

                newRole.setRoleID(rs.getInt("roles_id"));
                newRole.setRoleTitle(rs.getString("title"));

                role.add(newRole);
            }
        } catch (SQLException e) {
            throw new DAOException("Users aren't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, st, rs);
        }
        return role;
    }

    @Override
    public void updateRole(Role role, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_ROLE_BY_ID_REQUEST);

            ps.setString(1, role.getRoleTitle());
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("User isn't updated.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
