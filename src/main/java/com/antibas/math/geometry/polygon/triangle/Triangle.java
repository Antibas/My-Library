package com.antibas.math.geometry.polygon.triangle;

import com.antibas.math.geometry.polygon.ConvexPolygon;
import com.antibas.math.geometry.polygon.Polygon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public abstract class Triangle extends ConvexPolygon {
    protected static int angleAIndex=0, angleBIndex=1, angleCIndex=2;
    protected static int AIndex=0, BIndex=1, CIndex=2;

    public Triangle(double angleA, double angleB, double angleC, double A, double B, double C) {
        super(3, List.of(angleA, angleB, angleC), List.of(A, B, C));
    }

    @Override
    public double getArea() {
        return this.getBase()*this.getHeight()/2;
    }
}
