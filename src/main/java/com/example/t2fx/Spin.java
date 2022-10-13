package com.example.t2fx;

import javafx.geometry.Point2D;

public class Spin {
    public static double[][] spin(double[][] arr, Point2D p, double angle) {
        //массив для смещения на нач. координат
        double[][] temp = {{1, 0, 0}, {0, 1, 0}, {-p.getX(), -p.getY(), 1}};
        double[][] newMatr = new double[4][3];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    newMatr[i][j] += arr[i][k] * temp[k][j];
                }
            }
        }
        angle = (Math.PI * angle) / 180;
        double[][] spinArr = {{Math.cos(angle), Math.sin(angle), 0}, {-Math.sin(angle), Math.cos(angle), 0}, {0, 0, 1}};
        double[][] newArr1 = new double[4][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    newArr1[i][j] += newMatr[i][k] * spinArr[k][j];
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