package com.antibas.math.geometry.polygon.triangle;

public class IsoscelesTriangle extends Triangle{
    public IsoscelesTriangle(double baseAngles, double topAngle, double base, double equalSides) {
        super(baseAngles, topAngle, baseAngles, equalSides, base, equalSides);
    }

    @Override
    public double getHeight() {
        // h^2 + (base/2)^2 == A^2
        // h == sqrt(A^2 - (base/2)^2)
        return Math.sqrt(Math.pow(this.getSide(AIndex), 2) - Math.pow(this.getBase()/2, 2));
    }

    @Override
    public double getBase() {
        return this.getSide(BIndex);
    }
}
