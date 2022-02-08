package by.tc.elective.controller.impl.delete;

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

public class DeleteCategoryCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(DeleteCategoryCommand.class);
        final String TITLE = "deleteTitle";
        String title;
        int categoryID;
        boolean isOk = false;

        title = (String) request.getAttribute(TITLE);
        ServiceFactory factory = ServiceFactory.getInstance();
        CategoryService categoryService = factory.getCategoryService();

        try {
            categoryID = categoryService.findCategoryByTitle(title);
            if(categoryID!=0) {
                isOk = categoryService.deleteCategory(categoryID);
            }
            if(isOk) {
                response.sendRedirect("Controller?command=ShowCategories&info=" + "Category is deleted.");
            } else {
                response.sendRedirect("Controller?command=ShowCategories&errorMessage=" + "Something is wrong. Please, try again");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Category isn't deleted.", e);
            response.sendRedirect("error.jsp");
        }
    }
}
