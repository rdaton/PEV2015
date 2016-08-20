package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import control.Funcion1;
import logica.TipoCruce;
import logica.TipoMutacion;
import logica.TipoSeleccion;

@SuppressWarnings("serial")
public class MiPanelIzquierdo extends JPanel {

	private MiPanelDerecho pnlDerecho;
	private String elMejor;
	private String elPeor;

	private JComboBox<TipoSeleccion> comboSeleccion;
	protected JComboBox<TipoMutacion> comboMutacion;
	private JComboBox<TipoCruce> comboCruce;
	private JTextField textPoblacion;
	private JTextField textIteraciones;
	private JTextField textProCruces;
	private JTextField textProMutacion;
	private JTextField textTorneo;
	//private JTextField textSemilla;
	private JCheckBox checkElitismo;

	protected JButton buttonCopia;
	protected JButton buttonRelanzar;


	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public MiPanelIzquierdo() {
		// ===========================
		// ----- PANEL IZQUIERDO -----
		// ===========================

		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension (270, 720));

		// ****** OPCIONES ******
		JPanel pnlOptions = new JPanel();
		pnlOptions.setPreferredSize(new Dimension (270, 450));
		pnlOptions.setBackground(Color.red);

		this.add(pnlOptions, BorderLayout.NORTH);

		// TORNEO
		JLabel torneo = new JLabel("N torneo");
		torneo.setPreferredSize(new Dimension (150, 20));

		textTorneo = new JTextField();
		textTorneo.setText("4");
		textTorneo.setPreferredSize(new Dimension (100, 20));

		// POBLACION
		JLabel lblPoblacion = new JLabel("Poblacion: ");
		lblPoblacion.setPreferredSize(new Dimension (150, 20));

		textPoblacion = new JTextField();
		textPoblacion.setText("3");
		textPoblacion.setPreferredSize(new Dimension (100, 20));

		// GENERACIONES
		JLabel lblGeneraciones = new JLabel("Generaciones: ");
		lblGeneraciones.setPreferredSize(new Dimension (150, 20));

		textIteraciones = new JTextField();
		textIteraciones.setText("1");
		textIteraciones.setPreferredSize(new Dimension (100, 20));

		// CRUCE %
		JLabel lblCruce = new JLabel("% Cruce");
		lblCruce.setPreferredSize(new Dimension (150, 20));

		textProCruces = new JTextField();
		textProCruces.setText("0.6");
		textProCruces.setPreferredSize(new Dimension (100, 20));

		// MUTACION %
		JLabel lblMutacion = new JLabel("% Mutacion");
		lblMutacion.setPreferredSize(new Dimension (150, 20));

		textProMutacion = new JTextField();
		textProMutacion.setText("0.06");
		textProMutacion.setPreferredSize(new Dimension (100, 20));

		// SEMILLA
		//JLabel lblSemilla = new JLabel("Semilla");
		//lblSemilla.setPreferredSize(new Dimension (150, 20));
		//
		//textSemilla = new JTextField();
		//textSemilla.setPreferredSize(new Dimension (100, 20));

		// TIPO CRUCE -- select
		JLabel lblTipoCruce = new JLabel("Cruce");
		lblTipoCruce.setPreferredSize(new Dimension (150, 20));

		comboCruce = new JComboBox<TipoCruce>(TipoCruce.values());
		comboCruce.setPreferredSize(new Dimension (100, 20));

		// SELECCION -- select
		JLabel lblSeleccion = new JLabel("Seleccion");
		lblSeleccion.setPreferredSize(new Dimension (150, 20));

		comboSeleccion = new JComboBox<TipoSeleccion>(TipoSeleccion.values());
		comboSeleccion.setPreferredSize(new Dimension (100, 20));

		// MUTACION -- select
		JLabel lblMutacionSelect = new JLabel("Mutacion: ");
		lblMutacionSelect.setPreferredSize(new Dimension (150, 20));

		comboMutacion = new JComboBox<TipoMutacion>(TipoMutacion.values());
		comboMutacion.setPreferredSize(new Dimension (100, 20));

		// ELITISMO
		checkElitismo = new JCheckBox("Elitismo");
		checkElitismo.setPreferredSize(new Dimension (100, 20));

		// A—ADIMOS TODO
		pnlOptions.add(torneo);
		pnlOptions.add(textTorneo);

