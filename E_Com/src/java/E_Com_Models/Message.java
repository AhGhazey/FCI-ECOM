/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Models;

/**
 *
 * @author aghazey
 */
public class Message {
    public int State;
    public int ID;
    public String Title;
    public String Body;
    public String To;
    public String From;

    public Message(int State, int ID, String Title, String Body, String To, String From) {
        this.State = State;
        this.ID = ID;
        this.Title = Title;
        this.Body = Body;
        this.To = To;
        this.From = From;
    }

    public Message(int State, int ID, String Title, String Body) {
        this.State = State;
        this.ID = ID;
        this.Title = Title;
        this.Body = Body;
    }
    
}
