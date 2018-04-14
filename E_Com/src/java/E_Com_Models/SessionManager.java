package E_Com_Models;


import E_Com_Models.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aghazey
 */
public class SessionManager {
    public User current_user;
    private static SessionManager singlton ;
    private SessionManager (){    
    }
    public static synchronized SessionManager Get_session_manger (){
    
        if (singlton == null){
            singlton = new SessionManager();
            return singlton;
        }
        return singlton;
    }
}
