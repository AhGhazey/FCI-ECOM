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
public class OfficeHour {
    public String Day;
    public String StartTime;
    public String EndTime;
    public String Place;

    public OfficeHour(String Day, String StartTime, String EndTime, String Place) {
        this.Day = Day;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.Place = Place;
    }
    
    
}
