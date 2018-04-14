/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Controllers;

import E_Com_Database.DatabaseWrapper;
import E_Com_Models.SessionManager;
import E_Com_Models.Student;
import E_Com_Models.Student_Courses;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aghazey
 */
public class StudentServlet extends HttpServlet {

    SessionManager session = SessionManager.Get_session_manger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = (Student) session.current_user;
        request.setAttribute("email", student.email);
        request.setAttribute("name", student.user_name);
        request.setAttribute("group", student.group_name);
        request.setAttribute("major", student.major);
        request.setAttribute("level", student.level);
        if (student.level == 4) {
            request.setAttribute("minor", student.minor);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Student_home.jsp");
        rd.forward(request, response);
        //response.sendRedirect("Student_home.jsp");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        DatabaseWrapper context = new DatabaseWrapper();
        Student student = (Student) session.current_user;
        List<Student_Courses> scourses = context.Get_student_courses(student.id);
        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();
        if (scourses.size() > 0) {
            JsonElement userObject = gson.toJsonTree((List<Student_Courses>) scourses);
            myObj.addProperty("success", true);
            myObj.add("scourses", userObject);
            out.println(myObj.toString());
        } else {
            myObj.addProperty("success", false);
            out.println(myObj.toString());
        }

    }

}
