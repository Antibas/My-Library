package com.antibas.math.geometry.polygon.trapezoid;

import com.antibas.math.geometry.polygon.ConvexPolygon;

import java.util.Collection;
import java.util.List;

public class Trapezoid extends ConvexPolygon {
    protected static int angleAIndex=0, angleBIndex=1, angleCIndex=2, angleDIndex=3;
    protected static int AIndex=0, BIndex=1, CIndex=2, DIndex=3;

    public Trapezoid(double baseAngle1, double baseAngle2, double A, double B, double C, double D) {
        super(4, List.of(baseAngle1, 180-baseAngle1, baseAngle2, 180-baseAngle2), List.of(A, B, C, D));
    }

    @Override
    public double getArea() {
        return getBase()*getBase2()*getHeight()/2;
    }

    @Override
    public double getHeight(){
        return 0;
    }

    @Override
    public double getBase(){
        return this.getSide(AIndex);
    }

    public double getBase2(){
        return this.getSide(CIndex);
    }
}
