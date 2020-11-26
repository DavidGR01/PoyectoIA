package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Nodo extends JPanel {

	private int radio;
	private Color c;

	public Nodo(int radio, Color c) {
		this.radio = radio;
		this.c = c;

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setPaint(c);

		g2d.fillOval(0, 0, radio, radio);
	}
}