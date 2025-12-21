package com.antibas.math.geometry.vector;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Vector2D {
	protected Point2D p1, p2;

	public Vector2D(double x1, double y1, double x2, double y2) {
		this.p1 = new Point2D(x1, y1);
		this.p2 = new Point2D(x2, y2);
	}

	public Vector2D(Point2D p1, Point2D p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Vector2D(double x2, double y2) {
		this(0, 0, x2, y2);
	}

	public Vector2D(Point2D p2) {
		this(new Point2D(), p2);
	}

	public Vector2D(Vector2D vector) {
		this(vector.p1, vector.p2);
	}

	public Vector2D() {
		this(0,0);
	}

    public double getX() {
		return p2.getX()-p1.getX();
	}

	public double getY() {
		return p2.getY()-p1.getY();
	}

	public double amplitude() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
	}

	public double angle() {
		return Math.atan((getY())/(getX()));
	}

	public boolean isCentered() {
		return p1.getX() == 0 && p1.getY() == 0;
	}

	public Vector2D getCentered() {
		return new Vector2D(getX(), getY());
	}

	public Vector2D getInverse() {
		return new Vector2D(p2.getX(), p2.getY(), p1.getX(), p1.getY());
	}

	public Vector2D getNormalized() {
		return div(new Vector2D(p1.getX(), p1.getY(), p2.getX(), p2.getY()), this.amplitude());
	}

	public static Vector2D add(Vector2D vector, double a) {
		return new Vector2D(vector.p1.getX()+a, vector.p2.getX()+a, vector.p1.getY()+a, vector.p2.getY()+a);
	}

	public static Vector2D sub(Vector2D vector, double a) {
		return new Vector2D(vector.p1.getX()-a, vector.p2.getX()-a, vector.p1.getY()-a, vector.p2.getY()-a);
	}

	public static Vector2D mul(Vector2D vector, double a) {
		return new Vector2D(vector.p1.getX()*a, vector.p2.getX()*a, vector.p1.getY()*a, vector.p2.getY()*a);
	}

	public static Vector2D div(Vector2D vector, double a) {
		return new Vector2D(vector.p1.getX()/a, vector.p2.getX()/a, vector.p1.getY()/a, vector.p2.getY()/a);
	}

	public static Vector2D add(Vector2D vector1, Vector2D vector2) {
		if(vector1.getP1().equals(vector2.getP1())) {
			return new Vector2D(vector1.p1.getX(), vector1.p1.getY(), vector1.p2.getX()+vector2.p2.getX(), vector1.p2.getY()+vector2.p2.getY());
		}
		if(vector1.getP2().equals(vector2.getP2())) {
			return new Vector2D(vector1.p1.getX(), vector1.p1.getY(), vector2.p2.getX(), vector2.p2.getY());
		}
		return null;
	}

	public static Vector2D sub(Vector2D vector1, Vector2D vector2) {
		return Vector2D.add(vector1, vector2.getInverse());
	}

	public static double dot(Vector2D vector1, Vector2D vector2) {
		return vector1.getX()*vector2.getX()+vector1.getY()*vector2.getY();
	}

	public static boolean areOppositeDirection(Vector2D vector1, Vector2D vector2) {
		return Vector2D.dot(vector1, vector2) == -vector1.amplitude()*vector2.amplitude();
	}

	public static boolean areSameDirection(Vector2D vector1, Vector2D vector2) {
		return Vector2D.dot(vector1, vector2) == vector1.amplitude()*vector2.amplitude();
	}

	public static boolean areParallel(Vector2D vector1, Vector2D vector2) {
		return Vector2D.areOppositeDirection(vector1, vector2) ||
			   Vector2D.areSameDirection(vector1, vector2);
	}

	public static boolean areVertical(Vector2D vector1, Vector2D vector2) {
		return Vector2D.dot(vector1, vector2) == 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
        result = prime * result + Double.hashCode(p1.getX());
        result = prime * result + Double.hashCode(p2.getX());
        result = prime * result + Double.hashCode(p1.getY());
        result = prime * result + Double.hashCode(p2.getY());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (Double.doubleToLongBits(p1.getX()) != Double.doubleToLongBits(other.p1.getX()))
			return false;
		if (Double.doubleToLongBits(p2.getX()) != Double.doubleToLongBits(other.p2.getX()))
			return false;
		if (Double.doubleToLongBits(p1.getY()) != Double.doubleToLongBits(other.p1.getY()))
			return false;
        return Double.doubleToLongBits(p2.getY()) == Double.doubleToLongBits(other.p2.getY());
    }

	@Override
	public String toString() {
		if(this.isCentered()) {
			return "(" + p2.getX() + ", " + p2.getY() + ")";
		}
		return "(" + p1.getX() + ", " + p1.getY() + ") -> (" + p2.getX() + ", " + p2.getY() + ")";
	}
	
	
}
