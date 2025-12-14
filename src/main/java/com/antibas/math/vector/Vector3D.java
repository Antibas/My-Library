package com.antibas.math.vector;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Vector3D extends Vector2D {
	protected double z1;
	protected double z2;
	public Vector3D() {
		super();
		this.z1 = 0;
		this.z2 = 0;
	}
	public Vector3D(double x1, double y1, double z1, double x2, double y2, double z2) {
		super(x1, y1, x2, y2);
		this.z1 = z1;
		this.z2 = z2;
	}
	public Vector3D(double x2, double y2, double z2) {
		super(x2, y2);
		this.z1 = 0;
		this.z2 = z2;
	}
	public Vector3D(Vector3D vector) {
		super(vector);
		this.z1 = vector.z1;
		this.z2 = vector.z2;
	}
	
	public Vector3D(Vector2D vector, double z1, double z2) {
		super(vector);
		this.z1 = z1;
		this.z2 = z2;
	}
	
	public Vector3D(Vector2D vector, double z2) {
		this(vector, 0, z2);
	}
	
	public Vector3D(Vector2D vector) {
		this(vector, 0, 0);
	}

    public double getZ() {
		return z2-z1;
	}
	
	@Override
	public Iterable<Double> getFirstPoint() {
		List<Double> l = new ArrayList<>();
		for(double d: super.getFirstPoint()) {
			l.add(d);
		}
		l.add(z1);
		return l;
	}
	@Override
	public Iterable<Double> getSecondPoint() {
		List<Double> l = new ArrayList<>();
		for(double d: super.getSecondPoint()) {
			l.add(d);
		}
		l.add(z2);
		return l;
	}
	@Override
	public double amplitude() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2));
	}
	@Override
	public boolean isCentered() {
		return super.isCentered() && z1 == 0;
	}
	@Override
	public Vector3D getCentered() {
		return new Vector3D(super.getCentered(), z2-z1);
	}
	@Override
	public Vector3D getInverse() {
		return new Vector3D(super.getInverse(), z2, z1);
	}
	@Override
	public Vector3D getNormalized() {
		return null;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
        result = prime * result + Double.hashCode(z1);
        result = prime * result + Double.hashCode(z2);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector3D other = (Vector3D) obj;
		if (Double.doubleToLongBits(z1) != Double.doubleToLongBits(other.z1))
			return false;
		if (Double.doubleToLongBits(z2) != Double.doubleToLongBits(other.z2))
			return false;
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		if(this.isCentered()) {
			return "(" + x2 + ", " + y2 + ", " + z2 + ")";
		}
		return "(" + x1 + ", " + y1 + ", " + z1  + ") -> (" + x2 + ", " + y2 + ", " + z2 + ")";
	}
	
	public static Vector3D det(Vector3D vector1, Vector3D vector2) {
		double x1 = vector1.getX(),
			   x2 = vector1.getY(),
			   x3 = vector1.getZ(),
			   y1 = vector2.getX(),
			   y2 = vector2.getY(),
			   y3 = vector2.getZ();
		return new Vector3D(x2*y3 - x3*y2, x3*y1 - x1*y2, x1*y2 - x2*y1);
	}
}
