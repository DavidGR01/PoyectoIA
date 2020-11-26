package app;

public class Punto {

	// ATRIBUTOS
	double x;
	double y;
	final double R = 6371; // radio terrestre en KM

	// CONSTRUCTOR
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// GETTERS
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	// Metodo que calcula la distancia entre puntos
	public double distancia(Punto p) {
		// Longitud - latitud de ambos puntos
		double long1 = this.getX();
		double long2 = p.getX();
		double lat1 = this.getY();
		double lat2 = p.getY();
		// Calculo de la distancia entre puntos
		double difLat = Math.toRadians(lat2 - lat1);
		double difLong = Math.toRadians(long2 - long1);
		double a = Math.pow(Math.sin(difLat / 2), 2)
				+ Math.pow(Math.sin(difLong / 2), 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;
	}

}
