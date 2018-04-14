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
public class StaffMail {
    public int staff_id;
    public String Name;
    public String Email;

    public StaffMail(int staff_id, String Name, String Email) {
        this.staff_id = staff_id;
        this.Name = Name;
        this.Email = Email;
    }
    
}
