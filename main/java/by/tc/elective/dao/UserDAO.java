package by.tc.elective.dao;

import by.tc.elective.bean.User;

import java.util.List;

public interface UserDAO {
    boolean registration(User newUser) throws DAOException;

    User signIn(String login, String password) throws DAOException;

    boolean deleteUser(int id) throws DAOException;

    User findUserByID(int id) throws DAOException;

    List<User> showAllUsers() throws DAOException;

    boolean updateUser(User user, int id) throws DAOException;
}
