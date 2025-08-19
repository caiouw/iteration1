/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kuripotcoders_valorantgunstore;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class Abilities {
    private String name, type;
    private int price;
    private ImageIcon icon;

    public Abilities(){

    }

    public Abilities(String name, int price){
        this.name = name;
        this.price = price;

        String filepath = "resources/abilities/" + name + ".png";
        File file = new File(filepath);
        this.icon = file.exists() ? new ImageIcon(filepath) : new ImageIcon("resources/nofile.jpg");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ImageIcon getIcon() {
        return icon;
    }

}    

