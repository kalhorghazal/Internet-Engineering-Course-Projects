package controllers;

import Bolbolestan.Bolbolestan;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CoursesController", urlPatterns = "/courses")
public class CoursesController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bolbolestan bolbolestan = Bolbolestan.getInstance();
        if (bolbolestan.isAnybodyLoggedIn()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("courses.jsp");
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("http://localhost:8080/ca3_war_exploded/login");
        }
    }
}