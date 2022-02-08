package by.tc.elective.controller.impl;

import by.tc.elective.bean.User;
import by.tc.elective.controller.Command;
import by.tc.elective.service.ServiceException;
import by.tc.elective.service.ServiceFactory;
import by.tc.elective.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(SignInCommand.class);
        final String LOGIN = "Login";
        final String PASSWORD = "Password";

        String login;
        String password;

        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        User user;

        try {
            user = userService.signIn(login, password);
            HttpSession session = request.getSession(true);
            if(user.isValid()) {
                session.setAttribute("user", user);
                response.sendRedirect("Controller?command=GO_TO_INDEX_PAGE");
            } else {
                response.sendRedirect("Controller?command=GO_TO_SIGN_IN_PAGE");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "User isn't found.", e);
            response.sendRedirect("Controller?command=GO_TO_ERROR_PAGE");
        }
    }
}
