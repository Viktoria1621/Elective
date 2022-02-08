package by.tc.elective.service;

import by.tc.elective.bean.Category;
import by.tc.elective.dao.DAOException;

import java.util.List;

public interface CategoryService {
    boolean initCategory(Category newCategory) throws ServiceException;
    boolean deleteCategory(int id) throws ServiceException;
    Category findCategoryByID(int id) throws ServiceException;
    int findCategoryByTitle(String title) throws ServiceException;
    List<Category> showAllCategories() throws ServiceException;
    boolean updateCategory(Category category, int id) throws ServiceException;
}
