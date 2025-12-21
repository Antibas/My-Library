package com.antibas.math.geometry.vector;

import com.antibas.util.pair.NamedPair;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Point3D extends Point2D {
    private double z;
    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public Point3D(double x, double y){
        this(x, y, 0d);
    }

    public Point3D(Point2D p){
        this(p.getX(), p.getY(), 0d);
    }

    public Point3D(){
        this(0d, 0d, 0d);
    }
}
