package by.tc.elective.controller.impl.go_to;

import by.tc.elective.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSignInPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp");
        request.setAttribute("errorMessage", "Wrong login or password. Please, try again.");
        dispatcher.forward(request, response);
    }
}
