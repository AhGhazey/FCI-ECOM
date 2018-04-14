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
public class AvailableSlot {
    public String day;
    public int hall_id;
    public int slot_id;
    public String start_hr;
    public String end_hr;
    public String hall_name;
    public int hall_capacity;

    public AvailableSlot(String day, int hall_id, int slot_id, String start_hr, String end_hr, String hall_name, int hall_capcity) {
        this.day = day;
        this.hall_id = hall_id;
        this.slot_id = slot_id;
        this.start_hr = start_hr;
        this.end_hr = end_hr;
        this.hall_name = hall_name;
        this.hall_capacity = hall_capcity;
    }

    public AvailableSlot() {
    }
    
    
}
