package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JPanel;

import app.Estacion;

@SuppressWarnings("serial")
public class Arista extends JPanel {

	private static int thickness = 15;

	private int xBox, yBox;

	private int x1, y1, x2, y2;

	private int xFinal, yFinal, offset;

	public Arista(Estacion a, Estacion b) {
		x1 = a.getxPic() - thickness / 2;
		y1 = a.getyPic() - thickness / 2;
		x2 = b.getxPic() - thickness / 2;
		y2 = b.getyPic() - thickness / 2;
		xBox = Math.min(x1, x2);
		yBox = Math.min(y1, y2);
		xFinal = -1;
		yFinal = -1;
		offset = 0;
		calcularDimensiones();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Color color = new Color(0, 0, 0, 1f); 
		g2d.setPaint(color);

		Stroke stroke3 = new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2d.setStroke(stroke3);
		g2d.drawLine(0 + thickness / 2, offset + thickness / 2, xFinal + thickness / 2, yFinal + thickness / 2);
	}

	private void calcularDimensiones() {

		int xDiffAbs = Math.abs(x1 - x2);
		int yDiffAbs = Math.abs(y1 - y2);

		Float pendiente;
		// Calculamos la pendiente. Null si es la recta horizontal
		if (x1 == x2)
			pendiente = null;
		else
			pendiente = ((float) (y1 - y2)) / (x1 - x2);

		if (pendiente == null) {
			xFinal = 0;
			yFinal = yDiffAbs;
		} else if (pendiente == 0) {
			xFinal = xDiffAbs;
			yFinal = 0;
		} else if (pendiente < 0) {
			xFinal = xDiffAbs;
			yFinal = 0;
			offset = yDiffAbs;
		} else if (pendiente > 0) {
			xFinal = xDiffAbs;
			yFinal = yDiffAbs;
		}
	}

	public int getxBox() {
		return xBox;
	}

	public int getyBox() {
		return yBox;
	}

}