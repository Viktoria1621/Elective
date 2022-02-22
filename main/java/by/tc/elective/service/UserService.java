package by.tc.elective.service;

import by.tc.elective.bean.User;

import java.util.List;

public interface UserService {
    boolean registration(User newUser) throws ServiceException;

    User signIn(String login, String password) throws ServiceException;

    boolean deleteUser(int id) throws ServiceException;

    User findUserByID(int id) throws ServiceException;

    List<User> showAllUsers() throws ServiceException;

    boolean updateUser(User user, int id) throws ServiceException;
}
