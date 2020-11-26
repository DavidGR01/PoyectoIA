package app;

import java.util.ArrayList;
import java.util.List;

import es.upm.aedlib.graph.*;

public class Grafo {

	// Grafo que representa la red de metro
	private UndirectedGraph<Estacion, Trayecto> red;

	/**
	 * Constructor del grafo. Construye la red de metro
	 */
	public Grafo() {
		// Inicializo el grafo
		red = new UndirectedAdjacencyListGraph<>();

		Estacion athenas1 = new Estacion("Kifissia", 38.07376975500105, 23.808315689518267, 531, 40);
		Estacion athenas2 = new Estacion("KAT", 38.06598759096725, 23.804049095845773, 498, 73);
		Estacion athenas3 = new Estacion("Marousi", 38.0561506129527, 23.805013511250998, 466, 105);
		Estacion athenas4 = new Estacion("Neratziotissa", 38.044826032780755, 23.792865393914184, 428, 142);
		Estacion athenas5 = new Estacion("Irini", 38.04374451849954, 23.782694457323174, 400, 145);
		Estacion athenas6 = new Estacion("Iraklio", 38.046245496021456, 23.766043303717474, 328, 153);
		Estacion athenas7 = new Estacion("Neo Ionia", 38.041598341329504, 23.755169628491036, 309, 172);
		Estacion athenas8 = new Estacion("Pefkakia", 38.039545853341096, 23.75176106963346, 292,190);
		Estacion athenas9 = new Estacion("Perissos", 38.032936108861406, 23.74463163205478, 272,209);
		Estacion athenas10 = new Estacion("Ano Patissia", 38.023754752397494, 23.735986469515108, 253,229);
		Estacion athenas11 = new Estacion("Aghios Eleftherios", 38.02010352751815, 23.73188805415485, 233,248);
		Estacion athenas12 = new Estacion("Kato Patissia", 38.011575423877076, 23.728820964455462, 212,270);
		Estacion athenas13 = new Estacion("Aghios Nikolaos", 38.006930296315154, 23.727683707843234, 191,292);

		// estacion en linea verde y roja

		Estacion athenas14 = new Estacion("Attiki", 37.99944880265642, 23.722756891083915, 173,310);
		Estacion athenas15 = new Estacion("Victoria", 37.99308300014649, 23.730323254470207, 209, 360);

		// estacion en linea verde y roja

		Estacion athenas16 = new Estacion("Omonia", 37.98416020104178, 23.72868743592787, 210, 390);

		// estacion en linea verde y azul

		Estacion athenas17 = new Estacion("Monastiraki", 37.976082670128605, 23.725623789535472, 210, 425);
		Estacion athenas18 = new Estacion("Thissio", 37.97673404540172, 23.72067136296464, 210, 462);
		Estacion athenas19 = new Estacion("Petralona", 37.96863353917757, 23.709220633202904, 200,486);
		Estacion athenas20 = new Estacion("Tavros", 37.96249109348175, 23.7033519233788, 177,509);
		Estacion athenas21 = new Estacion("Kallithea", 37.9604225677948, 23.697265871913242, 155,532);
		Estacion athenas22 = new Estacion("Moschato", 37.95503307384399, 23.679689864284008, 133,554);
		Estacion athenas23 = new Estacion("Faliro", 37.9450867983585, 23.665226153853716, 107,577);
		Estacion athenas24 = new Estacion("Piraeus", 37.948168527813955, 23.64326062925314, 18,588);

		// Estaciones linea ROJA

		Estacion athenas25 = new Estacion("Aghios Antonios", 38.00730151921081, 23.69949706951168, 109,245);
		Estacion athenas26 = new Estacion("Sepolia", 38.003350247868, 23.713410817497586, 142,278);
		Estacion athenas27 = new Estacion("Larissa Station", 37.99258056122259, 23.721356485874686, 173,343);
		Estacion athenas28 = new Estacion("Metaxourghio", 37.98698637900558, 23.721215229506697, 173,371);
		Estacion athenas29 = new Estacion("Panepistimio", 37.980840918159565, 23.73310173872911, 229,407);
		Estacion athenas30 = new Estacion("Syntagma", 37.9752676428218, 23.73570462884102, 247, 425);
		Estacion athenas31 = new Estacion("Akropoli", 37.969032728288006, 23.72957927448526, 250,464);
		Estacion athenas32 = new Estacion("Sygrou - Fix", 37.9644068253163, 23.726554717658804, 250,494);
		Estacion athenas33 = new Estacion("Neos Kosmos", 37.95773742487141, 23.72846187344377, 250,524);
		Estacion athenas34 = new Estacion("Aghios Loannis", 37.95678973558862, 23.734698381473027, 250,553);
		Estacion athenas35 = new Estacion("Dafni", 37.94933542727922, 23.737247896645897, 250, 583);
		Estacion athenas36 = new Estacion("Aghios Dimitrios", 37.94052565668688, 23.74071221022312, 250, 612);

		// Estaciones linea AZUL

		Estacion athenas37 = new Estacion("Egaleo", 37.99276237509949, 23.681669038773073, 50, 340);
		Estacion athenas38 = new Estacion("Eleonas", 37.98794922185388, 23.693768335383325, 92,382);
		Estacion athenas39 = new Estacion("Kerameikos", 37.979395534166045, 23.71159070406393, 125,415);
		Estacion athenas40 = new Estacion("Evangelismos", 37.97706378687556, 23.746691834164693, 312,425);
		Estacion athenas41 = new Estacion("Megaro Moussikis", 37.97993913829356, 23.753000389331483, 366,414);
		Estacion athenas42 = new Estacion("Ambelokipi", 37.987854229724384, 23.757034431796, 384,396);
		Estacion athenas43 = new Estacion("Panormou", 37.99384071888214, 23.763342987092475, 402,378);
		Estacion athenas44 = new Estacion("Katehaki", 37.994043642190896, 23.776174674811422, 419,360);
		Estacion athenas45 = new Estacion("Ethniki Amyna", 38.0007736127288, 23.785959373639688, 436,342);
		Estacion athenas46 = new Estacion("Holargos", 38.005169742407986, 23.794714103950767, 454,325);
		Estacion athenas47 = new Estacion("Nomismatokopio", 38.00990374121201, 23.8057004320679, 472,307);
		Estacion athenas48 = new Estacion("Aghia Paraskevi", 38.01771417085876, 23.812180649024317, 489,289);
		Estacion athenas49 = new Estacion("Holandri", 38.02227832239688, 23.82132161735102, 506, 272);
		Estacion athenas50 = new Estacion("Doukissis Plakentias", 38.02444196902856, 23.83368123660564, 533, 248);
		Estacion athenas51 = new Estacion("Pallini", 38.006759047769755, 23.869644294998704, 583, 267);
		Estacion athenas52 = new Estacion("Paiania - Kantza", 37.98426514547666, 23.869886277983845, 583,313);
		Estacion athenas53 = new Estacion("Koropi", 37.91389363358505, 23.895735912118077, 583,416);
		Estacion athenas54 = new Estacion("Airport", 37.93757373728647, 23.94479758853519, 650,445);

		// Vertex linea VERDE

		Vertex vAthenas1 = red.insertVertex(athenas1);
		Vertex vAthenas2 = red.insertVertex(athenas2);
		Vertex vAthenas3 = red.insertVertex(athenas3);
		Vertex vAthenas4 = red.insertVertex(athenas4);
		Vertex vAthenas5 = red.insertVertex(athenas5);
		Vertex vAthenas6 = red.insertVertex(athenas6);
		Vertex vAthenas7 = red.insertVertex(athenas7);
		Vertex vAthenas8 = red.insertVertex(athenas8);
		Vertex vAthenas9 = red.insertVertex(athenas9);
		Vertex vAthenas10 = red.insertVertex(athenas10);
		Vertex vAthenas11 = red.insertVertex(athenas11);
		Vertex vAthenas12 = red.insertVertex(athenas12);
		Vertex vAthenas13 = red.insertVertex(athenas13);
		Vertex vAthenas14 = red.insertVertex(athenas14);
		Vertex vAthenas15 = red.insertVertex(athenas15);
		Vertex vAthenas16 = red.insertVertex(athenas16);
		Vertex vAthenas17 = red.insertVertex(athenas17);
		Vertex vAthenas18 = red.insertVertex(athenas18);
		Vertex vAthenas19 = red.insertVertex(athenas19);
		Vertex vAthenas20 = red.insertVertex(athenas20);
		Vertex vAthenas21 = red.insertVertex(athenas21);
		Vertex vAthenas22 = red.insertVertex(athenas22);
		Vertex vAthenas23 = red.insertVertex(athenas23);
		Vertex vAthenas24 = red.insertVertex(athenas24);
		Vertex vAthenas25 = red.insertVertex(athenas25);
		Vertex vAthenas26 = red.insertVertex(athenas26);

		// Vertex linea ROJA

		Vertex vAthenas27 = red.insertVertex(athenas27);
		Vertex vAthenas28 = red.insertVertex(athenas28);
		Vertex vAthenas29 = red.insertVertex(athenas29);
		Vertex vAthenas30 = red.insertVertex(athenas30);
		Vertex vAthenas31 = red.insertVertex(athenas31);
		Vertex vAthenas32 = red.insertVertex(athenas32);
		Vertex vAthenas33 = red.insertVertex(athenas33);
		Vertex vAthenas34 = red.insertVertex(athenas34);
		Vertex vAthenas35 = red.insertVertex(athenas35);
		Vertex vAthenas36 = red.insertVertex(athenas36);

		// Vertex linea AZUL

		Vertex vAthenas37 = red.insertVertex(athenas37);
		Vertex vAthenas38 = red.insertVertex(athenas38);
		Vertex vAthenas39 = red.insertVertex(athenas39);
		Vertex vAthenas40 = red.insertVertex(athenas40);
		Vertex vAthenas41 = red.insertVertex(athenas41);
		Vertex vAthenas42 = red.insertVertex(athenas42);
		Vertex vAthenas43 = red.insertVertex(athenas43);
		Vertex vAthenas44 = red.insertVertex(athenas44);
		Vertex vAthenas45 = red.insertVertex(athenas45);
		Vertex vAthenas46 = red.insertVertex(athenas46);
		Vertex vAthenas47 = red.insertVertex(athenas47);
		Vertex vAthenas48 = red.insertVertex(athenas48);
		Vertex vAthenas49 = red.insertVertex(athenas49);
		Vertex vAthenas50 = red.insertVertex(athenas50);
		Vertex vAthenas51 = red.insertVertex(athenas51);
		Vertex vAthenas52 = red.insertVertex(athenas52);
		Vertex vAthenas53 = red.insertVertex(athenas53);
		Vertex vAthenas54 = red.insertVertex(athenas54);

		// Aristas

		// Trayectos linea VERDE

		Trayecto trayecto1 = new Trayecto(athenas1, athenas2, 2, "V");
		Trayecto trayecto2 = new Trayecto(athenas2, athenas3, 2, "V");
		Trayecto trayecto3 = new Trayecto(athenas3, athenas4, 2, "V");
		Trayecto trayecto4 = new Trayecto(athenas4, athenas5, 2, "V");
		Trayecto trayecto5 = new Trayecto(athenas5, athenas6, 2, "V");
		Trayecto trayecto6 = new Trayecto(athenas6, athenas7, 3, "V");
		Trayecto trayecto7 = new Trayecto(athenas7, athenas8, 1, "V");
		Trayecto trayecto8 = new Trayecto(athenas8, athenas9, 1, "V");
		Trayecto trayecto9 = new Trayecto(athenas9, athenas10, 2, "V");
		Trayecto trayecto10 = new Trayecto(athenas10, athenas11, 1, "V");
		Trayecto trayecto11 = new Trayecto(athenas11, athenas12, 2, "V");
		Trayecto trayecto12 = new Trayecto(athenas12, athenas13, 1, "V");
		Trayecto trayecto13 = new Trayecto(athenas13, athenas14, 1, "V");
		Trayecto trayecto15 = new Trayecto(athenas14, athenas15, 2, "V");
		Trayecto trayecto16 = new Trayecto(athenas15, athenas16, 2, "V");
		Trayecto trayecto18 = new Trayecto(athenas16, athenas17, 2, "V");
		Trayecto trayecto20 = new Trayecto(athenas17, athenas18, 1, "V");
		Trayecto trayecto21 = new Trayecto(athenas18, athenas19, 2, "V");
		Trayecto trayecto22 = new Trayecto(athenas19, athenas20, 2, "V");
		Trayecto trayecto23 = new Trayecto(athenas20, athenas21, 1, "V");
		Trayecto trayecto24 = new Trayecto(athenas21, athenas22, 3, "V");
		Trayecto trayecto25 = new Trayecto(athenas22, athenas23, 3, "V");
		Trayecto trayecto26 = new Trayecto(athenas23, athenas24, 3, "V");

		// Trayectos linea ROJA

		Trayecto trayecto27 = new Trayecto(athenas25, athenas26, 1, "R");
		Trayecto trayecto28 = new Trayecto(athenas26, athenas14, 2, "R");
		Trayecto trayecto29 = new Trayecto(athenas14, athenas27, 3, "R");
		Trayecto trayecto30 = new Trayecto(athenas27, athenas28, 1, "R");
		Trayecto trayecto31 = new Trayecto(athenas28, athenas16, 1, "R");
		Trayecto trayecto32 = new Trayecto(athenas16, athenas29, 1, "R");
		Trayecto trayecto33 = new Trayecto(athenas29, athenas30, 1, "R");
		Trayecto trayecto34 = new Trayecto(athenas30, athenas31, 2, "R");
		Trayecto trayecto35 = new Trayecto(athenas31, athenas32, 1, "R");
		Trayecto trayecto36 = new Trayecto(athenas32, athenas33, 1, "R");
		Trayecto trayecto37 = new Trayecto(athenas33, athenas34, 1, "R");
		Trayecto trayecto38 = new Trayecto(athenas34, athenas35, 2, "R");
		Trayecto trayecto39 = new Trayecto(athenas35, athenas36, 1, "R");

		// Trayectos linea AZUL

		Trayecto trayecto40 = new Trayecto(athenas37, athenas38, 1, "A");
		Trayecto trayecto41 = new Trayecto(athenas38, athenas39, 2, "A");
		Trayecto trayecto42 = new Trayecto(athenas39, athenas17, 1, "A");
		Trayecto trayecto43 = new Trayecto(athenas17, athenas30, 1, "A");
		Trayecto trayecto44 = new Trayecto(athenas30, athenas40, 1, "A");
		Trayecto trayecto45 = new Trayecto(athenas40, athenas41, 1, "A");
		Trayecto trayecto46 = new Trayecto(athenas41, athenas42, 2, "A");
		Trayecto trayecto47 = new Trayecto(athenas42, athenas43, 2, "A");
		Trayecto trayecto48 = new Trayecto(athenas43, athenas44, 2, "A");
		Trayecto trayecto49 = new Trayecto(athenas44, athenas45, 1, "A");
		Trayecto trayecto50 = new Trayecto(athenas45, athenas46, 2, "A");
		Trayecto trayecto51 = new Trayecto(athenas46, athenas47, 1, "A");
		Trayecto trayecto52 = new Trayecto(athenas47, athenas48, 2, "A");
		Trayecto trayecto53 = new Trayecto(athenas48, athenas49, 1, "A");
		Trayecto trayecto54 = new Trayecto(athenas49, athenas50, 2, "A");
		Trayecto trayecto55 = new Trayecto(athenas50, athenas51, 3, "A");
		Trayecto trayecto56 = new Trayecto(athenas51, athenas52, 3, "A");
		Trayecto trayecto57 = new Trayecto(athenas52, athenas53, 6, "A");
		Trayecto trayecto58 = new Trayecto(athenas53, athenas54, 6, "A");

		// Aristas linea VERDE

		red.insertUndirectedEdge(vAthenas1, vAthenas2, trayecto1);
		red.insertUndirectedEdge(vAthenas2, vAthenas3, trayecto2);
		red.insertUndirectedEdge(vAthenas3, vAthenas4, trayecto3);
		red.insertUndirectedEdge(vAthenas4, vAthenas5, trayecto4);
		red.insertUndirectedEdge(vAthenas5, vAthenas6, trayecto5);
		red.insertUndirectedEdge(vAthenas6, vAthenas7, trayecto6);
		red.insertUndirectedEdge(vAthenas7, vAthenas8, trayecto7);
		red.insertUndirectedEdge(vAthenas8, vAthenas9, trayecto8);
		red.insertUndirectedEdge(vAthenas9, vAthenas10, trayecto9);
		red.insertUndirectedEdge(vAthenas10, vAthenas11, trayecto10);
		red.insertUndirectedEdge(vAthenas11, vAthenas12, trayecto11);
		red.insertUndirectedEdge(vAthenas12, vAthenas13, trayecto12);
		red.insertUndirectedEdge(vAthenas13, vAthenas14, trayecto13);
		red.insertUndirectedEdge(vAthenas14, vAthenas15, trayecto15);
		red.insertUndirectedEdge(vAthenas15, vAthenas16, trayecto16);
		red.insertUndirectedEdge(vAthenas16, vAthenas17, trayecto18);
		red.insertUndirectedEdge(vAthenas17, vAthenas18, trayecto20);
		red.insertUndirectedEdge(vAthenas18, vAthenas19, trayecto21);
		red.insertUndirectedEdge(vAthenas19, vAthenas20, trayecto22);
		red.insertUndirectedEdge(vAthenas20, vAthenas21, trayecto23);
		red.insertUndirectedEdge(vAthenas21, vAthenas22, trayecto24);
		red.insertUndirectedEdge(vAthenas22, vAthenas23, trayecto25);
		red.insertUndirectedEdge(vAthenas23, vAthenas24, trayecto26);

		// Aristas linea ROJA

		red.insertUndirectedEdge(vAthenas25, vAthenas26, trayecto27);
		red.insertUndirectedEdge(vAthenas26, vAthenas14, trayecto28);
		red.insertUndirectedEdge(vAthenas14, vAthenas27, trayecto29);
		red.insertUndirectedEdge(vAthenas27, vAthenas28, trayecto30);
		red.insertUndirectedEdge(vAthenas28, vAthenas16, trayecto31);
		red.insertUndirectedEdge(vAthenas16, vAthenas29, trayecto32);
		red.insertUndirectedEdge(vAthenas29, vAthenas30, trayecto33);
		red.insertUndirectedEdge(vAthenas30, vAthenas31, trayecto34);
		red.insertUndirectedEdge(vAthenas31, vAthenas32, trayecto35);
		red.insertUndirectedEdge(vAthenas32, vAthenas33, trayecto36);
		red.insertUndirectedEdge(vAthenas33, vAthenas34, trayecto37);
		red.insertUndirectedEdge(vAthenas34, vAthenas35, trayecto38);
		red.insertUndirectedEdge(vAthenas35, vAthenas36, trayecto39);

		// Aristas linea AZUL

		red.insertUndirectedEdge(vAthenas37, vAthenas38, trayecto40);
		red.insertUndirectedEdge(vAthenas38, vAthenas39, trayecto41);
		red.insertUndirectedEdge(vAthenas39, vAthenas17, trayecto42);
		red.insertUndirectedEdge(vAthenas17, vAthenas30, trayecto43);
		red.insertUndirectedEdge(vAthenas30, vAthenas40, trayecto44);
		red.insertUndirectedEdge(vAthenas40, vAthenas41, trayecto45);
		red.insertUndirectedEdge(vAthenas41, vAthenas42, trayecto46);
		red.insertUndirectedEdge(vAthenas42, vAthenas43, trayecto47);
		red.insertUndirectedEdge(vAthenas43, vAthenas44, trayecto48);
		red.insertUndirectedEdge(vAthenas44, vAthenas45, trayecto49);
		red.insertUndirectedEdge(vAthenas45, vAthenas46, trayecto50);
		red.insertUndirectedEdge(vAthenas46, vAthenas47, trayecto51);
		red.insertUndirectedEdge(vAthenas47, vAthenas48, trayecto52);
		red.insertUndirectedEdge(vAthenas48, vAthenas49, trayecto53);
		red.insertUndirectedEdge(vAthenas49, vAthenas50, trayecto54);
		red.insertUndirectedEdge(vAthenas50, vAthenas51, trayecto55);
		red.insertUndirectedEdge(vAthenas51, vAthenas52, trayecto56);
		red.insertUndirectedEdge(vAthenas52, vAthenas53, trayecto57);
		red.insertUndirectedEdge(vAthenas53, vAthenas54, trayecto58);
	}

