package by.tc.elective.service.impl;

import by.tc.elective.bean.Course;
import by.tc.elective.dao.CategoryDAO;
import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.DAOFactory;
import by.tc.elective.bean.Category;
import by.tc.elective.service.CategoryService;
import by.tc.elective.service.ServiceException;
import by.tc.elective.service.Validator;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {


    @Override
    public boolean initCategory(Category newCategory) throws ServiceException {
        String title;
        title = newCategory.getCategoryTitle();
        if (!Validator.categoryValidator(title)) {
            return false;
        }
        DAOFactory factory = DAOFactory.getInstance();
        CategoryDAO categoryDAO = factory.getCategoryDAO();

        try {
            categoryDAO.initCategory(newCategory);
        } catch (DAOException e) {
            throw new ServiceException("Category isn't added.", e);
        }
        return true;
    }

    @Override
    public boolean deleteCategory(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CategoryDAO categoryDAO = factory.getCategoryDAO();

        try {
            categoryDAO.deleteCategory(id);
        } catch (DAOException e) {
            throw new ServiceException("Category isn't deleted.", e);
        }
        return true;
    }

    @Override
    public Category findCategoryByID(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CategoryDAO categoryDAO = factory.getCategoryDAO();
        Category category;

        try {
            category = categoryDAO.findCategoryByID(id);
        } catch (DAOException e) {
            throw new ServiceException("Category isn't found.", e);
        }
        return category;
    }

    @Override
    public int findCategoryByTitle(String title) throws ServiceException {
        if (!Validator.categoryValidator(title)) {
            return 0;
        }
        DAOFactory factory = DAOFactory.getInstance();
        CategoryDAO categoryDAO = factory.getCategoryDAO();
        int categoryID;

        try {
            categoryID = categoryDAO.findCategoryByTitle(title);
        } catch (DAOException e) {
            throw new ServiceException("Category isn't found.", e);
        }
        return categoryID;
    }

    @Override
    public List<Category> showAllCategories() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CategoryDAO categoryDAO = factory.getCategoryDAO();
        List<Category> categories;

        try {
            categories = categoryDAO.showAllCategories();
        } catch (DAOException e) {
            throw new ServiceException("Categories aren't found.", e);
        }
        return categories;
    }

    @Override
    public boolean updateCategory(Category category, int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        CategoryDAO categoryDAO = factory.getCategoryDAO();

        try {
            categoryDAO.updateCategory(category, id);
        } catch (DAOException e) {
            throw new ServiceException("Category isn't updated.", e);
        }
        return true;
    }

}
