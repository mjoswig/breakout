package de.manuel_joswig.breakout.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a wall of bricks
 * 
 * @author		Manuel Joswig
 * @copyright	2013 Manuel Joswig
 */
public class Wall {
	// distance between two bricks
	private static final int DIST = 1;
	
	// available brick colors
	private static final Color[] COLORS = {Color.RED, Color.GREEN, Color.YELLOW};
	
	private int width, height;
	private int bricksPerRow;
	private int brickWidth, brickHeight;
	private Color[][] brickColors;
	private Brick[][] bricks;
	
	public Wall(int w, int h, int n) {
		width = w;
		height = h;
		bricksPerRow = n;
		brickWidth = (int) (width / bricksPerRow);
		brickHeight = (int) (height / 4);
		brickColors = new Color[w][h];
		bricks = new Brick[w][h];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				brickColors[i][j] = COLORS[(int) (Math.random() * COLORS.length)];
				bricks[i][j] = new Brick(i * (brickWidth + DIST), 10 + j * (brickHeight + DIST), brickWidth, brickHeight, brickColors[i][j]);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getBrickWidth() {
		return brickWidth;
	}
	
	public int getBrickHeight() {
		return brickHeight;
	}
	
	public Brick getBrick(int x, int y) {
		return bricks[x][y];
	}
	
	public boolean isBroken() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (!bricks[i][j].isBroken()) return false;
			}
		}
		
		return true;
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (!bricks[i][j].isBroken()) bricks[i][j].draw(g);
			}
		}
	}
}
