package by.tc.elective.dao;

import by.tc.elective.bean.Role;
import java.util.List;

public interface RoleDAO {
    void initRole(Role newRole) throws DAOException;
    void deleteRole(int id) throws DAOException;
    Role findRoleByID(int id) throws DAOException;
    List<Role> showAllRoles() throws DAOException;
    void updateRole(Role role, int id) throws DAOException;
}
