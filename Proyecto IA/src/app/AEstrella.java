package app;

import java.util.ArrayList;
import java.util.List;

import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.lifo.LIFO;
import es.upm.aedlib.lifo.LIFOList;

public class AEstrella {

	// Atributos necesarios para aplicar el algortmo A*
	Grafo red;
	Estacion origen;
	Estacion destino;

	private ArrayList<Estacion> listaAbierta = new ArrayList<Estacion>();
	private ArrayList<Estacion> listaCerrada = new ArrayList<Estacion>();
	private LIFO<Vertex<Estacion>> pila = new LIFOList();

	private boolean movRed;
	private int horaSalida;

	private double tiempo = 0;

	/**
	 * Constructor. Inicializa las variables y da paso al algortimo
	 * 
	 * @param origen
	 * @param destino
	 * @param red
	 */
	public AEstrella(Grafo red, Estacion origen, Estacion destino, boolean movRed, int horaSalida) {
		// Inicializamos las variables
		this.red = red;
		this.origen = origen;
		this.destino = destino;
		this.movRed = movRed;
		this.horaSalida = horaSalida;

		Estacion actual = null;
		listaAbierta.add(origen);
		do {
			actual = menorF();
			listaCerrada.add(actual);
			if (actual.equals(destino)) {
				listaAbierta.clear();
			} else {
				List<Estacion> adyacentes = actual.vecinos(red);
				Estacion aux;
				for (int i = 0; i < adyacentes.size(); i++) {
					aux = adyacentes.get(i);
					if ((!listaAbierta.contains(aux)) && (!listaCerrada.contains(aux))) {
						setCostes(actual, aux);
						aux.setEstacionAnterior(actual);
						listaAbierta.add(aux);
					} else if (listaAbierta.contains(aux)) {
						if (aux.getG() < actual.getG()) {
							setCostes(actual, aux);
							aux.setEstacionAnterior(actual);
						}
					}
				}
			}
		} while (listaAbierta.size() != 0);
		apilar(actual, origen);
	}

	/**
	 * Devuelve el menor coste de la funci�n f para todos los posibles �rboles
	 * 
	 * @return
	 */
	public Estacion menorF() {
		Estacion resultado = listaAbierta.get(0);
		if (listaAbierta.size() > 1) {
			for (int i = 1; i < listaAbierta.size(); i++) {
				if (listaAbierta.get(i).getF() < resultado.getF()) {
					resultado = listaAbierta.get(i);
				}
			}
		}
		listaAbierta.remove(resultado);
		return resultado;
	}

	/**
	 * Calcula las funciones f, g y h
	 * 
	 * @param estacion1
	 * @param estacion2
	 */
	public void setCostes(Estacion estacion1, Estacion estacion2) {

		// Calculo H
		double dH = getDistanciaH(estacion2);

		// Calculo G
		double dG = getDistanciaG(estacion1, estacion2);

		if (estacion1.getEstacionAnterior() != null && !red.getTrayecto(estacion1, estacion2).getLinea()
				.equals(red.getTrayecto(estacion1, estacion1.getEstacionAnterior()).getLinea())) {
			// Penalizacion por trasbordos
			dG += getPenalizacion();
			if (movRed)
				dG += 6;
		}

		// Asigno valores
		estacion2.setG(estacion1.getG() + dG);
		estacion2.setH(estacion1.getH() + dH);
		estacion2.setF(estacion2.getG() + estacion2.getH());
	}

	/**
	 * Calcula la distancia heur�stica
	 * 
	 * @param est
	 * @return
	 */
	public double getDistanciaH(Estacion est) {
		Punto p1 = new Punto(est.getCoordX(), est.getCoordY());
		Punto p2 = new Punto(destino.getCoordX(), est.getCoordY());
		return p1.tiempo(p2);
	}

	/**
	 * Calculo el coste del recorrido que llevamos
	 * 
	 * @param estacion1
	 * @param estacion2
	 * @return
	 */
	public double getDistanciaG(Estacion estacion1, Estacion estacion2) {
		List<Estacion> conectadas = estacion1.vecinos(red);
		for (int i = 0; i < conectadas.size(); i++) {
			if (conectadas.get(i).equals(estacion2)) {
				return red.getTrayecto(estacion1, conectadas.get(i)).getDistancia();
			}
		}
		return -1;
	}

	/**
	 * M�todo recursivo que opera en la pila
	 * 
	 * @param actual
	 * @param origen
	 * @return
	 */
	public Estacion apilar(Estacion actual, Estacion origen) {
		if (!pila.isEmpty())
			tiempo += red.getTrayecto(pila.top().element(), actual).getTiempo();
		pila.push(red.getVertice(actual));
		if (actual.equals(origen)) {
			return actual;
		} else {
			return apilar(actual.getEstacionAnterior(), origen);
		}
	}

	/**
	 * Devuelve el camino resultado
	 * 
	 * @return
	 */
	public ArrayList<Estacion> getResultado() {
		ArrayList<Estacion> resultado = new ArrayList<Estacion>();
		while (!pila.isEmpty()) {
			resultado.add(pila.pop().element());
		}
		return resultado;
	}

	public double getTiempoRuta() {
		return tiempo;
	}

	public double getPenalizacion() {

		double random = 0;

		if ((horaSalida >= 7 && horaSalida <= 9) || (horaSalida >= 12 && horaSalida <= 15)
				|| horaSalida >= 19 && horaSalida <= 21)
			random = Math.random() * 8 + (Math.random() * 2 + 2);

		if ((horaSalida > 21) || (horaSalida > 15 && horaSalida < 19))
			random = Math.random() * 10 + (Math.random() * 2 + 2);

		return random;
	}

	public double getMaxPenalizacion() {

		double random = 0;

		if ((horaSalida >= 7 && horaSalida <= 9) || (horaSalida >= 12 && horaSalida <= 15)
				|| horaSalida >= 19 && horaSalida <= 21)
			random = 12;

		if ((horaSalida > 21) || (horaSalida > 15 && horaSalida < 19))
			random = 14;

		return random;
	}

}
