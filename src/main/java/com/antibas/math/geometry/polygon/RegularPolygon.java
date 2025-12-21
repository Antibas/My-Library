package com.antibas.math.geometry.polygon;

import com.antibas.math.geometry.vector.Point;
import com.antibas.util.ArrayUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class RegularPolygon extends ConvexPolygon{
    public RegularPolygon(Point center, int size, double side) {
        super(center,
                size,
                Arrays.stream(ArrayUtils.fillDouble(size, (size - 2) * 180.0d / size)).boxed().toList(),
                Arrays.stream(ArrayUtils.fillDouble(size, side)).boxed().toList()
        );
    }

    public RegularPolygon(int size, int side) {
        super(size,
                Arrays.stream(ArrayUtils.fillDouble(size, (size - 2) * 180.0d / size)).boxed().toList(),
                Arrays.stream(ArrayUtils.fillDouble(size, side)).boxed().toList()
        );
    }

    public double getApothem(){
        return this.getSide(0)/(2*Math.tan((double) 180 /this.sides.length));
    }

    @Override
    public double getHeight() {
        return this.getApothem();
    }

    @Override
    public double getBase() {
        return this.getSide(0);
    }

    @Override
    public double getArea() {
        return this.sides.length*this.getBase()*this.getApothem()/2;
    }
}
