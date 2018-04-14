/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aghazey
 */
public class GP_Team {
  
    public Map<Integer,List<TeamRecord> > project_members ;
    
    public GP_Team(){
        project_members = new HashMap<>();
    }
}
