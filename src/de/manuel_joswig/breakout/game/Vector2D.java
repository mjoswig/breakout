package de.manuel_joswig.breakout.game;

/**
 * Represents a vector in IR²
 * 
 * @author		Manuel Joswig
 * @copyright	2013 Manuel Joswig
 */
public class Vector2D {
	double x, y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getLength() {
		return Math.sqrt(x * x + y * y);
	}
}
