package com.company;

import java.awt.image.BufferedImage;

import static com.company.ImageHandler.displayImage;
import static com.company.ImageHandler.saveImage;

public class KirschFilt {

    BufferedImage image;

    public KirschFilt(BufferedImage sourceImage){

        this.image = sourceImage;
        
        BufferedImage finalImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        double[] color;

        if(image.getType() == 10) {
            for (int i = 1; i < image.getHeight() - 1; i++) {
                for (int j = 1; j < image.getWidth() - 1; j++) {
                    color = filtrate(image, j, i, 1);                       //flaga 1 jeśli obraz jest mono
                    finalImage.getRaster().setSample(j, i, 0, color[0]);
                }
            }
        } else{
            for (int i = 1; i < image.getHeight() - 1; i++) {
                for (int j = 1; j < image.getWidth() - 1; j++) {
                    color = filtrate(image, j, i, 0);                       //flaga 0 gdy obraz jest RGB
                    finalImage.getRaster().setSample(j, i, 0, color[0]);
                    finalImage.getRaster().setSample(j, i, 1, color[1]);
                    finalImage.getRaster().setSample(j, i, 2, color[2]);
                }
            }
        }
        displayImage(finalImage, "Filtr Kirscha");
        saveImage(finalImage,"kirschFilt.png");
    }
    

    private double[] filtrate(BufferedImage image, int i, int j, int flag)
    {
        int[][] mask1 = {{5, 5, 5}, {-3, 0, -3}, {-3, -3, -3}};
        int[][] mask2 = {{-3, 5, 5}, {-3, 0, 5}, {-3, -3, -3}};
        int[][] mask3 = {{-3, -3, 5}, {-3, 0, 5}, {-3, -3, 5}};
        int[][] mask4 = {{-3, -3, -3}, {-3, 0, 5}, {-3, 5, 5}};
        int[][] mask5 = {{-3, -3, -3}, {-3, 0, -3}, {5, 5, 5}};
        int[][] mask6 = {{-3, -3, -3}, {5, 0, -3}, {5, 5, -3}};
        int[][] mask7 = {{5, -3, -3}, {5, 0, -3}, {5, -3, -3}};
        int[][] mask8 = {{5, 5, -3}, {5, 0, -3}, {-3, -3, -3}};
        double[] temp;

        double maxRed;
        double maxGreen;
        double maxBlue;
        double tmp;

        maxRed = filter(image, i, j, mask1, 0);
        tmp = filter(image, i, j, mask2, 0);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filter(image, i, j, mask3, 0);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filter(image, i, j, mask4, 0);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filter(image, i, j, mask5, 0);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filter(image, i, j, mask6, 0);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filter(image, i, j, mask7, 0);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filter(image, i, j, mask8, 0);
        if (tmp > maxRed) maxRed = tmp;

        maxRed = (maxRed + 15) / 30;
        temp = new double[]{maxRed, 0, 0};

        if(flag == 0) {
            maxGreen = filter(image, i, j, mask1, 1);
            tmp = filter(image, i, j, mask2, 1);
            if (tmp > maxGreen) maxGreen = tmp;
            tmp = filter(image, i, j, mask3, 1);
            if (tmp > maxGreen) maxGreen = tmp;
            tmp = filter(image, i, j, mask4, 1);
            if (tmp > maxGreen) maxGreen = tmp;
            tmp = filter(image, i, j, mask5, 1);
            if (tmp > maxGreen) maxGreen = tmp;
            tmp = filter(image, i, j, mask6, 1);
            if (tmp > maxGreen) maxGreen = tmp;
            tmp = filter(image, i, j, mask7, 1);
            if (tmp > maxGreen) maxGreen = tmp;
            tmp = filter(image, i, j, mask8, 1);
            if (tmp > maxGreen) maxGreen = tmp;

            maxBlue = filter(image, i, j, mask1, 2);
            tmp = filter(image, i, j, mask2, 2);
            if (tmp > maxBlue) maxBlue = tmp;
            tmp = filter(image, i, j, mask3, 2);
            if (tmp > maxBlue) maxBlue = tmp;
            tmp = filter(image, i, j, mask4, 2);
            if (tmp > maxBlue) maxBlue = tmp;
            tmp = filter(image, i, j, mask5, 2);
            if (tmp > maxBlue) maxBlue = tmp;
            tmp = filter(image, i, j, mask6, 2);
            if (tmp > maxBlue) maxBlue = tmp;
            tmp = filter(image, i, j, mask7, 2);
            if (tmp > maxBlue) maxBlue = tmp;
            tmp = filter(image, i, j, mask8, 2);
            if (tmp > maxBlue) maxBlue = tmp;

            maxBlue = (maxBlue + 15) / 30;
            maxGreen = (maxGreen + 15) / 30;
            temp = new double[]{maxRed, maxGreen, maxBlue};
        }

        return temp;
    }

    private double filter(BufferedImage image, int i, int j, int[][] mask, int flag) {
        double color = image.getRaster().getSample(i - 1, j - 1, flag) * mask[0][0];
        color += image.getRaster().getSample(i - 1, j, flag) * mask[0][1];
        color += image.getRaster().getSample(i - 1, j + 1, flag) * mask[0][2];
        color += image.getRaster().getSample(i, j - 1, flag) * mask[1][0];
        color += image.getRaster().getSample(i, j + 1, flag) * mask[1][2];
        color += image.getRaster().getSample(i + 1, j - 1, flag) * mask[2][0];
        color += image.getRaster().getSample(i + 1, j, flag) * mask[2][1];
        color += image.getRaster().getSample(i + 1, j + 1, flag) * mask[2][2];
        return color;
    }
}