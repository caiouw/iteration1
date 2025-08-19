/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kuripotcoders_valorantgunstore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kardamo
 */
public class Store extends JFrame{
    ArrayList<JPanel> animal_panels = new ArrayList<>();
    ArrayList<JPanel> gun_panels = new ArrayList<>();
    JPanel[] panels = new JPanel[5];
    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Gun> guns = new ArrayList<>();
    ArrayList<Abilities> abilities = new ArrayList<>();
    JFrame frame = new JFrame();
    Color transparent = new Color(128, 128, 128, 180);
    Font DEFAULT_FONT = new Font("Tungsten", Font.BOLD, 15);
    Color DEFAULT_COLOR = new Color(0x73706c);

    public Store() throws FileNotFoundException, IOException{
    frame.setSize(new Dimension(2000, 1400));
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout(35, 0));
    
    // Create and set the background panel with the firing range image
    BackgroundPanel backgroundPanel = new BackgroundPanel("resources/firing range.png", new Dimension(2000, 1400));
    backgroundPanel.setLayout(new BorderLayout(35, 0));
    frame.setContentPane(backgroundPanel);
    
    frame.setResizable(false);

    readAnimals();
    readGuns();
    readAbilities();
    
    setupAnimalPanels();
    setupPanels(); // main panels
    setupGunPanels();
    setupInfoPanel();
    
