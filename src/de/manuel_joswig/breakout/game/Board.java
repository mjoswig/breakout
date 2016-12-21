package de.manuel_joswig.breakout.game;

import de.manuel_joswig.breakout.Breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Represents the game board
 * 
 * @author		Manuel Joswig
 * @copyright	2013 Manuel Joswig
 */
public class Board extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private JFrame f;
	
	private Ball b;
	private Paddle p;
	private Wall w;
	
	private int score;
	private boolean isGameActive;
	
	private Vector2D v;
	private Timer t;
	private Direction d;

	public Board(Breakout frame, Color bgColor) {
		f = frame;
		t = new Timer(1, this);
		
		setFocusable(true);
		setBackground(bgColor);
		addKeyListener(this);
		init();
	}
	
	public void init() {
		f.setTitle(Breakout.FRAME_TITLE + " (Score: 0)");
		
		b = new Ball((int) (Breakout.FRAME_WIDTH / 2), (int) (Breakout.FRAME_HEIGHT / 2), 3, 3, Color.WHITE);
		p = new Paddle((int) (Breakout.FRAME_WIDTH * 0.45), (int) (Breakout.FRAME_HEIGHT * 0.88), 50, 5, Color.CYAN);
		w = new Wall(Breakout.FRAME_WIDTH, (int) (Breakout.FRAME_HEIGHT / 20), 11);
		
		score = 0;
		isGameActive = false;
		
		// velocity vector of the ball
		v = new Vector2D(0, 1);
		
		// direction of the paddle
		d = Direction.NONE;
		
		t.stop();
	}
	
	public boolean isGameActive() {
		return isGameActive;
	}
	
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch (keyCode) {
			case KeyEvent.VK_LEFT:
				if (d.equals(Direction.LEFT)) d = Direction.NONE;
				break;
				
			case KeyEvent.VK_RIGHT:
				if (d.equals(Direction.RIGHT)) d = Direction.NONE; 
				break;
		}
	}
	
	public void keyTyped(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch (keyCode) {
			// move paddle left
			case KeyEvent.VK_LEFT:
				d = Direction.LEFT;
				repaint();
				break;
			
			// move paddle right
			case KeyEvent.VK_RIGHT:
				d = Direction.RIGHT;
				repaint();
				break;
			
			// interrupt game
			case KeyEvent.VK_SPACE:
				if (isGameActive) {
					t.stop();
				}
				else {
					t.start();
				}
				
				isGameActive = !isGameActive;
				break;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (d.equals(Direction.LEFT) || d.equals(Direction.RIGHT)) p.move(this, d);
		
		double dx = (b.getX() + (b.getWidth() / 2)) - (p.getX() + (p.getWidth() / 2));
		double dy = (b.getY() + (b.getHeight() / 2)) - (p.getY() + (p.getHeight() / 2));
		double angle = Math.toDegrees(Math.atan(dx / dy));
		
		b.move(v);
		
		// did the ball hit one of the bounds?
		if (b.getX() < 0 || b.getX() > Breakout.FRAME_WIDTH) {
			v.setX(-v.getX());
		} 
		else if (b.getY() < 0) {
			v.setY(-v.getY());
		}
		else if (b.getY() == getHeight() + 1) {
			// GAME OVER!! :(
			init();
		}
		else if (b.intersects(p)) {
			// checks whether the ball hit the left or the right side of the paddle
			if (b.getX() >= p.getX() && b.getX() <= p.getX() + p.getWidth() / 3) {
				// move it a little left...
				v.setX(v.getX() + Math.cos(Math.toRadians(180 - angle)));
			}
			else if	(b.getX() >= p.getX() + (2 * p.getWidth() / 3) && b.getX() <= p.getX() + p.getWidth()) {
				// move it a little right...
				v.setX(v.getX() + Math.cos(Math.toRadians(angle)));
			}
			
			// hit the ball back
			v.setY(-v.getY());
			
			// score + 1
			score++;
		}
		else {
			int wallWidth = w.getWidth();
			int wallHeight = w.getHeight();
			
			for (int i = 0; i < wallWidth; i++) {
				for (int j = 0; j < wallHeight; j++) {
					Brick tmp = w.getBrick(i, j);
					
					// if the ball collided with a brick...
					if (b.intersects(tmp) && !tmp.isBroken()) {
						if (tmp.getColor().equals(Color.RED) || tmp.getColor().equals(Color.YELLOW)) {
							// ... and hit it back again
							v.setY(-v.getY());
							
							// score + 10 (in case of a more stable brick)
							score += 10;
						}
						else {
							// score + 5 (in case of a common brick)
							score += 5;
						}
						
						// destroy it ;)
						tmp.setBroken();
					}
				}
			}
		}
		
		// update score
		f.setTitle(Breakout.FRAME_TITLE + " (Score: " + score + ")");
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		b.draw(g);
		p.draw(g);
		w.draw(g);
	}
}
