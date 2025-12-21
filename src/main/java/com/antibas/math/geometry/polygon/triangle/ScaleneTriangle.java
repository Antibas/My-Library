package com.antibas.math.geometry.polygon.triangle;

import com.antibas.math.geometry.vector.Point;
import com.antibas.math.geometry.vector.Point2D;

public class ScaleneTriangle extends Triangle{
    public ScaleneTriangle(double angleA, double angleB, double angleC, double A, double B, double C) {
        super(angleA, angleB, angleC, A, B, C);
    }

    public ScaleneTriangle(Point center, double angleA, double angleB, double angleC, double A, double B, double C) {
        super(center, angleA, angleB, angleC, A, B, C);
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getBase() {
        return this.getSide(BIndex);
    }
}
