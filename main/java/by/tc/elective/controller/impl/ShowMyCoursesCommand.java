package by.tc.elective.controller.impl;

import by.tc.elective.bean.Course;
import by.tc.elective.bean.User;
import by.tc.elective.controller.Command;
import by.tc.elective.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowMyCoursesCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(ShowMyCoursesCommand.class);

        int userID;
        User user;
        List<Course> courses = new ArrayList<>();
        List<Integer> usersCourseRunID;
        List<Integer> courseRunCoursesID;

        ServiceFactory factory = ServiceFactory.getInstance();
        CourseService courseService = factory.getCourseService();
        CourseRunUserInfoService courseRunUserInfoService = factory.getCourseRunUserInfoService();
        CourseRunService courseRunService = factory.getCourseRunService();

        try {
            HttpSession session = request.getSession(true);
            user = (User) session.getAttribute("user");
            userID = user.getUserID();
            usersCourseRunID = courseRunUserInfoService.showAllUserCourseRuns(userID);
            courseRunCoursesID = courseRunService.showCourseRunCourse(usersCourseRunID);

            for (Integer c : courseRunCoursesID) {
                courses.add(courseService.findCourseByID(c));
            }

            if (!courses.isEmpty()) {
                request.setAttribute("userCourses", courses);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myCourse.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("info", "You aren't taking any course now.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myCourse.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Courses aren't found.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