	/**
	 * Devuelve el v�rtice asociado a la estaci�n pasada como par�metro
	 * 
	 * @param estacion --> puntero
	 * @return
	 */
	public Vertex<Estacion> getVertice(Estacion estacion) {
		for (Vertex<Estacion> v : red.vertices()) {
			if (v.element() == estacion) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Devuelve las estaciones conectadas con la estaci�n pasada como par�metro
	 * 
	 * @param estacion
	 * @return
	 */
	public List<Vertex<Estacion>> vecinos(Vertex<Estacion> estacion) {
		List<Vertex<Estacion>> res = new ArrayList<>();

		for (Edge<Trayecto> e : red.edges(estacion)) {
			res.add(red.opposite(estacion, e));
		}

		return res;
	}

	/**
	 * Devuelve el trayecto entre dos estaciones
	 * 
	 * @param estacion1 --> puntero
	 * @param estacion2 --> puntero
	 * @return
	 */
	public Trayecto getTrayecto(Estacion estacion1, Estacion estacion2) {
		Vertex<Estacion> vertice1 = getVertice(estacion1);
		Vertex<Estacion> vertice2 = getVertice(estacion2);

		for (Edge<Trayecto> e : red.edges(vertice1)) {
			if (red.opposite(vertice1, e) == vertice2)
				return e.element();
		}

		return null;
	}

	public Estacion getEstacionByName(String name) {
		for (Vertex<Estacion> v : red.vertices()) {
			if (v.element().getNombre().equalsIgnoreCase(name))
				return v.element();
		}
		return null;
	}

	public ArrayList<String> getEstaciones() {
		ArrayList<String> res = new ArrayList<>();
		for (Vertex<Estacion> v : red.vertices())
			res.add(v.element().getNombre());
		return res;
	}

}
