/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Controllers;

import E_Com_Database.DatabaseWrapper;
import E_Com_Models.Announcement;
import E_Com_Models.AvailableSlot;
import E_Com_Models.GP_Team;
import E_Com_Models.News;
import E_Com_Models.SessionManager;
import E_Com_Models.Staff;
import E_Com_Models.Student;
import E_Com_Models.TeamRecord;
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
public class HomeServlet extends HttpServlet {

    DatabaseWrapper context = new DatabaseWrapper();
    SessionManager session = SessionManager.Get_session_manger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String flag = (String) request.getParameter("flag");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();
        switch (flag) {
            case "Get_announcement": {

                List<Announcement> announcements = context.Get_Announcement();
                JsonElement userObject = gson.toJsonTree((List<Announcement>) announcements);
                myObj.add("announcements", userObject);
                out.println(myObj.toString());
                break;
            }
            case "Get_News": {

                List<News> news = context.Get_News();
                JsonElement userObject = gson.toJsonTree((List<News>) news);
                myObj.add("news", userObject);
                out.println(myObj.toString());
                break;
            }

            case "Get_GP_Team": {
                Student s = (Student)session.current_user;
                List<TeamRecord> team = context.GetStudent_team(s.id);
                JsonElement userObject = gson.toJsonTree((List<TeamRecord>) team);
                myObj.add("team", userObject);
                myObj.addProperty("success", true);
                myObj.addProperty("StaffName", team.get(0).StaffName);
                myObj.addProperty("Description", team.get(0).Description);
                myObj.addProperty("TeamName", team.get(0).TeamName);
                out.println(myObj.toString());
                break;
            }
             case "Get_GP_Team_Staff": {
                Staff s = (Staff)session.current_user;
                GP_Team  teams = context.Get_GP_teams_staff(s.id);
                JsonElement userObject = gson.toJsonTree((GP_Team) teams);
                myObj.add("teams", userObject);
                myObj.addProperty("success", true);
//                myObj.addProperty("StaffName", team.get(0).StaffName);
//                myObj.addProperty("Description", team.get(0).Description);
//                myObj.addProperty("TeamName", team.get(0).TeamName);
                out.println(myObj.toString());
                break;
            }
        }
    }

}
