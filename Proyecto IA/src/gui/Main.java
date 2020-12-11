package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.AEstrella;
import app.Estacion;
import app.Grafo;

public class Main {

	private JFrame frame;
	private static JPanel panelMap;
	private static FondoMapa fondo = new FondoMapa();
	private static ArrayList<Estacion> ruta = new ArrayList<>();
	private static ArrayList<Arista> aristasDibujadas = new ArrayList<>();
	private static ArrayList<Nodo> nodosDibujados = new ArrayList<>();
	private static ArrayList<Estacion> trasbordos = new ArrayList<>();
	private static Grafo redDatos = new Grafo() ;
	private static Grafo red ;
	private static JLabel lbl_duracionRuta, lblNumParadas, lblNumTras, lblStringDuracionRuta, lblMaxMin;
	private static JComboBox<String> comboOrigen, comboDestino;
	private static AEstrella alg;
	private static JRadioButton checkMovRed;
	private JLabel lblNewLabel;
	private JSpinner timeSpinner;
	private static int nParadas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		/**
		 * Set L&F
		 */
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1243, 754);
		frame.setTitle("Metro Atenas");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/logo.png")));
		// Para centrar el frame en la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		// Combobox de los origenes
		comboOrigen = new JComboBox<String>();
		comboOrigen.setBounds(794, 88, 173, 33);
		comboOrigen.setMaximumRowCount(50);
		frame.getContentPane().add(comboOrigen);
		for (String s : addJComboBoxSeparators(redDatos.getEstaciones()))
			comboOrigen.addItem(s);
		comboOrigen.setSelectedIndex(1);

		// Combobox de los destinos
		comboDestino = new JComboBox<String>();
		comboDestino.setBounds(1004, 88, 173, 33);
		comboDestino.setMaximumRowCount(50);
		frame.getContentPane().add(comboDestino);
		for (String s : addJComboBoxSeparators(redDatos.getEstaciones()))
			comboDestino.addItem(s);
		comboDestino.setSelectedIndex(1);

		JLabel lblOrigen = new JLabel("ORIGEN");
		lblOrigen.setFont(new Font("Poppins", Font.BOLD, 17));
		lblOrigen.setBounds(797, 65, 98, 21);
		frame.getContentPane().add(lblOrigen);

		JLabel lblDestino = new JLabel("DESTINO");
		lblDestino.setFont(new Font("Poppins", Font.BOLD, 17));
		lblDestino.setBounds(1012, 65, 98, 21);
		frame.getContentPane().add(lblDestino);

		JButton btnGo = new JButton("BUSCAR");
		btnGo.setFont(new Font("Poppins", Font.BOLD, 17));
		btnGo.setToolTipText("Buscar Ruta");
		btnGo.setIcon(new ImageIcon(Main.class.getResource("/resources/lupa.png")));
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				red = new Grafo();
				alg = new AEstrella(red, red.getEstacionByName(comboOrigen.getSelectedItem().toString()),
						red.getEstacionByName(comboDestino.getSelectedItem().toString()), false,
						Integer.valueOf(new SimpleDateFormat("HH").format(timeSpinner.getValue())));
				ruta = alg.getResultado();
				nParadas = ruta.size();
				if (ruta.size() < 2) {
					JOptionPane.showMessageDialog(new JFrame(), "Introduce dos estacion distintas.", "Comentarios",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					drawNodos();
					drawRuta();
					drawDatos();
					fondo.setLocation(0, 0);
					fondo.setSize(706, 655);
					panelMap.add(fondo);
					frame.repaint();
				}
			}
		});
		btnGo.setBounds(1004, 166, 173, 111);
		frame.getContentPane().add(btnGo);

		panelMap = new JPanel();
		panelMap.setBounds(33, 33, 706, 655);
		frame.getContentPane().add(panelMap);
		panelMap.setLayout(null);

		// JLabel lblNewLabel_1 = new JLabel("Movilidad Reducida");
		// lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		// lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		// lblNewLabel_1.setBounds(794, 160, 122, 27);
		// frame.getContentPane().add(lblNewLabel_1);

		// checkMovRed = new JRadioButton("");
		// checkMovRed.setBounds(905, 164, 29, 21);
		// frame.getContentPane().add(checkMovRed);

		lblStringDuracionRuta = new JLabel("<html>Duración estimada de la ruta<br>desde ______ hasta _______</html>");
		lblStringDuracionRuta.setHorizontalAlignment(SwingConstants.CENTER);
		lblStringDuracionRuta.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblStringDuracionRuta.setBounds(807, 346, 211, 53);
		frame.getContentPane().add(lblStringDuracionRuta);

		// Fondo al final siempre
		fondo.setLocation(0, 0);
		fondo.setSize(706, 655);
		panelMap.add(fondo);
		frame.repaint();

		lbl_duracionRuta = new JLabel("0 min");
		lbl_duracionRuta.setFont(new Font("Poppins", Font.BOLD, 25));
		lbl_duracionRuta.setBounds(1033, 350, 144, 46);
		frame.getContentPane().add(lbl_duracionRuta);

		JSeparator separator = new JSeparator();
		separator.setBounds(773, 325, 439, 2);
		frame.getContentPane().add(separator);

		JLabel lblNmeroDeParadas = new JLabel("Número de paradas");
		lblNmeroDeParadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNmeroDeParadas.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNmeroDeParadas.setBounds(807, 422, 211, 53);
		frame.getContentPane().add(lblNmeroDeParadas);

		JLabel lblNmeroDeTrasbordos = new JLabel("Número de trasbordos");
		lblNmeroDeTrasbordos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNmeroDeTrasbordos.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNmeroDeTrasbordos.setBounds(807, 485, 211, 53);
		frame.getContentPane().add(lblNmeroDeTrasbordos);

		lblNumParadas = new JLabel("0");
		lblNumParadas.setFont(new Font("Poppins", Font.BOLD, 25));
		lblNumParadas.setBounds(1033, 422, 144, 46);
		frame.getContentPane().add(lblNumParadas);

		lblNumTras = new JLabel("0");
		lblNumTras.setFont(new Font("Poppins", Font.BOLD, 25));
		lblNumTras.setBounds(1033, 485, 144, 46);
		frame.getContentPane().add(lblNumTras);

