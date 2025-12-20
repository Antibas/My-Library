package com.antibas.math.geometry.polygon;

import java.util.Collection;

public abstract class NonConvexPolygon extends Polygon{
    public NonConvexPolygon(int size, Collection<Double> angles, Collection<Double> sides) {
        super(size, angles, sides);
    }
}
