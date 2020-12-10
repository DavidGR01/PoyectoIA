package app;

import java.util.ArrayList;
import java.util.List;

import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.lifo.LIFO;
import es.upm.aedlib.lifo.LIFOList;

public class AEstrella {

	// Atributos generales del problema
	private Grafo red;
	private Estacion origen;
	private Estacion destino;

	// Atributos necesarios para aplicar el algortmo A*
	private ArrayList<Estacion> listaAbierta = new ArrayList<Estacion>();
	private ArrayList<Estacion> listaCerrada = new ArrayList<Estacion>();
	private LIFO<Vertex<Estacion>> pila = new LIFOList<>();

	// Especificaciones opcionales implementadas para dar m�s realismo a la
	// situaci�n
//	private boolean movRed;
	private int horaSalida;

	// Tiempo que se tarda en realizar dicho recorrido
	private double tiempoSinPenalizaciones = 0;

	/**
	 * Constructor. Inicializa las variables y da paso al algoritmo
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
		//this.movRed = movRed;
		this.horaSalida = horaSalida;

		// Comenzamos el algoritmo
		Estacion actual = null;
		listaAbierta.add(origen);
		do {
			actual = menorF();
			listaCerrada.add(actual);
			if (actual.equals(destino))
				listaAbierta.clear();
			else {
				List<Estacion> adyacentes = actual.vecinos(red);
				Estacion aux;
				for (int i = 0; i < adyacentes.size(); i++) {
					aux = adyacentes.get(i);
					if ((!listaAbierta.contains(aux)) && (!listaCerrada.contains(aux))) {
						setCostes(actual, aux);
						aux.setEstacionAnterior(actual);
						listaAbierta.add(aux);
					} else if (listaAbierta.contains(aux)) {
						double auxOldF = aux.getF();
						double auxOldG = aux.getG();
						double dH = getDistanciaH(aux);
						double dG = getDistanciaG(actual, aux);
						if(esTrasbordo(actual, aux)) {
							dG += getPenalizacion();
						}
						double auxNewF = actual.getG() + dG + dH;
						if (auxOldF > auxNewF) {
							aux.setEstacionAnterior(actual);
							aux.setG(actual.getG() + dG);
							aux.setH(dH);
							aux.setF(auxNewF);
						}
					}
				}
			}
		} while (listaAbierta.size() != 0);

		// Crea la pila de las estaciones del trayecto a realizar
		apilar(actual, origen);
	}

	/**
	 * Calcula la funci�n f
	 * 
	 * @return menor coste de la funci�n f para todos los posibles �rboles
	 */
	public Estacion menorF() {
		Estacion resultado = listaAbierta.get(0);
		if (listaAbierta.size() > 1) {
			for (int i = 1; i < listaAbierta.size(); i++) {
				if (listaAbierta.get(i).getF() < resultado.getF())
					resultado = listaAbierta.get(i);
			}
		}
		listaAbierta.remove(resultado);
		return resultado;
	}

	/**
	 * Calcula las funciones f, g y h
	 * 
	 * @param actual
	 * @param aux
	 */
	public void setCostes(Estacion actual, Estacion aux) {

		// Calculo h
		double dH = getDistanciaH(aux);

		// Calculo g
		double dG = getDistanciaG(actual, aux);

		// Modifico g con penalizaciones si hay que tomar transbordos
		if (esTrasbordo(actual, aux)) {
			// Penalizacion por trasbordos
			dG += getPenalizacion();
			// Penalizaci�n extra por movilidad reducida
//			if (movRed) {
//				dG += 6;
//			}
		}

		// Asigno valores
		aux.setG(actual.getG() + dG);
		aux.setH(dH);
		aux.setF(aux.getG() + aux.getH());
	}

