package controllers;

import javax.servlet.annotation.WebServlet;
import HTTPRequestHandler.HTTPRequestHandler;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import Bolbolestan.Bolbolestan;
import Bolbolestan.Student.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    public void init() throws ServletException {
        try {
            importStudentsFromWeb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("std_id");
        Bolbolestan bolbolestan = Bolbolestan.getInstance();
        if (bolbolestan.doesStudentExist(studentId)) {
            bolbolestan.makeLoggedIn(studentId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void importStudentsFromWeb() throws Exception{
        System.out.println("Importing students..");
        final String studentsURL = "http://138.197.181.131:5000/api/students";
        String StudentsJsonString = HTTPRequestHandler.getRequest(studentsURL);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Student> students = gson.fromJson(StudentsJsonString, new TypeToken<List<Student>>() {
        }.getType());
        for (Student student : students) {
            try {
                student.print();
                Bolbolestan.getInstance().addStudent(student);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}