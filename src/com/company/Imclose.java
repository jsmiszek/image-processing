package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Imclose extends ImageHandler{

    int radius;                     //promien kola - elementu strukturalnego
    BufferedImage sourceImage;

    public Imclose(int radius, BufferedImage image){
        this.radius = radius;
        this.sourceImage = image;
        closing();
    }

    private void closing(){
        if(radius <= 0){
            System.out.println("Radius must be greater than 0!");
        }
        BufferedImage finalImage = Erosion(Dilatation());
        displayImage(finalImage, "po zamknieciu");
        saveImage(finalImage,"po_erozji.png");
    }

    private BufferedImage Erosion(BufferedImage sourceImage) {
        BufferedImage afterErosion= new BufferedImage(sourceImage.getWidth(),sourceImage.getHeight(), BufferedImage.TYPE_INT_RGB);//TYPE_BYTE_GRAY);

        for (int k = 0; k < sourceImage.getHeight(); k++) {
            for (int l = 0; l < sourceImage.getWidth(); l++) {
                //afterErode.getRaster().setSample(k, l, 0, minimumColor(k,l));
                afterErosion.setRGB(k,l,minimumColor(k,l));
            }
        }
        return afterErosion;
    }

    private BufferedImage Dilatation() {
        BufferedImage afterDilatation = new BufferedImage(sourceImage.getWidth(),sourceImage.getHeight(), BufferedImage.TYPE_INT_RGB);//TYPE_BYTE_BINARY);//TYPE_BYTE_GRAY);

        for (int k = 0; k < sourceImage.getHeight(); k++) {
            for (int l = 0; l < sourceImage.getWidth(); l++) {
                //afterDilatation.getRaster().setSample(k, l, 0, maximumColor(k,l));
                afterDilatation.setRGB(k,l,maximumColor(k,l));
            }
        }
        return afterDilatation;
    }

    private int minimumColor(int x0, int y0) { // funkcja szukająca koloru w skali szarości o najniższej wartosci w promieniu radius
        int minimum = 256;
        for (int i = x0 - radius; i <= x0 + radius; i++)
            for (int j = y0 - radius; j <= y0 + radius; j++) {
                if (i < 0 || i >= sourceImage.getHeight() || j < 0 || j >= sourceImage.getWidth())
                    continue;
                if ((x0 - i) * (x0 - i) + (y0 - j) * (y0 - j) <= radius * radius) { // jeśli jest w obrębie koła

//                    int temp = sourceImage.getRaster().getSample(i, j, 0);
                    int temp = sourceImage.getRGB(i, j);
                    if (temp < minimum) // szukam minimalna wartosc koloru
                        minimum = temp;
                    if (minimum == 0) return minimum;



                }
            }
        return minimum;
    }

    private int maximumColor(int x0, int y0) { // funkcja szukająca koloru w skali szarości o najniższej wartosci w promieniu radius
        int maximum = -1;
        for (int i = x0 - radius; i <= x0 + radius; i++)
            for (int j = y0 - radius; j <= y0 + radius; j++) {
                if (i < 0 || i >= (int) sourceImage.getHeight() || j < 0 || j >= (int) sourceImage.getWidth())
                    continue;
                if ((x0 - i) * (x0 - i) + (y0 - j) * (y0 - j) <= radius * radius) { // jeśli jest w obrębie koła

                   // int temp = sourceImage.getRaster().getSample(i, j, 0);
                    int temp = sourceImage.getRGB(i, j);
                    if (temp > maximum) // szukam minimalna wartosc koloru
                        maximum = temp;
                    if (maximum == 255) return maximum;
                }
            }
        return maximum;
    }







}
