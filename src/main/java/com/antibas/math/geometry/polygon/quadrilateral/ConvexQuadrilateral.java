package com.antibas.math.geometry.polygon.quadrilateral;

import com.antibas.math.geometry.polygon.ConvexPolygon;
import com.antibas.math.geometry.vector.Point;
import com.antibas.math.geometry.vector.Point2D;

import java.util.List;

public abstract class ConvexQuadrilateral extends ConvexPolygon {
    protected static int angleAIndex=0, angleBIndex=1, angleCIndex=2, angleDIndex=3;
    protected static int AIndex=0, BIndex=1, CIndex=2, DIndex=3;

    public ConvexQuadrilateral(double angleA, double angleB, double angleC, double angleD, double A, double B, double C, double D) {
        super(4, List.of(angleA, angleB, angleC, angleD), List.of(A, B, C, D));
    }

    public ConvexQuadrilateral(Point center, double angleA, double angleB, double angleC, double angleD, double A, double B, double C, double D) {
        super(center, 4, List.of(angleA, angleB, angleC, angleD), List.of(A, B, C, D));
    }

//    public void validate(){
//        if(this.getAngle(angleAIndex) <= 0 || this.getAngle(angleBIndex) <= 0|| this.getAngle(angleCIndex) <= 0 || this.getAngle(angleDIndex) <= 0 ||
//                this.getSide(AIndex) <= 0 || this.getSide(BIndex) <= 0|| this.getSide(CIndex) <= 0|| this.getSide(DIndex) <= 0)
//            throw new IllegalArgumentException("Negative value");
//        if(this.getAngle(angleAIndex)  + this.getAngle(angleBIndex)  + this.getAngle(angleCIndex) +  this.getAngle(angleDIndex) != 360)
//            throw new IllegalArgumentException("Angles a, b, c and d must sum up to 360 d");
//    }


}
