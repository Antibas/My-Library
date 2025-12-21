package com.antibas.math.geometry;

import com.antibas.math.Math2;
import com.antibas.math.function.trigonometrical.PhaseModifier;
import com.antibas.math.geometry.vector.Point;
import com.antibas.math.geometry.vector.Point2D;
import com.antibas.math.geometry.vector.Point3D;
import com.antibas.math.geometry.vector.Vector2D;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Shape {
    protected Point center;

    public Shape(Point center){
        this.center = center;
    }

    public Shape(double x, double y){
        this.center = new Point2D(x, y);
    }

    public Shape(double x, double y, double z){
        this.center = new Point3D(x, y, z);
    }

    public Shape(){
        this(0,0);
    }

    public abstract double getArea();
    public abstract double getLength();
    protected abstract void validate();

    public static PhaseModifier phaseModifier = PhaseModifier.RADIANS;

    public static double fromRadians(double angle){
        return phaseModifier == PhaseModifier.DEGREES?180.0d*angle/ Math2.PI:angle;
    }

    public static double fromDegrees(double angle){
        return phaseModifier == PhaseModifier.RADIANS?Math2.PI*angle/180.0d:angle;
    }
}
