package de.manuel_joswig.breakout.game;

import java.awt.Color;

import de.manuel_joswig.breakout.Breakout;

/**
 * Represents the paddle
 * 
 * @author		Manuel Joswig
 * @copyright	2013 Manuel Joswig
 */
public class Paddle extends Entity {
	private static final Vector2D VELOCITY = new Vector2D(1.25, 0);
	
	public Paddle(double x, double y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}
	
	public void move(Board b, Direction dir) {
		switch (dir) {
			case LEFT:
				if (getX() > 0 && b.isGameActive()) setX(getX() - VELOCITY.getX());
				break;

			case RIGHT:
				if (getX() < Breakout.FRAME_WIDTH - getWidth() && b.isGameActive()) setX(getX() + VELOCITY.getX());
				break;
		}
	}
}
