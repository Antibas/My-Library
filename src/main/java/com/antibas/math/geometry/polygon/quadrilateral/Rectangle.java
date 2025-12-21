package com.antibas.math.geometry.polygon.quadrilateral;

import com.antibas.math.geometry.vector.Point;

public class Rectangle extends Parallelogram{
    public Rectangle(double A, double B) {
        super(90, A, B);
    }

    public Rectangle(Point center, double A, double B) {
        super(center, 90, A, B);
    }

    @Override
    public double getHeight(){
        return this.getSide(AIndex);
    }
}
