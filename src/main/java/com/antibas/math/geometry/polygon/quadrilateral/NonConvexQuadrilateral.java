package com.antibas.math.geometry.polygon.quadrilateral;

import com.antibas.math.geometry.polygon.NonConvexPolygon;
import com.antibas.math.geometry.vector.Point;

import java.util.Collection;
import java.util.List;

public abstract class NonConvexQuadrilateral extends NonConvexPolygon {
    public NonConvexQuadrilateral(double angleA, double angleB, double angleC, double angleD, double A, double B, double C, double D) {
        super(4, List.of(angleA, angleB, angleC, angleD), List.of(A, B, C, D));
    }

    public NonConvexQuadrilateral(Point center, double angleA, double angleB, double angleC, double angleD, double A, double B, double C, double D) {
        super(center, 4, List.of(angleA, angleB, angleC, angleD), List.of(A, B, C, D));
    }
}