    frame.pack();
    frame.setVisible(true);
}

    public void readAnimals() throws FileNotFoundException, IOException{
        Scanner scanner = new Scanner(new FileReader("resources/animals.txt"));
        while(scanner.hasNextLine()){
            String[] data = scanner.nextLine().split(",");
            // System.out.println(Arrays.toString(data));
            Animal animal = new Animal(data[0].trim(), data[1].trim(), data[2].trim(), Integer.parseInt(data[3].trim()));
            animals.add(animal);
        }
    }

    public void readGuns() throws FileNotFoundException, IOException{
        Scanner scanner = new Scanner(new FileReader("resources/guns.txt"));
        while(scanner.hasNextLine()){
            String[] data = scanner.nextLine().split(",");
            System.out.println(Arrays.toString(data));
            Gun gun = new Gun(data[0].trim(), data[1].trim(), Integer.parseInt(data[2].trim()));
            guns.add(gun);
        }
    }
    
    public void readAbilities() throws FileNotFoundException, IOException{
        Scanner scanner = new Scanner(new FileReader("resources/abilities.txt"));
        while(scanner.hasNextLine()){
            String[] data = scanner.nextLine().split(",");
            System.out.println(Arrays.toString(data));
            Abilities ability = new Abilities(data[0].trim(), Integer.parseInt(data[1].trim()));
            abilities.add(ability);
        }
    }    
    

    public void setupPanels(){
    panels[0] = createPanel(new Dimension(350, 0), Color.BLACK); // W
    panels[1] = createPanel(new Dimension(350, 0), DEFAULT_COLOR); // E
    panels[2] = createPanel(new Dimension(0, 0), Color.BLACK); // C
    panels[3] = createPanel(new Dimension(0, 100), Color.BLACK); // N
    panels[4] = createPanel(new Dimension(0, 100), Color.BLACK); // S
    
    for(JPanel panel : animal_panels){
        panels[0].add(panel);
    }
        
    // Get the background panel (content pane) and add panels to it
    BackgroundPanel backgroundPanel = (BackgroundPanel) frame.getContentPane();
    backgroundPanel.add(panels[0], BorderLayout.WEST);
    backgroundPanel.add(panels[1], BorderLayout.EAST);
    backgroundPanel.add(panels[2], BorderLayout.CENTER);
    backgroundPanel.add(panels[3], BorderLayout.NORTH);
    backgroundPanel.add(panels[4], BorderLayout.SOUTH);
}

    public void setupAnimalPanels(){
        for (Animal ani : animals){
            JPanel panel = createPanel(new Dimension(330, 100), transparent);
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
            
            JLabel label = new JLabel(ani.getName());
            label.setFont(DEFAULT_FONT);
            label.setForeground(Color.GRAY);
            ImageIcon icon = new ImageIcon(ani.getIcon().getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
            label.setIcon(icon);
            label.setVerticalTextPosition(JLabel.TOP);

            JLabel money = new JLabel("# " + ani.getMoney());
            money.setVerticalAlignment(JLabel.TOP);
            money.setFont(DEFAULT_FONT);
            money.setForeground(Color.GRAY);
            
            panel.add(label, BorderLayout.CENTER);
            panel.add(money, BorderLayout.EAST);

            animal_panels.add(panel);
        }
    }
    
    public void setupGunPanels(){
        JPanel[] gun_partition = new JPanel[4];
        gun_partition[0] = createPanel(new Dimension(150, 0), Color.BLACK); // W - sidearms
        gun_partition[1] = createPanel(new Dimension(150, 0), DEFAULT_COLOR); // E - armor
        gun_partition[2] = createPanel(new Dimension(1400, 0), DEFAULT_COLOR); // C - rifles
        gun_partition[3] = createPanel(new Dimension(1400, 250), DEFAULT_COLOR); // S - abilities

        // Set vertical layout for sidearms panel
        gun_partition[0].setLayout(new BoxLayout(gun_partition[0], BoxLayout.Y_AXIS));

        // Use BorderLayout for abilities section to have header on top
        gun_partition[3].setLayout(new BorderLayout());

        // Create the header
        JPanel ability_header = createPanel(new Dimension(150, 20), "abilities");
        gun_partition[3].add(ability_header, BorderLayout.NORTH);

        // Create container for the ability icons with your working GridLayout
        JPanel abilitiesContainer = new JPanel();
        abilitiesContainer.setLayout(new GridLayout(1, 0, 5, 5)); // Keep your working layout
        abilitiesContainer.setBackground(DEFAULT_COLOR);

        // Add abilities to the container
        for(Abilities ab : abilities) {
            abilitiesContainer.add(createPanel(new Dimension(399, 220), ab));
        }

        // Add the abilities container to the center
        gun_partition[3].add(abilitiesContainer, BorderLayout.CENTER);

        // adding to panels[2] center
        panels[2].setLayout(new BorderLayout(30, 30));
        panels[2].add(gun_partition[0], BorderLayout.WEST); // sidearm
        panels[2].add(gun_partition[1], BorderLayout.EAST); // armor
        panels[2].add(gun_partition[2], BorderLayout.CENTER); // rifles
        panels[2].add(gun_partition[3], BorderLayout.SOUTH); // abilities

        // sidearms
        JPanel sidearm = createPanel(new Dimension(150, 20), "sidearms");
        gun_partition[0].add(sidearm);
        gun_partition[0].add(Box.createVerticalStrut(5)); // Add space after header
        for(Gun gun : guns){
            if(gun.getType().equalsIgnoreCase("sidearms")){
                gun_partition[0].add(createPanel(new Dimension(150, 165), gun));
                gun_partition[0].add(Box.createVerticalStrut(5)); // Add space between each sidearm
            }
        }
    }
    
    public void setupInfoPanel(){
        JPanel[] info_partition = new JPanel[1];
        info_partition[0] = createPanel(new Dimension(350, 0), DEFAULT_COLOR);
        
        panels[1].setLayout(new BorderLayout(30, 30));
        panels[1].add(info_partition[0], BorderLayout.NORTH);
        
        JPanel info = createPanel(new Dimension(150, 20), "info");
        info_partition[0].add(info);
  

    }

    public JPanel createPanel(Dimension dim){
        JPanel panel = new JPanel();

        panel.setPreferredSize(dim);

        return panel;
    }

    public JPanel createPanel(Dimension dim, Color col){
        JPanel panel = new JPanel();

        panel.setPreferredSize(dim);
        panel.setBackground(col);

        return panel;
    }

    public JPanel createPanel(Dimension dim, String text){
        JPanel panel = new JPanel();

        panel.setPreferredSize(dim);
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(63, 99, 84, 150)); // transparent dark green
        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        JLabel label = new JLabel(text.toUpperCase());
        label.setFont(DEFAULT_FONT);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);
        panel.add(label);

        return panel;
    }
    
    private JPanel currentlySelectedGunPanel = null;
    private JPanel currentlySelectedAbilityPanel = null;

    public JPanel createPanel(Dimension dim, Gun gun){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(dim);
        panel.setBackground(new Color(0x73706c));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                if (currentlySelectedGunPanel != panel) {
                    panel.setBackground(new Color(0x48be93));
                    panel.repaint();
                }
            }
            @Override
            public void mouseExited(MouseEvent e){
                if (currentlySelectedGunPanel != panel) {
                    panel.setBackground(new Color(0x73706c));
                    panel.repaint();
                }
            }
            @Override
            public void mouseClicked(MouseEvent e){
                // Clear previous selection
                if (currentlySelectedGunPanel != null && currentlySelectedGunPanel != panel) {
                    currentlySelectedGunPanel.setBackground(new Color(0x73706c));
                    currentlySelectedGunPanel.setBorder(null);
                    currentlySelectedGunPanel.repaint();
                }

                // Toggle current selection
                if (currentlySelectedGunPanel == panel) {
                    // Deselect current panel
                    panel.setBackground(new Color(0x73706c));
                    panel.setBorder(null);
                    currentlySelectedGunPanel = null;
                } else {
                    // Select this panel
                    panel.setBackground(new Color(32, 80, 57, 150));
                    panel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0x48be93), 2),
                        BorderFactory.createEmptyBorder(1, 1, 1, 1)
                    ));
                    currentlySelectedGunPanel = panel;
                }
                panel.repaint();
            }
        });

        JLabel label = new JLabel("# " + gun.getPrice() + " | " + gun.getName());
        label.setFont(DEFAULT_FONT);
        label.setForeground(Color.LIGHT_GRAY);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon icon = new ImageIcon(gun.getIcon().getImage().getScaledInstance(120, 75, Image.SCALE_SMOOTH));
        label.setIcon(icon);

        panel.add(label, BorderLayout.CENTER);

        return panel;
    }
    
    public JPanel createPanel(Dimension dim, Abilities ability){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(dim);
        panel.setBackground(new Color(0x73706c));
        panel.setOpaque(true); // Ensure panel is opaque

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                if (currentlySelectedAbilityPanel != panel) {
                    panel.setBackground(new Color(0x48be93));
                    panel.revalidate();
                    panel.repaint();
                }
            }
            @Override
            public void mouseExited(MouseEvent e){
                if (currentlySelectedAbilityPanel != panel) {
                    panel.setBackground(new Color(0x73706c));
                    panel.revalidate();
                    panel.repaint();
                }
            }
            @Override
            public void mouseClicked(MouseEvent e){
                // Clear previous selection
                if (currentlySelectedAbilityPanel != null && currentlySelectedAbilityPanel != panel) {
                    currentlySelectedAbilityPanel.setBackground(new Color(0x73706c));
                    currentlySelectedAbilityPanel.setBorder(null);
                    currentlySelectedAbilityPanel.revalidate();
                    currentlySelectedAbilityPanel.repaint();
                }

                // Toggle current selection
                if (currentlySelectedAbilityPanel == panel) {
                    // Deselect current panel
                    panel.setBackground(new Color(0x73706c));
                    panel.setBorder(null);
                    currentlySelectedAbilityPanel = null;
                } else {
                    // Select this panel
                    panel.setBackground(new Color(32, 80, 57, 150));
                    panel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0x48be93), 2),
                        BorderFactory.createEmptyBorder(1, 1, 1, 1)
                    ));
                    currentlySelectedAbilityPanel = panel;
                }
                panel.revalidate();
                panel.repaint();

                // Force repaint of parent container
                if (panel.getParent() != null) {
                    panel.getParent().repaint();
                }
            }
        });

        JLabel label = new JLabel("# " + ability.getPrice() + " | " + ability.getName());
        label.setFont(DEFAULT_FONT);
        label.setForeground(Color.LIGHT_GRAY);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(false); // Make label transparent so panel background shows

        ImageIcon icon = new ImageIcon(ability.getIcon().getImage().getScaledInstance(120, 75, Image.SCALE_SMOOTH));
        label.setIcon(icon);

        panel.add(label, BorderLayout.CENTER);

        return panel;
    }
}
