package com.antibas.math.geometry.polygon.trapezoid;

import com.antibas.math.geometry.vector.Point;
import com.antibas.math.geometry.vector.Point2D;

public class IsoscelesTrapezoid extends Trapezoid{
    public IsoscelesTrapezoid(double baseAngle1, double baseAngle2, double base1, double base2, double equalSides) {
        super(baseAngle1, baseAngle2, base1, equalSides, base2, equalSides);
    }

    public IsoscelesTrapezoid(Point center, double baseAngle1, double baseAngle2, double base1, double base2, double equalSides) {
        super(center, baseAngle1, baseAngle2, base1, equalSides, base2, equalSides);
    }
}
