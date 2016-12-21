package de.manuel_joswig.breakout;

import de.manuel_joswig.breakout.game.Board;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * Initializes the window
 * 
 * @author		Manuel Joswig
 * @copyright	2013 Manuel Joswig
 */
public class Breakout extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final int FRAME_WIDTH = 450;
	public static final int FRAME_HEIGHT = 450;
	public static final String FRAME_TITLE = "Breakout";

	public Breakout() {
		setTitle(FRAME_TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		// add game panel
		add(new Board(this, Color.BLACK));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Breakout();
	}
}