package com.company;

import java.awt.image.BufferedImage;
import java.util.Random;

import static com.company.ImageHandler.*;

public class Fractal {

    BufferedImage image;
    double [][] a1 = {{-0.67, -0.02, 0}, {-0.18, 0.81, 10}, {0, 0, 1}};
    double [][] a2 = {{0.4, 0.4, 0}, {-0.1, 0.4, 0}, {0, 0, 1}};
    double [][] a3 = {{-0.4, -0.4, 0}, {-0.1, 0.4, 0}, {0, 0, 1}};
    double [][] a4 = {{-0.1, 0, 0,}, {0.44, 0.44, -2}, {0, 0, 1}};

    public Fractal(BufferedImage sourceImage){
        this.image = sourceImage;
        makeFractal();
    }

    double [][] c = {{0,1},{1,1}};
    double [][] d = {{0,1,1},{0,0,1}};
    private void makeFractal(){

        /*BufferedImage finalImage = generateImage();
        displayImage(finalImage, "Fraktal");
        saveImage(finalImage,"fraktal.png");*/


    }

    private BufferedImage generateImage(){

        Random rand = new Random();
        int width = image.getWidth();
        int height = image.getHeight();
        double x = rand.nextInt(width + 1); //losuje piksel początkowy
        double y = rand.nextInt(height + 1);
        int z = 1;

        //przy mnożeniu : kolumny == wiersze
        double [][] macierz = {{x, y, z}};
        int losowa;

        BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                finalImage.getRaster().setSample(j, i, 0, 0);


        for( int i = 0; i < 50000000; i++ ){
            losowa = rand.nextInt(4);           //losuję liczbę z przedziału 0-4

            switch(losowa){
                case 0:
                    macierz = matrixMultiply(a1, macierz);
                case 1:
                    macierz = matrixMultiply(a2, macierz);
                case 2:
                    macierz = matrixMultiply(a3, macierz);
                case 3:
                    macierz = matrixMultiply(a4, macierz);
            }

            x = Math.round(20 * macierz[0][0] + width/2);
            y = Math.round(20 * macierz[0][1] + height/2);
            if(x >= 0 && x <= width && y >= 0 && y <= height)
                if((finalImage.getRaster().getSample((int)x, (int)y, 0)) < 255)       //jeśli x i y mieści sie w obrazie to wartość piksela zwiększam o 1 (o ile nie osiągnął już wartości 255)
                    finalImage.getRaster().setSample((int)x, (int)y, 0, finalImage.getRaster().getSample((int)x, (int)y, 0) + 1);
        }

        return finalImage;
    }

    private double[][] matrixMultiply(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];
        System.out.println(a.length);
        System.out.println(b[0].length);
        for(int i = 0; i < a.length; ++i) {
            for(int j = 0; j < b[0].length; ++j) {
                c[i][j] = 0;

                for(int k = 0; k < b.length; ++k) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }


}
