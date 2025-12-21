package com.antibas.math.geometry.vector;

import com.antibas.util.pair.NamedPair;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Vector2D {
	protected double x1, y1;
	protected double x2, y2;
	
	public Vector2D(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Vector2D(NamedPair<Double> namedPair1, NamedPair<Double> namedPair2) {
		this(namedPair1.getFirst(), namedPair1.getSecond(), namedPair2.getFirst(), namedPair2.getSecond());
	}
	
	public Vector2D(double x2, double y2) {
		this(0, 0, x2, y2);
	}
	
	public Vector2D(NamedPair<Double> namedPair2) {
		this(namedPair2.getFirst(), namedPair2.getSecond());
	}
	
	public Vector2D(Vector2D vector) {
		this(vector.x1, vector.y1, vector.x2, vector.y2);
	}
	
	public Vector2D() {
		this(0,0);
	}

    public double getX() {
		return x2-x1;
	}

	public double getY() {
		return y2-y1;
	}
	
	public Iterable<Double> getFirstPoint(){
		return new NamedPair<>(x1, y1);
	}
	
	public Iterable<Double> getSecondPoint(){
		return new NamedPair<>(x2, y2);
	}
	
	public double amplitude() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
	}
	
	public double angle() {
		return Math.atan((getY())/(getX()));
	}
	
	public boolean isCentered() {
		return x1 == 0 && y1 == 0;
	}
	
	public Vector2D getCentered() {
		return new Vector2D(getX(), getY());
	}
	
	public Vector2D getInverse() {
		return new Vector2D(x2, y2, x1, y1);
	}
	
	public Vector2D getNormalized() {
		return div(new Vector2D(x1, y1, x2, y2), this.amplitude());
	}
	
	public static Vector2D add(Vector2D vector, double a) {
		return new Vector2D(vector.x1+a, vector.x2+a, vector.y1+a, vector.y2+a);
	}
	
	public static Vector2D sub(Vector2D vector, double a) {
		return new Vector2D(vector.x1-a, vector.x2-a, vector.y1-a, vector.y2-a);
	}
	
	public static Vector2D mul(Vector2D vector, double a) {
		return new Vector2D(vector.x1*a, vector.x2*a, vector.y1*a, vector.y2*a);
	}
	
	public static Vector2D div(Vector2D vector, double a) {
		return new Vector2D(vector.x1/a, vector.x2/a, vector.y1/a, vector.y2/a);
	}

	public static Vector2D add(Vector2D vector1, Vector2D vector2) {
		if(vector1.getFirstPoint().equals(vector2.getFirstPoint())) {
			return new Vector2D(vector1.x1, vector1.y1, vector1.x2+vector2.x2, vector1.y2+vector2.y2);
		}
		if(vector1.getSecondPoint().equals(vector2.getFirstPoint())) {
			return new Vector2D(vector1.x1, vector1.y1, vector2.x2, vector2.y2);
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
        result = prime * result + Double.hashCode(x1);
        result = prime * result + Double.hashCode(x2);
        result = prime * result + Double.hashCode(y1);
        result = prime * result + Double.hashCode(y2);
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
		if (Double.doubleToLongBits(x1) != Double.doubleToLongBits(other.x1))
			return false;
		if (Double.doubleToLongBits(x2) != Double.doubleToLongBits(other.x2))
			return false;
		if (Double.doubleToLongBits(y1) != Double.doubleToLongBits(other.y1))
			return false;
        return Double.doubleToLongBits(y2) == Double.doubleToLongBits(other.y2);
    }
	
	@Override
	public String toString() {
		if(this.isCentered()) {
			return "(" + x2 + ", " + y2 + ")";
		}
		return "(" + x1 + ", " + y1 + ") -> (" + x2 + ", " + y2 + ")";
	}
	
	
}
