package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {

    JButton buttonOpenFile;
    JButton buttonQuitProgram;
    JButton buttonImageErode;

    ImageHandler image;
    Imclose imageAfterErode;


    public GUI() {
        //setSize(1280,720);
        setSize(640, 640);
        setTitle("Aplikacja Obrazy");
        setLayout(null);

        buttonOpenFile = new JButton("Otwórz obraz");
        buttonOpenFile.setBounds(50, 50, 200, 50); // nadanie parametrow przyciskowi
        add(buttonOpenFile);
        buttonOpenFile.addActionListener(this);


        buttonImageErode = new JButton("Erozja");
        buttonImageErode.setBounds(50, 125, 200, 50);
        add(buttonImageErode);
        buttonImageErode.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (buttonOpenFile.equals(source)) {
            JFileChooser fileChoose = new JFileChooser("C:\\Users\\JusMat\\Desktop\\image-processing");


            if (fileChoose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChoose.getSelectedFile();
                try {
                    image = new ImageHandler(file.getName());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    Component frame = null;
                    JOptionPane.showMessageDialog(null, "Błąd otwarcia pliku\n");
                }
            }
        }


        if (buttonImageErode.equals(source)) {

            System.out.println("Erozja Obrazu");
            new OpenDialog();
            imageAfterErode = new Imclose(OpenDialog.getData(), image.getSourceImage());
        }
    }
}
