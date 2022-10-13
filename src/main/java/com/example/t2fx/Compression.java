package com.example.t2fx;

import javafx.geometry.Point2D;

public class Compression {
    public static double[][] compression(double[][] arr, Point2D p, double f){
        //массив для смещения на нач координат
        double[][] temp = {{1, 0, 0}, {0, 1, 0}, {-p.getX(), -p.getY(), 1}};
        double[][] newMatr = new double[4][3];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    newMatr[i][j] += arr[i][k] * temp[k][j];
                }
            }
        }

        double[][] compressionArr = {{1, 0, 0}, {f, 1, 0}, {0, 0, 1}};
        double[][] newArr1 = new double[4][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    newArr1[i][j] += newMatr[i][k] * compressionArr[k][j];
                }
            }
        }

        double[][] tempArr1 = {{1, 0, 0}, {0, 1, 0}, {p.getX(), p.getY(), 1}};
        double[][] newMatr2 = new double[4][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    newMatr2[i][j] += newArr1[i][k] * tempArr1[k][j];
                }
            }
        }

        return newMatr2;
    }

}