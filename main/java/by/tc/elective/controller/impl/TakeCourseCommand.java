package by.tc.elective.controller.impl;

import by.tc.elective.bean.User;
import by.tc.elective.controller.Command;
import by.tc.elective.service.CourseRunUserInfoService;
import by.tc.elective.service.ServiceException;
import by.tc.elective.service.ServiceFactory;
import by.tc.elective.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TakeCourseCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

