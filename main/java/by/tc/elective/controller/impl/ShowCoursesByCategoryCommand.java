package by.tc.elective.controller.impl;

import by.tc.elective.bean.Category;
import by.tc.elective.bean.Course;
import by.tc.elective.controller.Command;
import by.tc.elective.service.CategoryService;
import by.tc.elective.service.CourseService;
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

public class ShowCoursesByCategoryCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(ShowCoursesByCategoryCommand.class);
        final String CATEGORY_ID = "categoryID";

        String categoryID = request.getParameter(CATEGORY_ID);

        ServiceFactory factory = ServiceFactory.getInstance();
        CourseService courseService = factory.getCourseService();
        CategoryService categoryService = factory.getCategoryService();
        List<Course> coursesByCategory;
        Category category;

        try {
            coursesByCategory = courseService.findCourseByCategory(Integer.parseInt(categoryID));
            category = categoryService.findCategoryByID(Integer.parseInt(categoryID));
            if (!coursesByCategory.isEmpty()) {
                request.setAttribute("coursesByCategory", coursesByCategory);
                request.setAttribute("categoryTitle", category.getCategoryTitle());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/course.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Courses aren't found.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
