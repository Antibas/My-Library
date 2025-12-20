package com.antibas.math.geometry.polygon;

import java.util.Collection;

public abstract class ConvexPolygon extends Polygon{
    public ConvexPolygon(int size, Collection<Double> angles, Collection<Double> sides) {
        super(size, angles, sides);
    }
}
