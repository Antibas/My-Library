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
        this.validate();
    }

    public void validate(){
        if(this.getAngle(angleAIndex) <= 0 || this.getAngle(angleBIndex) <= 0|| this.getAngle(angleCIndex) <= 0 ||
                this.getSide(AIndex) <= 0 || this.getSide(BIndex) <= 0|| this.getSide(CIndex) <= 0)
            throw new IllegalArgumentException("Negative value");
        if(this.getAngle(angleAIndex)  + this.getAngle(angleBIndex)  + this.getAngle(angleCIndex)  != 180)
            throw new IllegalArgumentException("Angles a, b and c must sum up to 180 d");
    }

    @Override
    public double getArea() {
        return this.getBase()*this.getHeight()/2;
    }

    public abstract double getHeight();
    public abstract double getBase();
}
