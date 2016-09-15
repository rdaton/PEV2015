package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

	protected MiPanelDerecho pnlDerecho;
	protected String elMejor;
	

	protected JComboBox<TipoSeleccion> comboSeleccion;
	protected JCheckBox todasSelecciones;
	protected JComboBox<TipoMutacion> comboMutacion;
	protected JCheckBox todasMutaciones;
	protected JComboBox<TipoCruce> comboCruce;
	protected JCheckBox todosCruces;
	protected JTextField textPoblacion;
	protected JTextField textPoblacion2;
	protected JTextField textIteraciones;
	protected JTextField textIteraciones2;
	protected JTextField textProCruces;
	protected JTextField textProCruces2;
	protected JTextField textProMutacion;
	protected JTextField textProMutacion2;
	protected JTextField textTorneo;
	protected JTextField textTorneo2;
	//private JTextField textSemilla;
	protected JCheckBox checkElitismo;

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
		torneo.setPreferredSize(new Dimension (100, 20));

		textTorneo = new JTextField();
		textTorneo.setText("4");
		textTorneo.setPreferredSize(new Dimension (50, 20));

		textTorneo2 = new JTextField();
		textTorneo2.setText("4");
		textTorneo2.setPreferredSize(new Dimension (50, 20));
		// POBLACION
		JLabel lblPoblacion = new JLabel("Poblacion: ");
		lblPoblacion.setPreferredSize(new Dimension (100, 20));

		textPoblacion = new JTextField();
		textPoblacion.setText("300");
		textPoblacion.setPreferredSize(new Dimension (50, 20));
		
		textPoblacion2 = new JTextField();
		textPoblacion2.setText("300");
		textPoblacion2.setPreferredSize(new Dimension (50, 20));

		// GENERACIONES
		JLabel lblGeneraciones = new JLabel("Generaciones: ");
		lblGeneraciones.setPreferredSize(new Dimension (100, 20));

		textIteraciones = new JTextField();
		textIteraciones.setText("50");
		textIteraciones.setPreferredSize(new Dimension (50, 20));
		
		textIteraciones2 = new JTextField();
		textIteraciones2.setText("50");
		textIteraciones2.setPreferredSize(new Dimension (50, 20));

		// CRUCE %
		JLabel lblCruce = new JLabel("% Cruce");
		lblCruce.setPreferredSize(new Dimension (100, 20));

		textProCruces = new JTextField();
		textProCruces.setText("0.6");
		textProCruces.setPreferredSize(new Dimension (50, 20));

		textProCruces2 = new JTextField();
		textProCruces2.setText("0.6");
		textProCruces2.setPreferredSize(new Dimension (50, 20));

		// MUTACION %
		JLabel lblMutacion = new JLabel("% Mutacion");
		lblMutacion.setPreferredSize(new Dimension (100, 20));

		textProMutacion = new JTextField();
		textProMutacion.setText("0.06");
		textProMutacion.setPreferredSize(new Dimension (50, 20));
		
		textProMutacion2 = new JTextField();
		textProMutacion2.setText("0.06");
		textProMutacion2.setPreferredSize(new Dimension (50, 20));

		// SEMILLA
		//JLabel lblSemilla = new JLabel("Semilla");
		//lblSemilla.setPreferredSize(new Dimension (100, 20));
		//
		//textSemilla = new JTextField();
		//textSemilla.setPreferredSize(new Dimension (100, 20));

		// TIPO CRUCE -- select
		JLabel lblTipoCruce = new JLabel("Cruce");
		lblTipoCruce.setPreferredSize(new Dimension (80, 20));

		comboCruce = new JComboBox<TipoCruce>(TipoCruce.values());
		comboCruce.setPreferredSize(new Dimension (70, 20));
		
		todosCruces=new JCheckBox("Todas");
		todosCruces.setPreferredSize(new Dimension(80,20));

		// SELECCION -- select
		JLabel lblSeleccion = new JLabel("Seleccion");
		lblSeleccion.setPreferredSize(new Dimension (80, 20));

		comboSeleccion = new JComboBox<TipoSeleccion>(TipoSeleccion.values());
		comboSeleccion.setPreferredSize(new Dimension (70, 20));
		
		todasSelecciones=new JCheckBox("Todas");
		todasSelecciones.setPreferredSize(new Dimension(80,20));

		// MUTACION -- select
		JLabel lblMutacionSelect = new JLabel("Mutacion: ");
		lblMutacionSelect.setPreferredSize(new Dimension (80, 20));

		comboMutacion = new JComboBox<TipoMutacion>(TipoMutacion.values());
		comboMutacion.setPreferredSize(new Dimension (70, 20));
		
		todasMutaciones=new JCheckBox("Todas");
		todasMutaciones.setPreferredSize(new Dimension(80,20));

		// ELITISMO
		checkElitismo = new JCheckBox("Elitismo");
		checkElitismo.setPreferredSize(new Dimension (100, 20));

		// A�ADIMOS TODO
		pnlOptions.add(torneo);
		pnlOptions.add(textTorneo);
		pnlOptions.add(textTorneo2);


		pnlOptions.add(lblPoblacion);
		pnlOptions.add(textPoblacion);
		pnlOptions.add(textPoblacion2);

		pnlOptions.add(lblGeneraciones);
		pnlOptions.add(textIteraciones);
		pnlOptions.add(textIteraciones2);

		pnlOptions.add(lblCruce);
		pnlOptions.add(textProCruces);
		pnlOptions.add(textProCruces2);

		pnlOptions.add(lblMutacion);
		pnlOptions.add(textProMutacion);
		pnlOptions.add(textProMutacion2);

		//pnlOptions.add(lblSemilla);
		//pnlOptions.add(textSemilla);

		pnlOptions.add(lblTipoCruce);
		pnlOptions.add(comboCruce);
		pnlOptions.add(todosCruces);

		pnlOptions.add(lblSeleccion);
		pnlOptions.add(comboSeleccion);
		pnlOptions.add(todasSelecciones);

		pnlOptions.add(lblMutacionSelect);
		pnlOptions.add(comboMutacion);
		pnlOptions.add(todasMutaciones);

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

		// A�ADIMOS TODO
		pnlButtons.add(buttonCopia);
		this.add(pnlButtons, BorderLayout.SOUTH);
		this.revalidate();
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public JPanel invocarFuncion() {
		int tSeleccion = Integer.valueOf(this.comboSeleccion.getSelectedIndex());
		boolean totSeleccion=this.todasSelecciones.isSelected();
		int tMutacion = Integer.valueOf(this.comboMutacion.getSelectedIndex());
		boolean totMutacion=this.todasMutaciones.isSelected();
		int tCruce = Integer.valueOf(this.comboCruce.getSelectedIndex());
		boolean totCruce=this.todosCruces.isSelected();
		int num_iter = Integer.valueOf(this.textIteraciones.getText());
		int num_iter2 = Integer.valueOf(this.textIteraciones2.getText());
		int tam_pob = Integer.valueOf(this.textPoblacion.getText());
		int tam_pob2 = Integer.valueOf(this.textPoblacion2.getText());
		double pCruces = Double.valueOf(this.textProCruces.getText());
		double pCruces2 = Double.valueOf(this.textProCruces2.getText());
		double pMut = Double.valueOf(this.textProMutacion.getText());
		double pMut2 = Double.valueOf(this.textProMutacion2.getText());
		//double semilla = Double.valueOf(this.textSemilla.getText());
		boolean elitismo = this.checkElitismo.isSelected();
		int torneo = Integer.valueOf(this.textTorneo.getText());
		int torneo2 = Integer.valueOf(this.textTorneo2.getText());
		List<String> listaMejores=new ArrayList();
		List<List<Object>> resultados = null;
		List<List<List<Object>>> listaResultados=new ArrayList();		
		List<List> structResultados= Collections.synchronizedList(new ArrayList());
		structResultados.add(listaMejores);
		structResultados.add(listaResultados);
		
		// EJECUTAMOS FUNCION
		
		Funcion1 unaFuncion1 = new Funcion1(torneo, tam_pob, num_iter, pCruces, pMut, tCruce, tSeleccion, tMutacion, elitismo);
		elMejor = unaFuncion1.getMejorEntreMejores();
		resultados = unaFuncion1.dameResultados();
		
		

		
		
		//Pruebo todas las combinaciones indicadas y saco el mejor resultado
		//creo pool de threads
		/*int cores = Runtime.getRuntime().availableProcessors();
		ExecutorService executorService = Executors.newFixedThreadPool(cores+1);
		
		Funcion1 unaFuncion1=null;
		elMejor=null;
		resultados=null;
		double incremento=0.05;
		for (int t=torneo;t<=torneo2;t++)
		{
			for (int p=tam_pob; p<=tam_pob2;p++)
			{
				for (int g=num_iter;g<=num_iter2;g++)
				{
					for (double c=pCruces;c<=pCruces2;c+=incremento)
					{
						for (double m=pMut;m<=pMut2;m+=incremento)
						{
							 for (TipoCruce tC : TipoCruce.values()) 
							 {		
								 if (!totCruce)  //¿he hecho tick en check de seleccion
									 tC= TipoCruce.values()[tCruce]; //si no, elijo solo un elemento
								 for (TipoSeleccion tS: TipoSeleccion.values())
								 {
									 if (!totSeleccion) 
										 tS= TipoSeleccion.values()[tSeleccion];
									 for (TipoMutacion tM: TipoMutacion.values())
									 {
										 if (!totMutacion) 
											 tM= TipoMutacion.values()[tMutacion];
										 
										
										 rangoWorker unWorker = new rangoWorker(structResultados,t, p, g, c, m, tC.toInt(), tS.toInt(), tM.toInt(), elitismo);
										 executorService.execute(unWorker);								
										 
										 
										 
										 if(!totMutacion) break; 
									 }
									 if (!totSeleccion) break;
								 }
								 if (!totCruce) break; //si no he hecho tick, hago sólo una vuelta de bucle
							 }
							
						}
					}
				}
			}
		}
		executorService.shutdown(); //doy señal de cierre al  pool de threads
		try { //espero 24h a que se cierre el pool (un poco exagerado)
			while (!executorService.awaitTermination(24L, TimeUnit.HOURS)) {
			    System.out.println("El pool aún no ha acabado");
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		//de todos los resultados, cojo el mejor adaptado
		int contMejor=0;
		int cont=0;
		elMejor=(String)structResultados.get(0).get(contMejor);
		Iterator<String> unIterador=structResultados.get(0).iterator();		
		while (unIterador.hasNext())
		{
			String unChurro=unIterador.next();
			String valorLetras=unChurro.split("\n")[2];
			if (Double.parseDouble(valorLetras)>Double.parseDouble(elMejor.split("\n")[2]))
			{
				elMejor=unChurro;
				contMejor=cont;
			}
			cont++;
			
		}
		
		resultados=(List<List<Object>>)structResultados.get(1).get(contMejor);
		*/
		
		
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
		//posicion 1 es la matriz de caminos
		String[] unChurro=elMejor.split("\n");
		StringBuffer unBuffer=new StringBuffer();
		unBuffer.append(unChurro[0]); //String de 'mejor jugador
		for (int i=2;i<unChurro.length;i++)
		{
			unBuffer.append(unChurro[i]).append("\n");
		}		
		return unBuffer.toString();
	}
	
	public String dameTrazado(){
		String[] unChurro=elMejor.split("\n");
		return unChurro[1];
		
	}

	
}