package com.antibas.math.geometry.polygon;

import com.antibas.math.geometry.Baseable;
import com.antibas.math.geometry.vector.Point;
import com.antibas.math.geometry.vector.Point2D;

import java.util.Arrays;
import java.util.Collection;

public abstract class ConvexPolygon extends Polygon implements Baseable {
    public ConvexPolygon(Point center, int size, Collection<Double> angles, Collection<Double> sides) {
        super(center, size, angles, sides);
        this.validate();
    }

    public ConvexPolygon(int size, Collection<Double> angles, Collection<Double> sides) {
        this(new Point2D(), size, angles, sides);
    }

    @Override
    public void validate() {
        super.validate();
        if(Arrays.stream(this.angles).reduce(0.0d, Double::sum) != (angles.length- 2)*180)
            throw new IllegalArgumentException("Angles must sum up to "+ (angles.length- 2)*180);
    }
}
