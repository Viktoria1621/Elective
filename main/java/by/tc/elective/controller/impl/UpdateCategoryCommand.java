package by.tc.elective.controller.impl;

import by.tc.elective.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCategoryCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String ADD_CATEGORY = "AddCategory";
        final String DELETE_CATEGORY = "DeleteCategory";

        String addTitle;
        String deleteTitle;

        addTitle = request.getParameter(ADD_CATEGORY);
        deleteTitle = request.getParameter(DELETE_CATEGORY);

        if (!addTitle.equals("")) {
            request.setAttribute("addTitle", addTitle);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Controller?command=AddNewCategory");
            dispatcher.forward(request, response);
        }

        if (!deleteTitle.equals("")) {
            request.setAttribute("deleteTitle", deleteTitle);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Controller?command=DeleteCategory");
            dispatcher.forward(request, response);
        }
    }
}
