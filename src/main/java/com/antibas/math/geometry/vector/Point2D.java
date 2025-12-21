package com.antibas.math.geometry.vector;

import com.antibas.util.pair.NamedPair;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Point2D implements Point{
    private double x, y;
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(double x){
        this(x, 0d);
    }

    public Point2D(){
        this(0d, 0d);
    }

    @Override
    public double getZ() {
        throw new UnsupportedOperationException();
    }
}
