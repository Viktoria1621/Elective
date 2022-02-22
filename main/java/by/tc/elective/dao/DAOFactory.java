package by.tc.elective.dao;

import by.tc.elective.dao.impl.*;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.dao.pool.ConnectionPoolException;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private UserDAO userDAO = new UserDAOImpl();
    private CourseDAO courseDAO = new CourseDAOImpl();
    private CategoryDAO categoryDAO = new CategoryDAOImpl();
    private CourseRunUserInfoDAO courseRunUserInfoDAO = new CourseRunUserInfoDAOImpl();
    private CourseRunDAO courseRunDAO = new CourseRunDAOImpl();

    private DAOFactory() {

    }

    private ConnectionPool connectionPool;

    {
        try {
            connectionPool = new ConnectionPool();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException();
        }
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public CourseDAO getCourseDAO() {
        return courseDAO;
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public CourseRunUserInfoDAO getCourseRunUserInfoDAO() {
        return courseRunUserInfoDAO;
    }

    public CourseRunDAO getCourseRunDAO() {
        return courseRunDAO;
    }
}
