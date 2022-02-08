package by.tc.elective.controller.impl;

import by.tc.elective.bean.CourseRunUserInfo;
import by.tc.elective.bean.User;
import by.tc.elective.controller.Command;
import by.tc.elective.service.CourseRunService;
import by.tc.elective.service.CourseRunUserInfoService;
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

public class JoinCourseCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(JoinCourseCommand.class);

        final String USER = "user";
        final String COURSE_ID = "courseID";

        User user;
        int courseID;
        int userID;
        boolean isOk;
        List<Integer> courseRunsByCourse;

        ServiceFactory factory = ServiceFactory.getInstance();
        CourseRunUserInfoService courseRunUserInfoService = factory.getCourseRunUserInfoService();
        CourseRunService courseRunService = factory.getCourseRunService();

        try {
            HttpSession session = request.getSession(true);
            user = (User) session.getAttribute(USER);
            userID = user.getUserID();
            courseID = Integer.parseInt(request.getParameter(COURSE_ID));

            courseRunsByCourse = courseRunService.showCourseRunsByCourse(courseID);
            Integer courseRunID = courseRunsByCourse.get(1);
            CourseRunUserInfo info = new CourseRunUserInfo(userID, courseRunID);
            isOk = courseRunUserInfoService.initUserToCourse(info);

            if(isOk) {
                request.setAttribute("info", "Congratulations!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/courseInfo.jsp");
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
