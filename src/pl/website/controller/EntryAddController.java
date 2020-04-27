package pl.website.controller;

import pl.website.model.User;
import pl.website.service.EntryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class EntryAddController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("inputName");
        String description = request.getParameter("inputDescription");
        String url = request.getParameter("inputUrl");
        User authenticatedUser = (User) request.getSession().getAttribute("user");
        if (request.getUserPrincipal() != null) {
            EntryService entryService = new EntryService();
            entryService.addEntry(name, description, url, authenticatedUser);
            response.sendRedirect(request.getContextPath() + "/");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getUserPrincipal() != null)
            request.getRequestDispatcher("WEB-INF/new.jsp").forward(request, response);
        else
            response.sendError(403);

    }
}
