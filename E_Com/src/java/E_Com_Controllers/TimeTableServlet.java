/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Controllers;

import E_Com_Database.DatabaseWrapper;
import E_Com_Models.Hall;
import E_Com_Models.SessionManager;
import E_Com_Models.Staff;
import E_Com_Models.Student;
import E_Com_Models.TimeTableCell;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aghazey
 */
public class TimeTableServlet extends HttpServlet {

    DatabaseWrapper context = new DatabaseWrapper();
    SessionManager session = SessionManager.Get_session_manger();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String flag = request.getParameter("flag");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();
        //List<Object> objects = new ArrayList<Object>();
        //JsonElement userObject = gson.toJsonTree((List<Object>) objects);
        //myObj.add("DG", userObject);
        //out.println(myObj.toString());
        switch(flag){
            case "get_halls":
            {
                List<Hall> halls = context.Get_Faculty_Halls();
                JsonElement userObject = gson.toJsonTree((List<Hall>) halls);
                myObj.add("Halls", userObject);
                out.println(myObj.toString());
                break;
            }
            case "add_slot":
            {
                String day = request.getParameter("day");
                int hall_id = Integer.parseInt(request.getParameter("hall_id"));
                int slot_number = Integer.parseInt(request.getParameter("slot_number"));
                String slot_time = request.getParameter("time_slot");
                String [] se = slot_time.split("-");
                String start_time = se[0];
                String end_time = se[1];
                boolean isfree = true;
                int res = context.Insert_timeslot(hall_id, slot_number, isfree, day, start_time, end_time);
                break;
            }
            
             case "get_time_table":
            {
                
              if (session.current_user.role == 1) {
                Student student = (Student)session.current_user;
                List<TimeTableCell> cells = context.GetStudentTimeTable(student.id);
                JsonElement userObject = gson.toJsonTree((List<TimeTableCell>) cells);
                myObj.add("cells", userObject);
                out.println(myObj.toString());
              }else {
                Staff staff = (Staff)session.current_user;
                List<TimeTableCell> cells = context.GetStaffTimeTable(staff.id);
                JsonElement userObject = gson.toJsonTree((List<TimeTableCell>) cells);
                myObj.add("cells", userObject);
                out.println(myObj.toString());
              }
                break;
            }
             case "get_gtime_table":
            {
                
                int level = Integer.parseInt(request.getParameter("level"));
                int group = Integer.parseInt(request.getParameter("group"));
                int department = Integer.parseInt(request.getParameter("department"));
                List<TimeTableCell> cells = context.GetGroupTimeTable(department, level, group);
                JsonElement userObject = gson.toJsonTree((List<TimeTableCell>) cells);
                myObj.add("cells", userObject);
                out.println(myObj.toString());
             
                break;
            }
                
        }
       
    }

    
   

}
