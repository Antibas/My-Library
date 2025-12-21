package com.antibas.math.geometry.polygon.triangle;

import com.antibas.math.geometry.vector.Point;
import com.antibas.math.geometry.vector.Point2D;

public class EquilateralTriangle extends IsoscelesTriangle{
    public EquilateralTriangle(double A) {
        super(60, 60, A, A);
    }

    public EquilateralTriangle(Point center, double A) {
        super(center,60, 60, A, A);
    }
}
