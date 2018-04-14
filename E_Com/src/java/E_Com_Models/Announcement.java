/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E_Com_Models;

import java.util.logging.Logger;

/**
 *
 * @author aghazey
 */
public class Announcement {
    
    public int id;
    public int level;
    public String title;
    public String content;

    public Announcement(int id, int level, String title, String content) {
        this.id = id;
        this.level = level;
        this.title = title;
        this.content = content;
    }

    public Announcement() {
    }
    private static final Logger LOG = Logger.getLogger(Announcement.class.getName());
    
}
