package com.company;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import static com.company.ImageHandler.displayImage;
import static com.company.ImageHandler.loadImage;

public class GUI extends JFrame implements ActionListener {

    JButton buttonOpenFile;
    JButton buttonImageClosing;
    JButton buttonImageMap;
    JButton buttonImageFractal;
    JButton buttonImageKirsch;

    BufferedImage sourceImage;
    Imclose imageImclose;
    LengthMap imageAfterMap;
    Fractal imageFractal;
    KirschFilt imageKirsch;


    public GUI() {
        setSize(350, 500);
        setTitle("Aplikacja Obrazy");
        setLayout(null);

        //Map<JButton, String> buttons = new LinkedHashMap<>();

        //buttons.put(buttonOpenFile, "Otwórz obraz");
        //buttons.put(buttonImageClosing, "Zamkniecie");
       // buttons.put(buttonImageMap, "Mapa odleglosci geodezyjnej");


        buttonOpenFile = new JButton("Otwórz obraz");
        buttonOpenFile.setBounds(50, 50, 200, 50); // nadanie parametrow przyciskowi
        add(buttonOpenFile);
        buttonOpenFile.addActionListener(this);


        buttonImageClosing = new JButton("Zamknięcie");
        buttonImageClosing.setBounds(50, 125, 200, 50);
        add(buttonImageClosing);
        buttonImageClosing.addActionListener(this);

        buttonImageMap = new JButton("Mapa odległości");
        buttonImageMap.setBounds(50, 200, 200, 50);
        add(buttonImageMap);
        buttonImageMap.addActionListener(this);

        buttonImageFractal = new JButton("Fraktale");
        buttonImageFractal.setBounds(50, 275, 200, 50);
        add(buttonImageFractal);
        buttonImageFractal.addActionListener(this);

        buttonImageKirsch = new JButton("Filtracja Kirscha");
        buttonImageKirsch.setBounds(50, 350, 200, 50);
        add(buttonImageKirsch);
        buttonImageKirsch.addActionListener(this);

        //addButton(buttons);

    }

   /* private void addButton(Map<JButton, String> buttons){
        int i = 0;
        for(JButton button : buttons.keySet()){
            button = new JButton(buttons.get(button));
            button.setBounds(50, 50 + (i * 75), 200, 50);
            add(button);
            button.addActionListener(this);
            i++;
        }
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (buttonOpenFile.equals(source)) {
            JFileChooser fileChoose = new JFileChooser("C:\\Users\\JusMat\\Desktop\\image-processing");


            if (fileChoose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChoose.getSelectedFile();
                try {
                    sourceImage = loadImage(file.getName());
                    displayImage(sourceImage, "Obraz pierwotny");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    Component frame = null;
                    JOptionPane.showMessageDialog(null, "Błąd otwarcia pliku\n");
                }
            }
        }


        if (buttonImageClosing.equals(source)) {
            System.out.println("Zamkniecie");
            new OpenDialog();
            imageImclose = new Imclose(OpenDialog.getData(), sourceImage);
        }

        if (buttonImageMap.equals(source)){
            System.out.println("Mapa");
            new OpenDialogPoint();
            Point p = OpenDialogPoint.getMyPoint();
            System.out.println(p.x + "  " + p.y);
            imageAfterMap = new LengthMap(OpenDialogPoint.getMyPoint(), sourceImage);
        }

        if (buttonImageFractal.equals(source)) {
            System.out.println("Fraktale");
            imageFractal = new Fractal(sourceImage);
        }

        if (buttonImageKirsch.equals(source)) {
            System.out.println("Kirsch");
            imageKirsch = new KirschFilt(sourceImage);
        }
    }
}
