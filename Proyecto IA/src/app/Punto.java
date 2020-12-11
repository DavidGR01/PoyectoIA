package app;

public class Punto {

	private double lat;
	private double longitud;

	// Radio de la tierra en km. Para calcular el tiempo entre dos puntos
	final double R = 6371;

	/**
	 * Constructor
	 * 
	 * @param lat
	 * @param longitud
	 */
	public Punto(double lat, double longitud) {
		this.lat = lat;
		this.longitud = longitud;
	}

	// Getters
	public double getCoordX() {
		return lat;
	}

	public double getCoordY() {
		return longitud;
	}

	/**
	 * Calcula el tiempo que tarda un tren en ir en línea recta entre los puntos
	 * this y p
	 * 
	 * @param p
	 * @return el tiempo entre p y this
	 */
	public double tiempo(Punto p) {

		/*
		 * Fórmula: R = radio de la Tierra
		 * 
		 * Δlat = lat2− lat1 Δlong = long2− long1
		 * 
		 * a = sin²(Δlat/2) + cos(lat1) · cos(lat2) · sin²(Δlong/2) c = 2 · atan2(√a,
		 * √(1−a)) d = R · c
		 */

		double difLong = Math.toRadians(p.getCoordY() - longitud);
		double difLat = Math.toRadians(p.getCoordX() - lat);

		double a = (Math.pow(Math.sin(difLat / 2), 2) + Math.pow(Math.sin(difLong / 2), 2) * Math.cos(Math.toRadians(lat))
				* Math.cos(Math.toRadians(p.getCoordX())));

		double c = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));

		double distancia = R * c;

		// Paso la distancia a tiempo
		// Un tren va a una velocidad aproximada de 50 km/h
		return (distancia / 50) * 60;
	}

}
