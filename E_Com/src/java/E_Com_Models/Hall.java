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
public class Hall {
    public int id;
    public int capcity;
    public String Hall_name;

    public Hall(int id, int capcity, String Hall_name) {
        this.id = id;
        this.capcity = capcity;
        this.Hall_name = Hall_name;
    }

    public Hall() {
    }
    
}