	/**
	 * Calcula el tiempo entre estacion1 y estacion2, esto se sumar� a la funcion g
	 * actual
	 * 
	 * @param estacion1
	 * @param estacion2
	 * @return tiempo entre ambas estaciones
	 */
	public double getDistanciaG(Estacion estacion1, Estacion estacion2) {
//		Trayecto t = red.getTrayecto(estacion1, estacion2);
//		return t != null ? t.getTiempo() : -1;
		// Calculamos todos los vecinos de estacion1
		List<Estacion> conectadas = estacion1.vecinos(red);
		// Recorremos los vecinos hasta encontrar estacion2
		for (int i = 0; i < conectadas.size(); i++) {
			// Cuando encontramos estacion2
			if (conectadas.get(i).equals(estacion2))
				// Devolvemos el tiempo del trayecto entre ambas estaciones
				return red.getTrayecto(estacion1, conectadas.get(i)).getTiempo();
		}
		// Si no existe trayecto entre estacion1 y estacion2 devolvemos -1
		return -1;
	}
	
	
	private boolean esTrasbordo(Estacion actual, Estacion aux) {
		return actual.getEstacionAnterior() != null && !red.getTrayecto(actual, aux).getLinea()
				.equals(red.getTrayecto(actual, actual.getEstacionAnterior()).getLinea());
	}

	/**
	 * Calcula la distancia heur�stica h que corresponde al tiempo entre
	 * estacionActual y destino al recorrer dicho recorrido en l�nea recta, es
	 * decir, el tiempo que queda en el mejor caso
	 * 
	 * @param estacionActual
	 * @return tiempo entre ambas estaciones en l�nea recta
	 */
	public double getDistanciaH(Estacion estacionActual) {
		// Asigno los puntos estacionAcutal y destino
		Punto p1 = new Punto(estacionActual.getCoordX(), estacionActual.getCoordY());
		Punto p2 = new Punto(destino.getCoordX(), destino.getCoordY());
		// Devuelvo el tiempo entre ambos puntos, es decir, lo que nos queda por
		// recorrer
		return p1.tiempo(p2);
	}

	/**
	 * Método recursivo que apila las estaciones correspondientes al trayecto
	 * resultado
	 * 
	 * @param actual
	 * @param origen
	 * @return pila con el trayecto que resulta de aplicar el algoritmo A*
	 */
	public void apilar(Estacion actual, Estacion origen) {
		if (!pila.isEmpty())
			tiempoSinPenalizaciones += red.getTrayecto(pila.top().element(), actual).getTiempo();
		pila.push(red.getVertice(actual));
		if (!actual.equals(origen))
			apilar(actual.getEstacionAnterior(), origen);
	}

	/**
	 * Calcula una penalizaci�n aleatoria al tener que tomar un transbordo
	 * 
	 * @return dicha penalizaci�n
	 */
	public double getPenalizacion() {
		double random = 0;
		// Si estamos en hora punta
		if ((horaSalida >= 7 && horaSalida <= 9) || (horaSalida >= 12 && horaSalida <= 15)
				|| horaSalida >= 19 && horaSalida <= 21)
			random =  4;
		// Si no es hora punta	
		else
			random = 6;
		return random;
	}

	/**
	 * M�todo auxiliar usado para la interfaz gr�fica
	 * 
	 * @return m�xima penalizaci�n que puede haber en un transbordo
	 */
	public double getMaxPenalizacion() {
		double random = 0;
		if ((horaSalida >= 7 && horaSalida <= 9) || (horaSalida >= 12 && horaSalida <= 15)
				|| horaSalida >= 19 && horaSalida <= 21)
			random = 7;
		else
			random = 10;
		return random;
	}

	/**
	 * M�todo auxiliar usado para la interfaz gr�fica
	 * 
	 * @return minima penalizaci�n que puede haber en un transbordo
	 */
	public double getMinPenalizacion() {
		return 1;
	}

	/**
	 * Desapila la pila y devuelve una lista con las estaciones a recorrer
	 * 
	 * @return resultado del algoritmo A*
	 */
	public ArrayList<Estacion> getResultado() {
		ArrayList<Estacion> resultado = new ArrayList<Estacion>();
		while (!pila.isEmpty())
			resultado.add(pila.pop().element());
		return resultado;
	}

	/**
	 * Getter del tiempo
	 * 
	 * @return tiempo de la ruta
	 */
	public double getTiempoRuta() {
		return destino.getG();
	}

	public double getTiempoSinPenalizaciones() {
		return tiempoSinPenalizaciones;
	}

}
