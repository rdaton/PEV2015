package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import control.Funcion1;
import control.Funcion2;
import control.Funcion3;
import control.Funcion4;
import control.Funcion5;
import logica.TipoCruce;
import logica.TipoFuncion;
import logica.TipoSeleccion;

@SuppressWarnings("serial")
public class MiPanelIzquierdo extends JPanel {

	private MiPanelDerecho pnlDerecho;
	private String elMejor;
	private String elPeor;

	private JComboBox<TipoSeleccion> comboSeleccion;
	protected JComboBox<TipoFuncion> comboFuncion;
	private JComboBox<TipoCruce> comboCruce;
	private JTextField textPrecision;
	private JTextField textPoblacion;
	private JTextField textIteraciones;
	private JTextField textProCruces;
	private JTextField textProMutacion;
	//private JTextField textSemilla;
	private JCheckBox checkElitismo;
	
	private JTextField textArgF;
	private JTextField textArgF2;

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

		// FUNCIONES -- select
		JLabel lblFuncion = new JLabel("Funcion: ");
		lblFuncion.setPreferredSize(new Dimension (150, 20));

		comboFuncion = new JComboBox<TipoFuncion>(TipoFuncion.values());
		comboFuncion.setPreferredSize(new Dimension (100, 20));
		
		JLabel argf4 = new JLabel("Arg #");
		argf4.setPreferredSize(new Dimension (150, 20));
		JLabel torneo = new JLabel("N torneo");
		torneo.setPreferredSize(new Dimension (150, 20));

		// CROMOSOMAS
		JPanel pnlCromosomas = new JPanel();
		pnlCromosomas.setPreferredSize(new Dimension (270, 50));
		pnlCromosomas.setBorder(BorderFactory.createTitledBorder("Cromosomas"));

		JLabel lblCromosomas = new JLabel("Precision: ");
		lblCromosomas.setPreferredSize(new Dimension (150, 20));

		textPrecision = new JTextField();
		textPrecision.setText("0.1");
		textPrecision.setPreferredSize(new Dimension (100, 20));

		pnlCromosomas.add(lblCromosomas);
		pnlCromosomas.add(textPrecision);

		// POBLACION
		JLabel lblPoblacion = new JLabel("Poblacion: ");
		lblPoblacion.setPreferredSize(new Dimension (150, 20));

		textPoblacion = new JTextField();
		textPoblacion.setText("100");
		textPoblacion.setPreferredSize(new Dimension (100, 20));

		// GENERACIONES
		JLabel lblGeneraciones = new JLabel("Generaciones: ");
		lblGeneraciones.setPreferredSize(new Dimension (150, 20));

		textIteraciones = new JTextField();
		textIteraciones.setText("100");
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

		// ELITISMO
		checkElitismo = new JCheckBox("Elitismo");
		checkElitismo.setPreferredSize(new Dimension (100, 20));
		
		textArgF = new JTextField();
		textArgF.setText("3");
		textArgF.setBounds(102, 68, 70, 19);
		textArgF.setColumns(10);
		
		textArgF2 = new JTextField();
		textArgF2.setText("4");
		textArgF2.setBounds(102, 39, 70, 17);
		textArgF2.setColumns(10);

		// A�ADIMOS TODO
		pnlOptions.add(lblFuncion);
		pnlOptions.add(comboFuncion);
		
		pnlOptions.add(argf4);
		pnlOptions.add(textArgF);
		
		pnlOptions.add(torneo);
		pnlOptions.add(textArgF2);

		pnlOptions.add(pnlCromosomas);

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

		//buttonRelanzar = new JButton("Relanzar este AG");
		//buttonRelanzar.setPreferredSize(new Dimension (180, 60));
		//buttonRelanzar.setEnabled(false);
		//buttonRelanzar.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
		//
		//	}
		//});

		
		

		// A�ADIMOS TODO
		pnlButtons.add(buttonCopia);
		//pnlButtons.add(buttonRelanzar);
		//pnlButtons.add(buttonEliminar);

		this.add(pnlButtons, BorderLayout.SOUTH);

		this.revalidate();
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public JPanel invocarFuncion() {
		int tSeleccion = Integer.valueOf(this.comboSeleccion.getSelectedIndex());
		int selec = Integer.valueOf(this.comboFuncion.getSelectedIndex());
		int tCruce = Integer.valueOf(this.comboCruce.getSelectedIndex());
		int num_iter = Integer.valueOf(this.textIteraciones.getText());
		int tam_pob = Integer.valueOf(this.textPoblacion.getText());
		double pCruces = Double.valueOf(this.textProCruces.getText());
		double pMut = Double.valueOf(this.textProMutacion.getText());
		double prec = Double.valueOf(this.textPrecision.getText());
		//double semilla = Double.valueOf(this.textSemilla.getText());
		boolean elitismo = this.checkElitismo.isSelected();
		int argf1=Integer.valueOf(this.textArgF2.getText());
		int argf2=Integer.valueOf(this.textArgF.getText());
		List<List<Object>> resultados = null;

		// ELEGIR FUNCION
		switch (selec) {
		case 0:
			Funcion1 unaFuncion1 = new Funcion1(argf1,argf2,prec, tam_pob, num_iter, pCruces, pMut, tCruce, tSeleccion, elitismo);
			elPeor = unaFuncion1.getMejorEntrePeores();
			resultados = unaFuncion1.dameResultados();
			break;
		case 1:
			Funcion2 unaFuncion2 = new Funcion2(argf1,argf2,prec, tam_pob, num_iter, pCruces, pMut, tCruce, tSeleccion, elitismo);
			elMejor = unaFuncion2.getMejorEntreMejores();
			resultados = unaFuncion2.dameResultados();
			break;
		case 2:
			Funcion3 unaFuncion3 = new Funcion3(argf1,argf2,prec, tam_pob, num_iter, pCruces, pMut, tCruce, tSeleccion, elitismo);
			elMejor = unaFuncion3.getMejorEntreMejores();
			resultados = unaFuncion3.dameResultados();
			break;
		case 3:
			Funcion4 unaFuncion4 = new Funcion4(argf1,argf2,prec, tam_pob, num_iter, pCruces, pMut, tCruce, tSeleccion, elitismo);
			elPeor = unaFuncion4.getMejorEntrePeores();
			resultados = unaFuncion4.dameResultados();
			break;
		case 4:
			Funcion5 unaFuncion5 = new Funcion5(argf1,argf2,prec, tam_pob, num_iter, pCruces, pMut, tCruce, tSeleccion, elitismo);
			elPeor = unaFuncion5.getMejorEntrePeores();
			resultados = unaFuncion5.dameResultados();
			break;
		}

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