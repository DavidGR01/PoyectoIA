package app;

public class Trayecto {

	private Estacion estacion1;
	private Estacion estacion2;
	private double tiempo;
	private String linea;

	/**
	 * Constructor
	 * 
	 * @param estacion1
	 * @param estacion2
	 * @param tiempo
	 */
	public Trayecto(Estacion estacion1, Estacion estacion2, double tiempo,  String linea) {

		this.estacion1 = estacion1;
		this.estacion2 = estacion2;
		this.tiempo = tiempo;
		this.linea = linea;

	}

	// Getters

	public Estacion getEstacion1() {
		return estacion1;
	}

	public Estacion getEstacion2() {
		return estacion2;
	}

	public double getTiempo() {
		return tiempo;
	}
	
	public String getLinea() {
		return linea;
	}

}
