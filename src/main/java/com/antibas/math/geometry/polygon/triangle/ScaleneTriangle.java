package com.antibas.math.geometry.polygon.triangle;

public class ScaleneTriangle extends Triangle{
    public ScaleneTriangle(double angleA, double angleB, double angleC, double A, double B, double C) {
        super(angleA, angleB, angleC, A, B, C);
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