//		lblMaxMin = new JLabel("(min - max)");
//		lblMaxMin.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		lblMaxMin.setHorizontalAlignment(SwingConstants.CENTER);
//		lblMaxMin.setBounds(822, 409, 268, 33);
//		frame.getContentPane().add(lblMaxMin);

		timeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setBounds(896, 194, 56, 33);
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date());
		frame.getContentPane().add(timeSpinner);

		lblNewLabel = new JLabel("Hora de salida");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(802, 204, 101, 13);
		frame.getContentPane().add(lblNewLabel);

	}

	private ArrayList<String> addJComboBoxSeparators(ArrayList<String> a) {
		a.add(0, "------Linea 1 (verde)--------");
		a.add(25, "------Linea 2 (roja)--------");
		a.add(38, "------Linea 3 (azul)--------");
		return a;
	}

	private static void drawDatos() {
		lblStringDuracionRuta
				.setText("<html>Duración estimada de la ruta<br>desde <b>" + comboOrigen.getSelectedItem().toString()
						+ "</b> hasta <b>" + comboDestino.getSelectedItem().toString() + "</b></html>");
		lblNumTras.setText(trasbordos.size() + "");
		lblNumParadas.setText(nParadas + "");
		// La ultima suma de getPenalizacion se debe a la primera parada
		double dur = alg.getTiempoRuta();
		double sinPen = alg.getTiempoSinPenalizaciones();
		lbl_duracionRuta.setText(Math.round(dur) + " min");

		// int min = (int) Math.round(sinPen + alg.getMinPenalizacion() *
		// trasbordos.size() + alg.getMinPenalizacion());
		// int max = (int) Math.round(sinPen + alg.getMaxPenalizacion() *
		// trasbordos.size() + alg.getMaxPenalizacion());

		// lblMaxMin.setText("(" + min + " min - " + max + " min)");
	}

	private static void drawRuta() {
		// Añadimos las estaciones ficticias para las esquinas
		anadirEstacionesFicticias();

		// Para cada par de vertices pintar la arista correspondiente
		for (int i = 0; i < ruta.size() - 1; i++) {
			// Creamos la arista donde se calculan las posiciones
			Arista a = new Arista(ruta.get(i), ruta.get(i + 1));
			// Colocamos la esquina superior derecha donde nos diga la arista
			a.setBounds(a.getxBox(), a.getyBox(), 400, 400);
			// Hacemos el fondo transparente
			a.setOpaque(false);
			// La añadimos al panel
			panelMap.add(a);
			// La añadimos a la lista para poder borrarla despues
			aristasDibujadas.add(a);
		}
	}

	private static void drawNodos() {
		int radioCir = 10;
		Color c = Color.white;

		// Pintamos los trasbordos
		for (int i = 0; i < ruta.size() - 2; i++) {
			String LineaT1 = red.getTrayecto(ruta.get(i), ruta.get(i + 1)).getLinea();
			String LineaT2 = red.getTrayecto(ruta.get(i + 1), ruta.get(i + 2)).getLinea();
			if (!LineaT1.equals(LineaT2)) {
				trasbordos.add(red.getVertice(ruta.get(i + 1)).element());
				Nodo t = new Nodo(radioCir, Color.green);
				t.setBounds(ruta.get(i + 1).getxPic() - radioCir / 2, ruta.get(i + 1).getyPic() - radioCir / 2, 50, 50);
				t.setOpaque(false);
				panelMap.add(t);
				nodosDibujados.add(t);
			}
		}

		for (int i = 0; i < ruta.size(); i++) {
			if (i == 0)
				c = Color.cyan;
			else if (i == ruta.size() - 1)
				c = Color.orange;
			Nodo t = new Nodo(radioCir, c);
			t.setBounds(ruta.get(i).getxPic() - radioCir / 2, ruta.get(i).getyPic() - radioCir / 2, 50, 50);
			t.setOpaque(false);
			panelMap.add(t);
			nodosDibujados.add(t);
			c = Color.white;
		}

	}

	private static void anadirEstacionesFicticias() {
		for (int i = 0; i < ruta.size() - 1; i++) {
			// Attiki - Victoria
			if ((ruta.get(i).getNombre().toLowerCase().equals("attiki")
					&& ruta.get(i + 1).getNombre().toLowerCase().equals("victoria"))
					|| (ruta.get(i).getNombre().toLowerCase().equals("victoria")
							&& ruta.get(i + 1).getNombre().toLowerCase().equals("attiki"))) {
				ruta.add(i + 1, new Estacion("random", 0, 0, 208, 346));// Estacion ficticia
			}
			// Irini - Iraklio
			if ((ruta.get(i).getNombre().toLowerCase().equals("irini")
					&& ruta.get(i + 1).getNombre().toLowerCase().equals("iraklio"))
					|| (ruta.get(i).getNombre().toLowerCase().equals("iraklio")
							&& ruta.get(i + 1).getNombre().toLowerCase().equals("irini"))) {
				ruta.add(i + 1, new Estacion("d", 0, 0, 338, 146));// Estacion ficticia
			}
			// Omonia - Metaxourghio
			if ((ruta.get(i).getNombre().toLowerCase().equals("omonia")
					&& ruta.get(i + 1).getNombre().toLowerCase().equals("metaxourghio"))
					|| (ruta.get(i).getNombre().toLowerCase().equals("metaxourghio")
							&& ruta.get(i + 1).getNombre().toLowerCase().equals("omonia"))) {
				ruta.add(i + 1, new Estacion("d", 0, 0, 177, 387));// Estacion ficticia
			}
			// Faliro - Piraeus
			if ((ruta.get(i).getNombre().toLowerCase().equals("faliro")
					&& ruta.get(i + 1).getNombre().toLowerCase().equals("piraeus"))
					|| (ruta.get(i).getNombre().toLowerCase().equals("piraeus")
							&& ruta.get(i + 1).getNombre().toLowerCase().equals("faliro"))) {
				ruta.add(i + 1, new Estacion("d", 0, 0, 95, 587));// Estacion ficticia
			}
			// Kerameikos - Monastiraki
			if ((ruta.get(i).getNombre().toLowerCase().equals("monastiraki")
					&& ruta.get(i + 1).getNombre().toLowerCase().equals("kerameikos"))
					|| (ruta.get(i).getNombre().toLowerCase().equals("kerameikos")
							&& ruta.get(i + 1).getNombre().toLowerCase().equals("monastiraki"))) {
				ruta.add(i + 1, new Estacion("d", 0, 0, 138, 424));// Estacion ficticia
			}
			// Evangelismos - Megaro Moussikis
			if ((ruta.get(i).getNombre().toLowerCase().equals("evangelismos")
					&& ruta.get(i + 1).getNombre().toLowerCase().equals("megaro moussikis"))
					|| (ruta.get(i).getNombre().toLowerCase().equals("megaro moussikis")
							&& ruta.get(i + 1).getNombre().toLowerCase().equals("evangelismos"))) {
				ruta.add(i + 1, new Estacion("d", 0, 0, 353, 423));// Estacion ficticia
			}
			// Halandri - Pallini
			if ((ruta.get(i).getNombre().toLowerCase().equals("doukissis plakentias")
					&& ruta.get(i + 1).getNombre().toLowerCase().equals("pallini"))
					|| (ruta.get(i).getNombre().toLowerCase().equals("pallini")
							&& ruta.get(i + 1).getNombre().toLowerCase().equals("doukissis plakentias"))) {
				ruta.add(i + 1, new Estacion("d", 0, 0, 577, 252));// Estacion ficticia
			}
			// Koropi - Airport
			if ((ruta.get(i).getNombre().toLowerCase().equals("koropi")
					&& ruta.get(i + 1).getNombre().toLowerCase().equals("airport"))
					|| (ruta.get(i).getNombre().toLowerCase().equals("airport")
							&& ruta.get(i + 1).getNombre().toLowerCase().equals("koropi"))) {
				ruta.add(i + 1, new Estacion("d", 0, 0, 586, 445));// Estacion ficticia
			}
		}
	}

	private void clear() {

		// Limpiamos la ruta
		ruta.clear();

		// Eliminamos el fondo para redibujarlo mas tarde
		panelMap.remove(fondo);

		// Limpiamos los campos de busqueda y de Datos
		lbl_duracionRuta.setText("0 min");
		lblNumParadas.setText("0");
		lblNumTras.setText("0");
		// lblMaxMin.setText("(min - max)");
		trasbordos.clear();

		// Limpiamos las aristas dibujadas
		for (Arista a : aristasDibujadas)
			panelMap.remove(a);
		aristasDibujadas.clear();

		// Limpiamos los nodos
		for (Nodo t : nodosDibujados)
			panelMap.remove(t);
		nodosDibujados.clear();

	}
}
