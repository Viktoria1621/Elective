package by.tc.elective.controller.impl;

import by.tc.elective.bean.Category;
import by.tc.elective.controller.Command;
import by.tc.elective.service.CategoryService;
import by.tc.elective.service.ServiceException;
import by.tc.elective.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowCategoriesCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(ShowCategoriesCommand.class);

        ServiceFactory factory = ServiceFactory.getInstance();
        CategoryService categoryService = factory.getCategoryService();
        List<Category> categories;

        try {
            categories = categoryService.showAllCategories();
            if (!categories.isEmpty()) {
                request.setAttribute("categories", categories);
                request.setAttribute("add", "Add");
                request.setAttribute("delete", "Delete");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/category.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Categories aren't found.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
