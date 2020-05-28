package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import static com.company.ImageHandler.displayImage;
import static com.company.ImageHandler.saveImage;

public class LengthMap {

    Point myPoint;
    BufferedImage image;
    int radius;

    public LengthMap(Point point, BufferedImage sourceImage){
        this.myPoint = point;
        this.image = sourceImage ;
        this.radius = 5;

        BufferedImage finalImage = makeMap(image);
        displayImage(finalImage, "Odległości geodezyjne");
        saveImage(finalImage,"mapaOdleglosci.png");
    }

    private BufferedImage makeMap(BufferedImage image){
        int [][] marker = new int [image.getWidth()][image.getHeight()];
        int [][] distance = new int [image.getWidth()][image.getHeight()];
        BufferedImage finalImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        for( int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                distance[i][j] = -1;
            }
        }
        distance[myPoint.x][myPoint.y] = 0;

        int minX = myPoint.x;
        int maxX = myPoint.x;
        int minY = myPoint.y;
        int maxY = myPoint.y;

        int iter = 1;
        int flaga;
        int temp;
        while(true){
            flaga = 0;
            minX--;
            maxX++;
            minY--;
            maxY++;

            for( int i = minY; i <= maxY; i++){
                for( int j = minX; j <= maxX; j++){
                    if( i >= 0 && i < image.getHeight() && j >= 0 && j < image.getWidth()) {
                        if (image.getRaster().getSample(j, i, 0) == 1) {
                            marker[i][j] = 1;
                            if ( distance[i][j] == -1) {
                                distance[i][j] = iter;
                                flaga++;
                            }
                        }
                    }
                }
            }
            iter++;
            if(flaga == 0)
                break;
        }
        iter--;

        for (int i = 0; i < image.getHeight(); i++){
            for( int j = 0; j < image.getWidth(); j++){
                if( distance[i][j] > 0){
                    temp = distance[i][j] * 255/ iter;
                }else{
                    temp = 0;
                }
                finalImage.getRaster().setSample(i, j, 0, temp);
            }
        }

        return finalImage;
    }
}
