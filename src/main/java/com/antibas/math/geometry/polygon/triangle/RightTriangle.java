package com.antibas.math.geometry.polygon.triangle;

import com.antibas.math.geometry.vector.Point;
import com.antibas.math.geometry.vector.Point2D;

public class RightTriangle extends Triangle{


    public RightTriangle(double angleA, double angleB, double A, double B, double C) {
        super(angleA, angleB, 90, A, B, C);
    }

    public RightTriangle(Point center, double angleA, double angleB, double A, double B, double C) {
        super(center, angleA, angleB, 90, A, B, C);
    }

    @Override
    public double getHeight() {
        return this.getSide(AIndex);
    }

    @Override
    public double getBase() {
        return this.getSide(BIndex);
    }

    @Override
    public void validate() {
        super.validate();
        if(Math.pow(this.getSide(AIndex), 2) + Math.pow(this.getSide(BIndex), 2) != Math.pow(this.getSide(CIndex), 2)){
            throw new IllegalArgumentException("Triangle doesn't follow the Pythagorean Theorem.");
        }
    }
}
