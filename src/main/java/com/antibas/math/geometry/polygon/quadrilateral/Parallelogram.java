package com.antibas.math.geometry.polygon.quadrilateral;

import com.antibas.math.geometry.vector.Point;

public class Parallelogram extends ConvexQuadrilateral{
    public Parallelogram(double baseAngle, double A, double B) {
        super(baseAngle, 180-baseAngle, baseAngle, 180-baseAngle, A, B, A, B);
    }

    public Parallelogram(Point center, double baseAngle, double A, double B) {
        super(center, baseAngle, 180-baseAngle, baseAngle, 180-baseAngle, A, B, A, B);
    }

    @Override
    public double getArea() {
        return this.getBase()*this.getHeight();
    }

    public double getHeight(){
        return 0;
    }

    public double getBase() {
        return this.getSide(BIndex);
    }
}
