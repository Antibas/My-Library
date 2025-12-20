package com.antibas.math.geometry;

import com.antibas.math.Math2;
import com.antibas.math.function.trigonometrical.PhaseModifier;

import static com.antibas.math.function.trigonometrical.PhaseModifier.DEGREES;
import static com.antibas.math.function.trigonometrical.PhaseModifier.RADIANS;

public interface Shape {
    double getArea();
    void validate();
    PhaseModifier phaseModifier = PhaseModifier.RADIANS;

    static double fromRadians(double angle){
        return phaseModifier == DEGREES?180.0d*angle/ Math2.PI:angle;
    }

    static double fromDegrees(double angle){
        return phaseModifier == RADIANS?Math2.PI*angle/180.0d:angle;
    }
}
