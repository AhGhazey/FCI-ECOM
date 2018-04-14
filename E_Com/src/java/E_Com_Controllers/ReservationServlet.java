/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Controllers;

import E_Com_Database.DatabaseWrapper;
import E_Com_Models.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aghazey
 */
public class ReservationServlet extends HttpServlet {

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
        switch (flag) {
            case "get_free_slots": {
                String day = request.getParameter("day");
                List<AvailableSlot> slots = context.Get_AvailableSlotses(day);
                JsonElement userObject = gson.toJsonTree((List<AvailableSlot>) slots);
                myObj.add("slots", userObject);
                out.println(myObj.toString());
                break;
            }
            case "make_reservation": {
                String date = request.getParameter("date");
                String[] date_array = date.split(", ");
                int year = Integer.parseInt(date_array[3]);
                int month = Integer.parseInt(date_array[2]);
                int day = Integer.parseInt(date_array[1]);
                String purpose = request.getParameter("purpose");
                int slot_id = Integer.parseInt(request.getParameter("slot_id"));
                int hall_id = Integer.parseInt(request.getParameter("hall_id"));
                int department_id = Integer.parseInt(request.getParameter("department_id"));
                int group_id = Integer.parseInt(request.getParameter("group_id"));
                int level = Integer.parseInt(request.getParameter("level"));
                String res_date = month + "/" + day + "/" + year;
                context.Insert_Reservation(purpose, session.current_user.user_id, slot_id, res_date, level, group_id, department_id);

                break;
            }
            case "GetAllRequests": {
                List<Reservation> reservations = context.GetAllReservation();
                JsonElement userObject = gson.toJsonTree((List<Reservation>) reservations);
                myObj.add("Reservations", userObject);
                out.println(myObj.toString());
                break;
            }
             case "refuse_reservation": {
                 User user = session.current_user;
                 String email = request.getParameter("email");
                int reservation_id = Integer.parseInt(request.getParameter("reservation_id"));
                    context.Refuse_reservation(reservation_id, user.user_id, email);
                 break;
            }
              case "accept_reservation": {
                 int  user_id = session.current_user.user_id;
                 String email = request.getParameter("email");
                int reservation_id = Integer.parseInt(request.getParameter("reservation_id"));
                int level = Integer.parseInt(request.getParameter("level"));
                int group_id = Integer.parseInt(request.getParameter("group"));
                int department_id = Integer.parseInt(request.getParameter("department"));
                String hall = request.getParameter("hall");
                String start_hr = request.getParameter("start_hr");
                String end_hr = request.getParameter("end_hr");
                String date  = request.getParameter("date");
                String day  = request.getParameter("day");
                String gName  = request.getParameter("gName");
                String dName  = request.getParameter("dName");
                String message="Dear, \n we announce that "+hall+" Hall has been reserverd for Group "+gName+
                        ", Department "+dName+ " and Level "+level+", On "+day+" "+date+" from "+start_hr+" to "
                        +end_hr+"\n \n Thanks. \n The Dean";
                
                context.Accept_Reservation(reservation_id, user_id, email, group_id, department_id, level, message);

                break;
            }
        }
    }

}
