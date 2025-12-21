package com.antibas.math.geometry.polygon.quadrilateral;

import com.antibas.math.geometry.vector.Point;

public class Rhombus extends Parallelogram{
    public Rhombus(double baseAngle, double A) {
        super(baseAngle, A, A);
    }

    public Rhombus(Point center, double baseAngle, double A) {
        super(center, baseAngle, A, A);
    }
}
