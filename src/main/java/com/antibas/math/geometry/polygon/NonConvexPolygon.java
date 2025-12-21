package com.antibas.math.geometry.polygon;

import com.antibas.math.geometry.vector.Point;
import com.antibas.math.geometry.vector.Point2D;

import java.util.Collection;

public abstract class NonConvexPolygon extends Polygon{
    public NonConvexPolygon(Point center, int size, Collection<Double> angles, Collection<Double> sides) {
        super(center, size, angles, sides);
    }

    public NonConvexPolygon(int size, Collection<Double> angles, Collection<Double> sides) {
        super(size, angles, sides);
    }
}
