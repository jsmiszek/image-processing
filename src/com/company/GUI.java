package com.company;

import javax.swing.*;

public class GUI extends JFrame {

    JButton buttonOpenFile;

    public GUI() {
        //setSize(1280,720);
        setSize(640, 640);
        setTitle("Aplikacja Obrazy");
        setLayout(null);

        buttonOpenFile = new JButton("Otwórz obraz");
        buttonOpenFile.setBounds(50, 50, 200, 50); // nadanie parametrow przyciskowi
        add(buttonOpenFile);
        //buttonOpenFile.addActionListener(this);
    }
}
