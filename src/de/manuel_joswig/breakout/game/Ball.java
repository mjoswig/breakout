package de.manuel_joswig.breakout.game;

import java.awt.Color;

/**
 * Represents the moving ball
 * 
 * @author		Manuel Joswig
 * @copyright	2013 Manuel Joswig
 */
public class Ball extends Entity {
	private static final Vector2D ACCELERATION = new Vector2D(2, 2);
	
	public Ball(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}
	
	public boolean intersects(Entity e) {
		return (getX() >= e.getX() - 1 && getX() <= e.getX() + e.getWidth() && getY() - 1 == e.getY());
	}
	
	public void move(Vector2D v) {
		setX(getX() + v.getX() * ACCELERATION.getX());
		setY(getY() + v.getY() * ACCELERATION.getY());
	}
}
