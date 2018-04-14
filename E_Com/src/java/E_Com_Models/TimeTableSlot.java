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
public class TimeTableSlot {
    public int id;
    public  int hall_id;
    public String day;
    public String start_hr;
    public String end_hr;
    public String hall_name;
    public boolean isfree;

    public TimeTableSlot() {
    }
    
    public int slot_number;
        public TimeTableSlot(int id, int hall_id, String day, String start_hr, String end_hr, boolean isfree, int slot_number) {
        this.id = id;
        this.hall_id = hall_id;
        this.day = day;
        this.start_hr = start_hr;
        this.end_hr = end_hr;
        this.isfree = isfree;
        this.slot_number = slot_number;
    }
}
