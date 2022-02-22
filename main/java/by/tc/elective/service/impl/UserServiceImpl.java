package by.tc.elective.service.impl;

import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.DAOFactory;
import by.tc.elective.dao.UserDAO;
import by.tc.elective.bean.User;
import by.tc.elective.service.ServiceException;
import by.tc.elective.service.UserService;
import by.tc.elective.service.Validator;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public boolean registration(User newUser) throws ServiceException {
        if (!Validator.registrationValidator(newUser)) {
            return false;
        }

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        try {
            userDAO.registration(newUser);
        } catch (DAOException e) {
            throw new ServiceException("User isn't added.", e);
        }
        return true;
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        if (!Validator.signInValidator(login, password)) {
            throw new ServiceException("User isn't found.");
        }

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        User user;
        try {
            user = userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException("User isn't found.", e);
        }
        return user;
    }


    @Override
    public boolean deleteUser(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        try {
            userDAO.deleteUser(id);
        } catch (DAOException e) {
            throw new ServiceException("User isn't deleted.", e);
        }
        return true;
    }

    @Override
    public User findUserByID(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        User user;
        try {
            user = userDAO.findUserByID(id);
        } catch (DAOException e) {
            throw new ServiceException("User isn't found.", e);
        }
        return user;
    }

    @Override
    public List<User> showAllUsers() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        List<User> user;
        try {
            user = userDAO.showAllUsers();
        } catch (DAOException e) {
            throw new ServiceException("Users aren't found.", e);
        }
        return user;
    }

    @Override
    public boolean updateUser(User user, int id) throws ServiceException {
        if (!Validator.registrationValidator(user)) {
            return false;
        }

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        try {
            userDAO.updateUser(user, id);
        } catch (DAOException e) {
            throw new ServiceException("User isn't updated.", e);
        }
        return true;
    }
}
