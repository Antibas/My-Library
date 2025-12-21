package com.antibas.math.geometry;

import com.antibas.math.Math2;
import com.antibas.math.function.trigonometrical.PhaseModifier;

public interface Shape {
    double getArea();
    double getLength();
    void validate();
    PhaseModifier phaseModifier = PhaseModifier.RADIANS;

    static double fromRadians(double angle){
        return phaseModifier == PhaseModifier.DEGREES?180.0d*angle/ Math2.PI:angle;
    }

    static double fromDegrees(double angle){
        return phaseModifier == PhaseModifier.RADIANS?Math2.PI*angle/180.0d:angle;
    }
}
