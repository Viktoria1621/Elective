package by.tc.elective.controller.impl.go_to;

import by.tc.elective.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToSignOutPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("user");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Controller?command=GO_TO_INDEX_PAGE");
        dispatcher.forward(request, response);
    }
}
