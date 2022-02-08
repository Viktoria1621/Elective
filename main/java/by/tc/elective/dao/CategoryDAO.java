package by.tc.elective.dao;

import by.tc.elective.bean.Category;

import java.util.List;

public interface CategoryDAO {
    boolean initCategory(Category newCategory) throws DAOException;
    boolean deleteCategory(int id) throws DAOException;
    Category findCategoryByID(int id) throws DAOException;
    int findCategoryByTitle(String title) throws DAOException;
    List<Category> showAllCategories() throws DAOException;
    boolean updateCategory(Category category, int id) throws DAOException;
}
