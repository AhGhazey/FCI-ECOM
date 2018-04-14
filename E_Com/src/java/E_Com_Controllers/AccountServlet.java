/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Controllers;

import E_Com_Database.DatabaseWrapper;
import E_Com_Models.Staff;
import E_Com_Models.Student;
import E_Com_Models.User;
import E_Com_Models.SessionManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.Clock;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aghazey
 */
@WebServlet(name = "AccountServlet", urlPatterns = {"/AccountServlet"})
public class AccountServlet extends HttpServlet {
    SessionManager session = SessionManager.Get_session_manger();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        
        
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        DatabaseWrapper context = new DatabaseWrapper();
        User user = context.Check_login(userName, password);
        session.current_user = user;
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        
        if (user == null){
            myObj.addProperty("success", false);
            out.println(myObj.toString());
        }else if (user instanceof Student){
            JsonElement userObject = gson.toJsonTree((Student)user);
            myObj.addProperty("success", true);
            myObj.add("User", userObject);
            out.println(myObj.toString());               
        }
        else if (user instanceof Staff){
            JsonElement userObject = gson.toJsonTree((Staff)user);
            myObj.addProperty("success", true);
            myObj.add("User", userObject);
            out.println(myObj.toString());   
        }
        out.close();
    }



}
