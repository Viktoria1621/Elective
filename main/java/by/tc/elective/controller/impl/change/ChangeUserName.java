package by.tc.elective.controller.impl.change;

import by.tc.elective.bean.User;
import by.tc.elective.controller.Command;
import by.tc.elective.service.ServiceFactory;
import by.tc.elective.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeUserName implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String USER_ID = "userID";
        final String USER_LOGIN = "login";
        final String USER_PASSWORD = "password";
        final String NEW_NAME = "Name";
        final String ROLE_ID = "roleID";

        int userID;
        String login;
        String password;
        String name;
        int roleID;

        HttpSession session = request.getSession();
        userID = (int) session.getAttribute(USER_ID);
        login = (String) session.getAttribute(USER_LOGIN);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

    }
}
