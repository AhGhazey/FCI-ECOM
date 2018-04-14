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
  public class TeamRecord{
        public String StaffName;
        public String TeamName;
        public String Description;
        public String StudentName;
        public String ProjectName;
         

        public TeamRecord(String TeamName, String Description, String StudentName, String ProjectName) {
            this.TeamName = TeamName;
            this.Description = Description;
            this.StudentName = StudentName;
            this.ProjectName = ProjectName;
        }
    }
