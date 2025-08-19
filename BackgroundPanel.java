package kuripotcoders_valorantgunstore;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/**
 *
 * @author kardamo
 */
public class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private boolean imageLoaded = false;

    public BackgroundPanel(String imagePath) {
        loadBackgroundImage(imagePath);
        setOpaque(false); // Allow the background to show through
    }

    public BackgroundPanel(String imagePath, Dimension size) {
        this(imagePath);
        setPreferredSize(size);
    }

    private void loadBackgroundImage(String imagePath) {
        try {
            // Try loading with forward slashes first
            File imageFile = new File(imagePath.replace("\\", "/"));
            
            if (imageFile.exists()) {
                ImageIcon icon = new ImageIcon(imagePath.replace("\\", "/"));
                backgroundImage = icon.getImage();
                imageLoaded = true;
                System.out.println("Background image loaded successfully: " + imagePath);
            } else {
                // Try with getResource as fallback
                java.net.URL imgURL = getClass().getResource("/" + imagePath.replace("\\", "/"));
                if (imgURL != null) {
                    ImageIcon icon = new ImageIcon(imgURL);
                    backgroundImage = icon.getImage();
                    imageLoaded = true;
                    System.out.println("Background image loaded via getResource: " + imagePath);
                } else {
                    System.err.println("Could not find background image: " + imagePath);
                    System.err.println("Tried: " + imageFile.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (imageLoaded && backgroundImage != null) {
            // Scale and draw the image to fit the panel
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            
            if (panelWidth > 0 && panelHeight > 0) {
                g.drawImage(backgroundImage, 0, 0, panelWidth, panelHeight, this);
            }
        }
    }

    /**
     * Check if the background image was loaded successfully
     * @return true if image is loaded, false otherwise
     */
    public boolean isImageLoaded() {
        return imageLoaded;
    }

    /**
     * Change the background image
     * @param imagePath path to the new image
     */
    public void setBackgroundImage(String imagePath) {
        loadBackgroundImage(imagePath);
        repaint();
    }
}
