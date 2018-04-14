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
public class News {
    public int id;
    public String title;
    public String image_src;
    public String content;

    public News(int id, String title, String image_src, String content) {
        this.id = id;
        this.title = title;
        this.image_src = image_src;
        this.content = content;
    }

    public News() {
    }
    
    
}
