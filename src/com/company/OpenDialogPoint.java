package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenDialogPoint {

    private static JDialog dialog;
    JLabel label1 = new JLabel("    x");
    JLabel label2 = new JLabel("    y");
    static JTextField in1Field=new JTextField();
    static JTextField in2Field=new JTextField();
    static String dataX;
    static String dataY;

    static Point myPoint;

    public OpenDialogPoint(){
        JFrame frame=new JFrame();
        dialog=new JDialog(frame, "Mapa odleglosci geodezyjnej", true);
        init();
    }

    void init(){
        dialog.setTitle("Mapa odległosci geodezyjnej");
        dialog.setLayout(new GridLayout(3,2));
        dialog.add(label1);
        dialog.add(in1Field);
        dialog.add(label2);
        dialog.add(in2Field);
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        dialog.add(new JLabel("    Confirm"));
        dialog.add(button);
        dialog.setSize(300,300);
        dialog.setVisible(true);
        getData();
    }

    public static Point getMyPoint() {
        return myPoint;
    }

    private Point getData(){
        dataX = in1Field.getText();
        dataY = in2Field.getText();
        this.myPoint = new Point(Integer.parseInt(dataX), Integer.parseInt(dataY));
        return myPoint;
    }
}


