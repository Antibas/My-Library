package com.antibas.math.geometry.polygon;

import com.antibas.math.geometry.Shape;

import java.util.Arrays;
import java.util.Collection;

public abstract class Polygon implements Shape {
    protected final Double[] angles, sides;

    public Polygon(int size, Collection<Double> angles, Collection<Double> sides) {
        if(angles.size() != size)
            throw new IllegalArgumentException();
        this.angles = angles.toArray(new Double[0]);
        this.sides = sides.toArray(new Double[0]);
    }

    public double getAngle(int i){
        return angles[i];
    }

    public void setAngle(int i, double a){
        angles[i] = a;
    }

    public double getSide(int i){
        return sides[i];
    }

    public void setSide(int i, double a){
        sides[i] = a;
    }

    @Override
    public double getLength() {
        return Arrays.stream(this.sides).reduce(0.0d, Double::sum);
    }

    @Override
    public void validate() {
        if(angles.length != sides.length)
            throw new IllegalArgumentException();
        if(Arrays.stream(angles).anyMatch(d -> d <=0))
            throw new IllegalArgumentException("Negative or zero value");
        if(Arrays.stream(sides).anyMatch(d -> d <=0))
            throw new IllegalArgumentException("Negative or zero value");
    }
}
