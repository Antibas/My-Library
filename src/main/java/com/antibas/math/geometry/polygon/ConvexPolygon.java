package com.antibas.math.geometry.polygon;

import java.util.Arrays;
import java.util.Collection;

public abstract class ConvexPolygon extends Polygon{
    public ConvexPolygon(int size, Collection<Double> angles, Collection<Double> sides) {
        super(size, angles, sides);
        this.validate();
    }

    @Override
    public void validate() {
        super.validate();
        if(Arrays.stream(this.angles).reduce(0.0d, Double::sum) != (angles.length- 2)*180)
            throw new IllegalArgumentException("Angles must sum up to "+ (angles.length- 2)*180);
    }

    public abstract double getHeight();
    public abstract double getBase();
}
