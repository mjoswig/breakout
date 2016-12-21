package de.manuel_joswig.breakout.game;

import java.awt.Color;

/**
 * Represents a single brick
 * 
 * @author		Manuel Joswig
 * @copyright	2013 Manuel Joswig
 */
public class Brick extends Entity {
	private boolean isBroken;
	
	public Brick(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		isBroken = false;
	}
	
	public boolean isBroken() {
		return isBroken;
	}
	
	public void setBroken() {
		isBroken = true;
	}
}
