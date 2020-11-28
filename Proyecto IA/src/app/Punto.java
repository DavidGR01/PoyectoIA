package app;

public class Punto {

	double coordX;
	double coordY;

	// Radio de la tierra en km. Para calcular el tiempo entre dos puntos
	final double R = 6371;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Punto(double x, double y) {
		this.coordX = x;
		this.coordY = y;
	}

	// Getters
	public double getCoordX() {
		return coordX;
	}

	public double getCoordY() {
		return coordY;
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
		 * Fórmula:
		 * 	R = radio de la Tierra
		 * 
		 * 	Δlat = lat2− lat1
		 * 	Δlong = long2− long1
		 * 
		 * 	a = sin²(Δlat/2) + cos(lat1) · cos(lat2) · sin²(Δlong/2)
		 * 	c = 2 · atan2(√a, √(1−a))
		 * 	d = R · c
		 */
		
		double difY = Math.toRadians(p.getCoordY() - coordY);
		double difX = Math.toRadians(p.getCoordX() - coordX);

		double a = Math.pow(Math.sin(difY / 2), 2) + Math.pow(Math.sin(difX / 2), 2) * Math.cos(Math.toRadians(coordY))
				* Math.cos(Math.toRadians(p.getCoordY()));

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double distancia = R * c;
		
		
		// Paso la distancia a tiempo
		// Un tren va a una velocidad aproximada de 80 km/h
		return (distancia / 80) * 60;
	}

}
