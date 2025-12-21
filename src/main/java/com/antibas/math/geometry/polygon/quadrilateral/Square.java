package com.antibas.math.geometry.polygon.quadrilateral;

import com.antibas.math.geometry.vector.Point;

public class Square extends Rhombus{
    public Square(double A) {
        super(90, A);
    }

    public Square(Point center, double A) {
        super(center, 90, A);
    }
}
