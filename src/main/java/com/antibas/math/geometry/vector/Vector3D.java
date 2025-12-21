package com.antibas.math.geometry.vector;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Vector3D extends Vector2D {
	public Vector3D() {
		this(0,0,0,0,0,0);
	}
	public Vector3D(double x1, double y1, double z1, double x2, double y2, double z2) {
		this.p1 = new Point3D(x1, y1, z1);
		this.p2 = new Point3D(x2, y2, z2);
	}

	public Vector3D(double x2, double y2, double z2) {
		this(0, 0, 0, x2, y2, z2);
	}

	public Vector3D(Vector3D vector) {
		this(vector,
				vector.getP1().getZ(),
				vector.getP2().getZ()
		);
	}
	
	public Vector3D(Vector2D vector, double z1, double z2) {
		this(vector.getP1().getX(),
				vector.getP1().getY(),
				z1,
				vector.getP2().getX(),
				vector.getP2().getY(),
				z2
		);
	}
	
	public Vector3D(Vector2D vector, double z2) {
		this(vector, 0, z2);
	}
	
	public Vector3D(Vector2D vector) {
		this(vector, 0, 0);
	}

    public double getZ() {
		return getP2().getZ()-getP1().getZ();
	}

	@Override
	public Point3D getP1() {
		return (Point3D)super.getP1();
	}

	@Override
	public Point3D getP2() {
		return (Point3D)super.getP2();
	}

	@Override
	public double amplitude() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2));
	}
	@Override
	public boolean isCentered() {
		return super.isCentered() && getP1().getZ() == 0;
	}
	@Override
	public Vector3D getCentered() {
		return new Vector3D(super.getCentered(), getP2().getZ()-getP1().getZ());
	}
	@Override
	public Vector3D getInverse() {
		return new Vector3D(super.getInverse(), getP2().getZ(), getP1().getZ());
	}
	@Override
	public Vector3D getNormalized() {
		return null;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
        result = prime * result + Double.hashCode(getP1().getZ());
        result = prime * result + Double.hashCode(getP2().getZ());
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
		if (Double.doubleToLongBits(getP1().getZ()) != Double.doubleToLongBits(other.getP1().getZ()))
			return false;
		if (Double.doubleToLongBits(getP2().getZ()) != Double.doubleToLongBits(other.getP2().getZ()))
			return false;
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		if(this.isCentered()) {
			return "(" + getP2().getX() + ", " + getP2().getY() + ", " + getP2().getZ() + ")";
		}
		return "(" + getP1().getZ() + ", " + getP1().getY() + ", " + getP1().getZ()  + ") -> (" + getP2().getX() + ", " + getP2().getY() + ", " + getP2().getZ() + ")";
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
