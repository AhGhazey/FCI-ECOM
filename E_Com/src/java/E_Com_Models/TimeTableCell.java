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
public class TimeTableCell {

    public String Day;
    public int Index;
    public String StaffName;
    public String CourseName;
    public String HallName;
    public String DepartmentName;
    public String GroupName;
    public int Level;
    public int day_index;

    public TimeTableCell(String Day, int Index, String StaffName, String CourseName, String HallName, String DepartmentName, String GroupName, int Level) {
        this.Day = Day;
        this.Index = Index;
        this.StaffName = StaffName;
        this.CourseName = CourseName;
        this.HallName = HallName;
        this.DepartmentName = DepartmentName;
        this.GroupName = GroupName;
        this.Level = Level;

        switch (Day) {
            case "Saturday": {
                day_index = 1;
                break;
            }
            case "Sunday": {
                day_index = 2;
                break;
            }
            case "Monday": {
                day_index = 3;
                break;
            }
            case "Tuesday": {
                day_index = 4;
                break;
            }
             case "Wednesday": {
                day_index = 5;
                break;
            }
              case "Thursday": {
                day_index = 6;
                break;
            }
               case "Friday": {
                day_index = 7;
                break;
            }
        };
    }

}
