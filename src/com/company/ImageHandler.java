package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandler {

    JFrame frame;
    BufferedImage image;
    String imagePath;

    public ImageHandler(String imagePath) throws IOException {
        this.imagePath = imagePath;

        loadImage();
        displayImage();
        saveImage(image, "lenaaaaaaa.jpg");
    }

    private void loadImage() throws IOException {
        image = ImageIO.read(new File(imagePath));
    }

    private void displayImage(){
        ImageIcon icon = new ImageIcon(image);
        frame = new JFrame("Obraz");
        JLabel label = new JLabel();
        label.setIcon(icon);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
    }

    public void saveImage(BufferedImage image, String name){
        File file = new File(name);
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
