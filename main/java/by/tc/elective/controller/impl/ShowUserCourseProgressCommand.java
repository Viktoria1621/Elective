package by.tc.elective.controller.impl;

import by.tc.elective.bean.Course;
import by.tc.elective.bean.CourseRun;
import by.tc.elective.bean.CourseRunUserInfo;
import by.tc.elective.bean.User;
import by.tc.elective.controller.Command;
import by.tc.elective.dao.CourseRunUserInfoDAO;
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
import java.util.List;

public class ShowUserCourseProgressCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(ShowUserCourseProgressCommand.class);
        final String USER = "user";

        ServiceFactory factory = ServiceFactory.getInstance();
        CourseRunUserInfoService courseRunUserInfoService = factory.getCourseRunUserInfoService();
        CourseRunService courseRunService = factory.getCourseRunService();

        User user;
        int courseID;
        int userID;
        int userCourseRunID = 0;
        CourseRunUserInfo courseRunUserInfo;
        List<Integer> userCourseRuns;
        List<Integer> courseRunsByCourse;

        try {
            HttpSession session = request.getSession(true);
            user = (User) session.getAttribute(USER);
            userID = user.getUserID();
            courseID = Integer.parseInt(request.getParameter("courseID"));

            courseRunsByCourse = courseRunService.showCourseRunsByCourse(courseID);
            userCourseRuns = courseRunUserInfoService.showAllUserCourseRuns(userID);

            for(int i = 0; i < courseRunsByCourse.size(); i++) {
                for(int j = 0; j < userCourseRuns.size(); j++) {
                    if(courseRunsByCourse.get(i) == userCourseRuns.get(j)) {
                        userCourseRunID = courseRunsByCourse.get(i);
                    }
                }
            }

            courseRunUserInfo = courseRunUserInfoService.findCourseInfoByUser(userID, userCourseRunID);
            if(courseRunUserInfo.isValid()) {
                request.setAttribute("courseRunUserInfo", courseRunUserInfo);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/courseProgress.jsp");
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
