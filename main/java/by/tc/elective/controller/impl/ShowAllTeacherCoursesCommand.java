package by.tc.elective.controller.impl;

import by.tc.elective.bean.Course;
import by.tc.elective.bean.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowAllTeacherCoursesCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(ShowAllTeacherCoursesCommand.class);
        final String USER = "user";

        ServiceFactory factory = ServiceFactory.getInstance();
        CourseService courseService = factory.getCourseService();
        int teacherID;
        User user;
        List<Course> teacherCourses;

        try {
            HttpSession session = request.getSession(true);
            user = (User) session.getAttribute(USER);
            teacherID = user.getUserID();
            teacherCourses = courseService.findCourseByTeacher(teacherID);
            if (!teacherCourses.isEmpty()) {
                request.setAttribute("teacherCourses", teacherCourses);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myCourse.jsp");
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
