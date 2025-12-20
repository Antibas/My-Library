package com.antibas.math.geometry.polygon.quadrilateral;

public class Rectangle extends Parallelogram{
    public Rectangle( double A, double B) {
        super(90, A, B);
    }

    @Override
    public double getHeight(){
        return this.getSide(AIndex);
    }
}