		pnlOptions.add(lblPoblacion);
		pnlOptions.add(textPoblacion);

		pnlOptions.add(lblGeneraciones);
		pnlOptions.add(textIteraciones);

		pnlOptions.add(lblCruce);
		pnlOptions.add(textProCruces);

		pnlOptions.add(lblMutacion);
		pnlOptions.add(textProMutacion);

		//pnlOptions.add(lblSemilla);
		//pnlOptions.add(textSemilla);

		pnlOptions.add(lblTipoCruce);
		pnlOptions.add(comboCruce);

		pnlOptions.add(lblSeleccion);
		pnlOptions.add(comboSeleccion);

		pnlOptions.add(lblMutacionSelect);
		pnlOptions.add(comboMutacion);

		pnlOptions.add(checkElitismo);

		// ****** BOTONES ******
		JPanel pnlButtons = new JPanel();
		pnlButtons.setPreferredSize(new Dimension (270, 500));
		pnlButtons.setBackground(Color.GREEN);

		buttonCopia = new JButton("Lanzar este AG");
		buttonCopia.setPreferredSize(new Dimension (180, 60));
		buttonCopia.setEnabled(false);
		buttonCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDerecho.addNewGrafic();
			}
		});

		// AÒADIMOS TODO
		pnlButtons.add(buttonCopia);
		this.add(pnlButtons, BorderLayout.SOUTH);
		this.revalidate();
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public JPanel invocarFuncion() {
		int tSeleccion = Integer.valueOf(this.comboSeleccion.getSelectedIndex());
		int tMutacion = Integer.valueOf(this.comboMutacion.getSelectedIndex());
		int tCruce = Integer.valueOf(this.comboCruce.getSelectedIndex());
		int num_iter = Integer.valueOf(this.textIteraciones.getText());
		int tam_pob = Integer.valueOf(this.textPoblacion.getText());
		double pCruces = Double.valueOf(this.textProCruces.getText());
		double pMut = Double.valueOf(this.textProMutacion.getText());
		//double semilla = Double.valueOf(this.textSemilla.getText());
		boolean elitismo = this.checkElitismo.isSelected();
		int torneo = Integer.valueOf(this.textTorneo.getText());
		List<List<Object>> resultados = null;

		// EJECUTAMOS FUNCION
		Funcion1 unaFuncion1 = new Funcion1(torneo, tam_pob, num_iter, pCruces, pMut, tCruce, tSeleccion, tMutacion, elitismo);
		elPeor = unaFuncion1.getMejorEntrePeores();
		resultados = unaFuncion1.dameResultados();


		if (resultados == null) { return null; }

		// *********************
		// ----- GRAFICA
		// *********************

		// CARGAMOS VALORES DE LA GRAFICA
		double[] x = new double [resultados.get(0).size()];
		for (int i=0;i<resultados.get(0).size();i++) {
			x[i] = i;
		}

		double[] y = new double[resultados.get(0).size()];
		for (int i=0;i<resultados.get(0).size();i++) {
			y[i] = (Double) resultados.get(0).get(i);
		}

		double[] y2 = new double[resultados.get(1).size()];
		for (int i=0;i<resultados.get(1).size();i++) {
			y2[i] = (Double) resultados.get(1).get(i);
		}

		double[] y3 = new double[resultados.get(2).size()];
		for (int i=0;i<resultados.get(2).size();i++) {
			y3[i] = (Double) resultados.get(2).get(i);
		}

		Plot2DPanel plot = new Plot2DPanel();
		plot.setPreferredSize(new Dimension(600,455));
		plot.setAxisLabels("Generacion", "Adaptacion");
		plot.addLegend("SOUTH");

		// INTRODUCIMOS VALORES EN LA GRAFICA
		plot.addLinePlot("Mejor Absoluto",Color.BLUE, x, y);
		plot.addLinePlot("Mejor de Generacion",Color.RED, x, y2);
		plot.addLinePlot("Media de Generacion",Color.GREEN, x, y3);

		//VACIAMOS LOS RESULTADOS
		resultados = null;

		JPanel frame = new JPanel();
		frame.add(plot);
		return frame;
	}

	public void setPnlDerecho(MiPanelDerecho pnlDerecho) {
		this.pnlDerecho = pnlDerecho;
	}

	public String getMejorEntreMejores() {
		return elMejor;
	}

	public String getMejorEntrePeores() {
		return elPeor;
	}
}