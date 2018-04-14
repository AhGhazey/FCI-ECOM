/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Controllers;

import E_Com_Database.DatabaseWrapper;
import E_Com_Models.Department;
import E_Com_Models.Group;
import E_Com_Models.OfficeHour;
import E_Com_Models.SessionManager;
import E_Com_Models.Staff;
import E_Com_Models.StaffMail;
import E_Com_Models.Student;
import E_Com_Models.Top50Record;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class StaffServlet extends HttpServlet {

    DatabaseWrapper context = new DatabaseWrapper();
    SessionManager session = SessionManager.Get_session_manger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String flag = (String) request.getParameter("flag");
        if (flag == null)return;

        switch (flag) {
            case "staff": {
                Staff staff = (Staff) session.current_user;
                request.setAttribute("email", staff.email);
                request.setAttribute("name", staff.user_name);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Staff_home.jsp");
                rd.forward(request, response);
                break;
            }
            case "main": {
                Staff staff = (Staff) session.current_user;
                request.setAttribute("email", staff.email);
                request.setAttribute("name", staff.user_name);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin_home.jsp");
                rd.forward(request, response);
                break;
            }
            case "dean": {
                Staff staff = (Staff) session.current_user;
                request.setAttribute("email", staff.email);
                request.setAttribute("name", staff.user_name);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Dean_home.jsp");
                rd.forward(request, response);
                break;
            }
            case "DGLists":
                List<Group> groups = context.Get_student_groups();
                List<Department> departments = context.Get_student_departments();
                Gson gson = new Gson();
                JsonObject myObj = new JsonObject();
                List<Object> objects = new ArrayList<Object>();
                objects.add(groups);
                objects.add(departments);
                JsonElement userObject = gson.toJsonTree((List<Object>) objects);
                myObj.add("DG", userObject);
                out.println(myObj.toString());
                break;
            case "AS": {
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                float gpa = Float.parseFloat(request.getParameter("gpa"));
                int major = Integer.parseInt(request.getParameter("major"));
                int minor = Integer.parseInt(request.getParameter("minor"));
                int group = Integer.parseInt(request.getParameter("group"));
                int level = Integer.parseInt(request.getParameter("level"));
                int res = context.Insert_student(name, email, password, gpa, major, minor, group, level);
                break;
            }
            case "ASt": {
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String position = request.getParameter("position");
                int role = Integer.parseInt(request.getParameter("role"));
                int department = Integer.parseInt(request.getParameter("department"));
                int res = context.Insert_staff(name, password, email, department, role, position);
                break;
            }
            case "AH": {
                String name = request.getParameter("name");
                int capacity = Integer.parseInt(request.getParameter("capacity"));
                int res = context.Insert_hall(name, capacity);
                break;
            }
            case "AC": {
                String name = request.getParameter("name");
                String code = request.getParameter("code");
                int res = context.Insert_course(name, code);
                break;
            }
            case "AD": {
                String name = request.getParameter("name");
                int res = context.Insert_department(name);
                break;
            }
            case "staffList":{
                List<StaffMail> mails = context.GetStaff_Mails();
                gson = new Gson();
                myObj = new JsonObject();
                userObject = gson.toJsonTree((List<StaffMail>) mails);
                myObj.add("mails", userObject);
                out.println(myObj.toString());
                break;
            }
            
             case "top50":{
                int  department_id = Integer.parseInt(request.getParameter("department"));
                int  level = Integer.parseInt(request.getParameter("level"));
                List<Top50Record> top50list = context.GetTop50(level, department_id);
                gson = new Gson();
                myObj = new JsonObject();
                userObject = gson.toJsonTree((List<Top50Record>) top50list);
                myObj.add("top50list", userObject);
                out.println(myObj.toString());
                break;
            }
              case "staffOfficeHours":{
                int  staff_id = Integer.parseInt(request.getParameter("staff_id"));
                List<OfficeHour> officehours = context.GetStaff_OfficeHours(staff_id);
                gson = new Gson();
                myObj = new JsonObject();
                userObject = gson.toJsonTree((List<OfficeHour>) officehours);
                myObj.add("officehours", userObject);
                out.println(myObj.toString());
                break;
            }
             case "add_office_hours":{
                
                String  day = request.getParameter("day");
                String  place = request.getParameter("place");
                String  from = request.getParameter("from");
                String  to = request.getParameter("to");
                Staff s = (Staff)session.current_user;
                context.Add_office_hours(day, from, to, place, s.id);        
                break;
            }
        }
    }

}
