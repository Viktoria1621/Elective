package by.tc.elective.controller.impl.add;

import by.tc.elective.bean.Category;
import by.tc.elective.controller.Command;
import by.tc.elective.service.CategoryService;
import by.tc.elective.service.ServiceException;
import by.tc.elective.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewCategoryCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(AddNewCategoryCommand.class);
        final String TITLE = "addTitle";
        String title;

        title = (String) request.getAttribute(TITLE);
        ServiceFactory factory = ServiceFactory.getInstance();
        CategoryService categoryService = factory.getCategoryService();

        try {
            Category newCategory = new Category(title);
            categoryService.initCategory(newCategory);
            response.sendRedirect("Controller?command=ShowCategories");
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"Category isn't added.", e);
            response.sendRedirect("error.jsp");
        }
    }
}
