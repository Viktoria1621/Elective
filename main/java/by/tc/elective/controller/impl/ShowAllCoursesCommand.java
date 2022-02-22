package by.tc.elective.controller.impl;

import by.tc.elective.bean.Course;
import by.tc.elective.controller.Command;
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

public class ShowAllCoursesCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(ShowAllCoursesCommand.class);

        ServiceFactory factory = ServiceFactory.getInstance();
        CourseService courseService = factory.getCourseService();
        List<Course> courses;

        try {
            courses = courseService.showAllCourses();
            if (!courses.isEmpty()) {
                request.setAttribute("courses", courses);
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
