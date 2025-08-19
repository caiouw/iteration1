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
public class Animal {
    private String name, breed, species;
    private int money;
    private ImageIcon icon;

    public Animal(){

    }

    public Animal(String breed, String name, String species, int money){
        this.name = name;
        this.breed = breed;
        this.species = species;
        this.money = money;

        String filepath  = "resources/animals/" + this.species.toLowerCase() + ".jpg";
        // System.out.println(filepath);
        File file = new File(filepath);
        this.icon = file.exists() ? new ImageIcon(filepath) : new ImageIcon("resources/nofile.jpg");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}