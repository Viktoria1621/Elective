package by.tc.elective.dao.impl;

import by.tc.elective.dao.CategoryDAO;
import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INIT_REQUEST = "INSERT INTO categories SET title = ?";
    private static final String DELETE_CATEGORY_BY_ID_REQUEST = "DELETE FROM categories WHERE categories_id = ?";
    private static final String FIND_CATEGORY_BY_ID_REQUEST = "SELECT * FROM categories WHERE categories_id = ?";
    private static final String FIND_CATEGORY_BY_TITLE_REQUEST = "SELECT * FROM categories WHERE title = ?";
    private static final String SELECT_ALL_CATEGORIES_REQUEST = "SELECT * FROM categories";
    private static final String UPDATE_CATEGORY_BY_ID_REQUEST = "UPDATE categories SET title = ? WHERE categories_id = ?";

    @Override
    public boolean initCategory(Category newCategory) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INIT_REQUEST);

            ps.setString(1, newCategory.getCategoryTitle());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Category isn't added.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }

    @Override
    public boolean deleteCategory(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_CATEGORY_BY_ID_REQUEST);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Category isn't deleted.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
        System.out.println("daoDelete");
        return true;
    }

    @Override
    public Category findCategoryByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Category category = new Category();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_CATEGORY_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                category.setCategoryID(rs.getInt("categories_id"));
                category.setCategoryTitle(rs.getString("title"));
            }
        } catch (SQLException e) {
            throw new DAOException("Category isn't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return category;
    }

    @Override
    public int findCategoryByTitle(String title) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int categoryID;
        Category category = new Category();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_CATEGORY_BY_TITLE_REQUEST);

            ps.setString(1, title);
            rs = ps.executeQuery();

            while (rs.next()) {
                category.setCategoryID(rs.getInt("categories_id"));
                category.setCategoryTitle(rs.getString("title"));
            }

            categoryID = category.getCategoryID();
        } catch (SQLException e) {
            throw new DAOException("Category isn't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        System.out.println("daoFind");
        return categoryID;
    }

    @Override
    public List<Category> showAllCategories() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<Category> category = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_CATEGORIES_REQUEST);

            while (rs.next()) {
                Category newCategory = new Category();

                newCategory.setCategoryID(rs.getInt("categories_id"));
                newCategory.setCategoryTitle(rs.getString("title"));

                category.add(newCategory);
            }
        } catch (SQLException e) {
            throw new DAOException("Categories aren't found.", e);
        }

        finally {
            connectionPool.closeConnection(con, st, rs);
        }
        return category;
    }

    @Override
    public boolean updateCategory(Category category, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_CATEGORY_BY_ID_REQUEST);

            ps.setString(1, category.getCategoryTitle());
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Category isn't updated.", e);
        }

        finally {
            connectionPool.closeConnection(con, ps);
        }
        return true;
    }
}
