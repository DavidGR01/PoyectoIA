package app;

import java.util.ArrayList;
import java.util.List;

import es.upm.aedlib.graph.Vertex;

public class Estacion {

	private String nombre;
	private double coordX, coordY;
	private int xPic, yPic;

	// funciones necesarias para el algortimo A*
	private double f, g, h;

	// variable necesaria para el algortitmo A*
	private Estacion estacionAnterior;

	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param coordX
	 * @param coordY
	 */
	public Estacion(String nombre, double coordX, double coordY, int xPic, int yPic) {
		this.nombre = nombre;
		this.coordX = coordX;
		this.coordY = coordY;
		this.xPic = xPic;
		this.yPic = yPic;
	}

	/**
	 * Devuelve una lista de las estaciones a las que se puede llegar desde la
	 * estaci�n this
	 * 
	 * @param red
	 * @return estaciones vecinas a la estaci�n this
	 */
	public List<Estacion> vecinos(Grafo red) {
		List<Vertex<Estacion>> listaVertices = red.vecinos(red.getVertice(this));
		List<Estacion> res = new ArrayList<>();
		for (Vertex<Estacion> v : listaVertices)
			res.add(v.element());
		return res;
	}

	// toString

	public String toString() {
		return "Estación: " + this.nombre;
	}

	// Getters

	public String getNombre() {
		return nombre;
	}

	public double getCoordX() {
		return coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public int getxPic() {
		return xPic;
	}

	public int getyPic() {
		return yPic;
	}

	public double getF() {
		return f;
	}

	public double getG() {
		return g;
	}

	public double getH() {
		return h;
	}

	public Estacion getEstacionAnterior() {
		return estacionAnterior;
	}

	// Setters

	public void setF(double f) {
		this.f = f;
	}

	public void setG(double g) {
		this.g = g;
	}

	public void setH(double h) {
		this.h = h;
	}

	public void setEstacionAnterior(Estacion estacionAnterior) {
		this.estacionAnterior = estacionAnterior;
	}
}
