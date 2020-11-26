package app;

import java.util.ArrayList;
import java.util.List;

import es.upm.aedlib.graph.Vertex;

public class Estacion {

	private String nombre;
	private double coordX, coordY;
	private int xPic, yPic;
	private boolean isTrasbordo;

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
	public Estacion(String nombre, double coordX, double coordY, int xPic, int yPic, boolean isTrasbordo) {
		this.nombre = nombre;
		this.coordX = coordX;
		this.coordY = coordY;
		this.xPic = xPic;
		this.yPic = yPic;
		this.isTrasbordo = isTrasbordo;
	}

	// Constructor para los trasbordos
	public Estacion(String nombre, double coordX, double coordY, int xPic, int yPic) {
		this.nombre = nombre;
		this.coordX = coordX;
		this.coordY = coordY;
		this.xPic = xPic;
		this.yPic = yPic;
		this.isTrasbordo = false;
	}

	/**
	 * Devuelve una lista de las estaciones vecinas de la estacion
	 * 
	 * @param red
	 * @return
	 */
	public List<Estacion> vecinos(Grafo red) {
		List<Vertex<Estacion>> listaVertices = red.vecinos(red.getVertice(this));
		List<Estacion> res = new ArrayList<>();
		for (Vertex<Estacion> v : listaVertices) {
			res.add(v.element());
		}

		return res;
	}

	// Getters y setters

	public double getCoordX() {
		return coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public String getNombre() {
		return nombre;
	}

	public String toString() {
		return "Estación: " + this.nombre;
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

	public Estacion getEstacionAnterior() {
		return estacionAnterior;
	}

	public int getxPic() {
		return xPic;
	}

	public int getyPic() {
		return yPic;
	}

	public boolean isTrasbordo() {
		return isTrasbordo;
	}

}
