package de.manuel_joswig.breakout.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Represents an entity
 * 
 * @author		Manuel Joswig
 * @copyright	2013 Manuel Joswig
 */
public class Entity {
	private double x, y;
	private int width, height;
	private Color color;
	
	public Entity(double x, double y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		color = c;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(color);
		g2d.fill(new Rectangle2D.Double(x, y, width, height));
	}
}
