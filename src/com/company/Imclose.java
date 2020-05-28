package com.company;

import java.awt.image.BufferedImage;

import static com.company.ImageHandler.displayImage;
import static com.company.ImageHandler.saveImage;

public class Imclose{

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
        BufferedImage finalImage = Dilatation(sourceImage);
        finalImage = Erosion(finalImage);
        displayImage(finalImage, "Zamknięcie");
        saveImage(finalImage,"zamkniecie.png");


        BufferedImage finalImage1 = Dilatation(sourceImage);
        displayImage(finalImage1, "Dylatacja");
    }

    private BufferedImage Erosion(BufferedImage sourceImage) {
        BufferedImage afterErosion= new BufferedImage(sourceImage.getWidth(),sourceImage.getHeight(), sourceImage.getType());

        for (int k = 0; k < sourceImage.getHeight(); k++) {
            for (int l = 0; l < sourceImage.getWidth(); l++) {
                afterErosion.getRaster().setSample(l, k, 0, minimumColor(k,l, sourceImage));
            }
        }
        return afterErosion;
    }

    private BufferedImage Dilatation(BufferedImage sourceImage) {
        BufferedImage afterDilatation = new BufferedImage(sourceImage.getWidth(),sourceImage.getHeight(), sourceImage.getType());

        for (int k = 0; k < sourceImage.getHeight(); k++) {
            for (int l = 0; l < sourceImage.getWidth(); l++) {
                afterDilatation.getRaster().setSample(l, k, 0, maximumColor(k, l, sourceImage));
            }
        }
        return afterDilatation;
    }

    private int minimumColor(int x0, int y0, BufferedImage sourceImage) { // funkcja szukająca koloru o najniższej wartosci w promieniu radius
        int minimum = 256;
        for (int i = x0 - radius; i <= x0 + radius; i++)
            for (int j = y0 - radius; j <= y0 + radius; j++) {
                if (i < 0 || i >= sourceImage.getHeight() || j < 0 || j >= sourceImage.getWidth())
                    continue;
                if ((x0 - i) * (x0 - i) + (y0 - j) * (y0 - j) <= radius * radius) { // jeśli jest w obrębie koła

                    int temp = sourceImage.getRaster().getSample(j, i, 0);
                    if (temp < minimum) // szukam minimalna wartosc koloru
                        minimum = temp;
                    if (minimum == 0) return minimum;



                }
            }
        return minimum;
    }

    private int maximumColor(int x0, int y0, BufferedImage sourceImage) { // funkcja szukająca koloru najniższej wartosci w promieniu radius
        int maximum = -1;
        for (int i = x0 - radius; i <= x0 + radius; i++)
            for (int j = y0 - radius; j <= y0 + radius; j++) {
                if (i < 0 || i >= sourceImage.getHeight() || j < 0 || j >= sourceImage.getWidth())
                    continue;
                if ((x0 - i) * (x0 - i) + (y0 - j) * (y0 - j) <= radius * radius) { // jeśli jest w obrębie koła

                    int temp = sourceImage.getRaster().getSample(j, i, 0);
                    if (temp > maximum) // szukam minimalna wartosc koloru
                        maximum = temp;
                    if (maximum == 255) return maximum;
                }
            }
        return maximum;
    }







}
