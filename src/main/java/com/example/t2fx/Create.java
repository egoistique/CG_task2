package com.example.t2fx;

import javafx.geometry.Point2D;

public class Create {
    public static double[][] create(double x0, double y0, double wight, double height){
        Point2D p0 = new Point2D(x0, y0);
        Point2D p1 = new Point2D(x0 + wight, y0);
        Point2D p2 = new Point2D(x0, y0 + height);
        Point2D p3 = new Point2D(x0 + wight, y0 + height);

        double[][] matr = {{p0.getX(), p0.getY(), 1}, {p1.getX(), p1.getY(), 1}, {p2.getX(), p2.getY(), 1}, {p3.getX(), p3.getY(), 1}};
        return matr;
    }
}
