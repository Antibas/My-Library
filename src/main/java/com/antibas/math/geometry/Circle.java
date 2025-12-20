package com.antibas.math.geometry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Circle implements Shape{
    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI*Math.pow(radius, 2);
    }

    @Override
    public void validate() {

    }
}
