/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Controllers;

import E_Com_Database.DatabaseWrapper;
import E_Com_Models.AvailableSlot;
import E_Com_Models.Message;
import E_Com_Models.SessionManager;
import E_Com_Models.User;
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
public class MessageServlet extends HttpServlet {
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
            case "anonMessage": {
             String email = request.getParameter("email");
             String title = request.getParameter("title");
             String message = request.getParameter("message");
             int result = context.Send_contact_us(email, title, message);
             if (result >0)
                 myObj.addProperty("success", true);
             else 
                 myObj.addProperty("success", false);
             out.println(myObj.toString()); 
                break;
            }
            case "message": {
             String email = request.getParameter("email");
             String title = request.getParameter("title");
             String message = request.getParameter("message");
             User user = session.current_user;
             int result = context.Send_Message(user.user_id,email, title, message);
             if (result >0)
                 myObj.addProperty("success", true);
             else 
                 myObj.addProperty("success", false);
             out.println(myObj.toString()); 
                break;
            }
            case "inbox": { 
                User user = session.current_user;
                List<Message> messages = context.GetInbox(user.user_id);
                gson = new Gson();
                myObj = new JsonObject();
                JsonElement userObject = gson.toJsonTree((List<Message>) messages);
                myObj.add("messages", userObject);
                out.println(myObj.toString());
             break;
            }
             case "outbox": { 
                User user = session.current_user;
                List<Message> messages = context.GetOutbox(user.user_id);
                gson = new Gson();
                myObj = new JsonObject();
                JsonElement userObject = gson.toJsonTree((List<Message>) messages);
                myObj.add("messages", userObject);
                out.println(myObj.toString());
             break;
            }
             case "readMessage": { 
                User user = session.current_user;
             break;
            }
        }
    }

   

}
