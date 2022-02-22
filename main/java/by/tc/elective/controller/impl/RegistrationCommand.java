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

public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(RegistrationCommand.class);
        final String NAME = "Name";
        final String LOGIN = "Login";
        final String PASSWORD = "Password";
        final String ROLE = "Role";

        String name;
        String login;
        String password;
        String role;
        int roleID = 0;
        boolean isOk;

        name = request.getParameter(NAME).trim();
        login = request.getParameter(LOGIN).trim();
        password = request.getParameter(PASSWORD).trim();
        role = request.getParameter(ROLE).trim();

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        try {
            switch (role) {
                case "Student":
                    roleID = 3;
                    break;
                case "Teacher":
                    roleID = 2;
                    break;
                case "Admin":
                    roleID = 1;
                    break;
                default:
                    logger.log(Level.ERROR, "User isn't added.");
            }
            User newUser = new User(login, password, name, roleID);
            isOk = userService.registration(newUser);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", newUser);
            if (isOk) {
                response.sendRedirect("Controller?command=GO_TO_INDEX_PAGE");
            } else {
                response.sendRedirect("Controller?command=GO_TO_REGISTRATION_PAGE&errorMessage=" + "Something is wrong. Please, try again");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "User isn't added.", e);
            response.sendRedirect("error.jsp");
        }
    }
}
