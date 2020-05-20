package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandler {

    JFrame frame;

    BufferedImage sourceImage;
    String imagePath;

    public ImageHandler(){}

    public ImageHandler(String imagePath) throws IOException {
        this.imagePath = imagePath;

        loadImage();
        displayImage(sourceImage, "pierwotny");
        System.out.println(sourceImage.getRGB(1,1));
        System.out.println(sourceImage.getRGB(1,2));
        System.out.println(sourceImage.getRGB(1,3));
    }

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    private void loadImage() throws IOException {
        sourceImage = ImageIO.read(new File(imagePath));
    }

    public void displayImage(BufferedImage image, String nameFrame){
        ImageIcon icon = new ImageIcon(image);
        frame = new JFrame(nameFrame);
        JLabel label = new JLabel();
        label.setIcon(icon);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.setVisible(true);

    }

    public static void saveImage(BufferedImage image, String name){
        File file = new File(name);
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
