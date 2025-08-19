/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kuripotcoders_valorantgunstore;

import java.io.File;

import javax.swing.ImageIcon;


/**
 *
 * @author kardamo
 */
public class Gun {
    private String name, type;
    private int price;
    private ImageIcon icon;

    public Gun(){

    }

    public Gun(String name, String type, int price){
        this.name = name;
        this.type = type;
        this.price = price;

        String filepath = "resources/guns/" + name + ".png";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ImageIcon getIcon() {
        return icon;
    }

}
