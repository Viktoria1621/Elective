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

public class ProfileEditingCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = LogManager.getLogger(RegistrationCommand.class);
        final String NAME = "Name";
        final String LOGIN = "Login";
        final String PASSWORD = "Password";
        final String USER = "user";

        String newName;
        String newLogin;
        String newPassword;
        String name;
        String login;
        String password;
        int role;
        int userID;
        User user;
        boolean isOk;

        newName = request.getParameter(NAME).trim();
        newLogin = request.getParameter(LOGIN).trim();
        newPassword = request.getParameter(PASSWORD).trim();

        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            UserService userService = factory.getUserService();
            HttpSession session = request.getSession(true);
            user = (User) session.getAttribute(USER);
            userID = user.getUserID();
            role = user.getRoleID();

            if (!newLogin.equals("")) {
                login = newLogin;
            } else login = user.getLogin();

            if (!newPassword.equals("")) {
                password = newPassword;
            } else password = user.getPassword();

            if (!newName.equals("")) {
                name = newName;
            } else name = user.getName();

            User newUser = new User(userID, login, password, name, role);
            isOk = userService.updateUser(newUser, userID);
            session.setAttribute("user", newUser);

            if (!isOk) {
                request.setAttribute("error", "Something is wrong.");
            }
            response.sendRedirect("Controller?command=GO_TO_PROFILE_PAGE");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "User isn't updated.", e);
            response.sendRedirect("error.jsp");
        }
    }
}
