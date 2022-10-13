package com.example.t2fx;

import javafx.geometry.Point2D;

public class Compression {
    public static double[][] compression(double[][] arr, Point2D p, double f){
        double[][] tempArr = {{1, 0, 0}, {0, 1, 0}, {-p.getX(), -p.getY(), 1}}; //массив для смещения на "нач. к-т"
        double[][] newArr = new double[4][3];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    newArr[i][j] += arr[i][k] * tempArr[k][j];
                }
            }
        }

        double[][] compressionArr = {{1, 0, 0}, {f, 1, 0}, {0, 0, 1}};
        double[][] newArr1 = new double[4][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    newArr1[i][j] += newArr[i][k] * compressionArr[k][j];
                }
            }
        }

        double[][] tempArr1 = {{1, 0, 0}, {0, 1, 0}, {p.getX(), p.getY(), 1}}; //массив для смещения обратно
        double[][] newArr2 = new double[4][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    newArr2[i][j] += newArr1[i][k] * tempArr1[k][j];
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = newArr2[i][j];
            }
        }

        return arr;
    }

}