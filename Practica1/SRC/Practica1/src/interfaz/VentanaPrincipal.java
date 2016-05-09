package interfaz;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.Line;

import control.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaPrincipal {

	private JFrame frame;
	private JTextField textIteraciones;
	private JTextField textPrecision;
	private JTextField textPoblacion;
	private JTextField textPcruces;
	private JTextField textpMutacion;
	private JComboBox comboFuncion;
	private JComboBox comboSeleccion;
	private JCheckBox checkElitismo;
	private JTextField textArgF;
	public JTextField textArgF2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 413, 323);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(22, 12, 233, 287);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Función");
		lblNewLabel.setBounds(12, 12, 70, 15);
		panel.add(lblNewLabel);

		JLabel lblCromosomas = new JLabel("Precisión");
		lblCromosomas.setBounds(12, 101, 70, 15);
		panel.add(lblCromosomas);

		JLabel lblPob = new JLabel("Población");
		lblPob.setBounds(12, 128, 70, 15);
		panel.add(lblPob);

		JLabel lblNewLabel_1 = new JLabel("Iteraciones");
		lblNewLabel_1.setBounds(12, 155, 88, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("% Cruces");
		lblNewLabel_2.setBounds(12, 182, 70, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("% Mutación");
		lblNewLabel_3.setBounds(12, 214, 88, 15);
		panel.add(lblNewLabel_3);

		JLabel lblSemilla = new JLabel("Selección");
		lblSemilla.setBounds(12, 241, 70, 15);
		panel.add(lblSemilla);

		comboFuncion = new JComboBox();
		comboFuncion.setModel(new DefaultComboBoxModel(new String[] {
				"Función 1", "Función 2", "Función 3", "Función 4",
				"Función 5"}));
		comboFuncion.setBounds(82, 7, 107, 20);
		panel.add(comboFuncion);

		textIteraciones = new JTextField();
		textIteraciones.setText("100");
		textIteraciones.setBounds(102, 153, 70, 20);
		panel.add(textIteraciones);
		textIteraciones.setColumns(10);

		textPrecision = new JTextField();
		textPrecision.setText("0.01");
		textPrecision.setColumns(10);
		textPrecision.setBounds(100, 99, 70, 20);
		panel.add(textPrecision);

		textPoblacion = new JTextField();
		textPoblacion.setText("100");
		textPoblacion.setColumns(10);
		textPoblacion.setBounds(102, 128, 70, 20);
		panel.add(textPoblacion);

		textPcruces = new JTextField();
		textPcruces.setText("0.6");
		textPcruces.setColumns(10);
		textPcruces.setBounds(100, 180, 70, 20);
		panel.add(textPcruces);

		textpMutacion = new JTextField();
		textpMutacion.setText("0.06");
		textpMutacion.setColumns(10);
		textpMutacion.setBounds(102, 212, 70, 20);
		panel.add(textpMutacion);

		comboSeleccion = new JComboBox();
		comboSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Estadistico", "Torneo"}));
		comboSeleccion.setBounds(101, 238, 88, 20);
		panel.add(comboSeleccion);

		checkElitismo = new JCheckBox("Elitismo");
		checkElitismo.setBounds(22, 264, 129, 23);
		panel.add(checkElitismo);
		
		textArgF = new JTextField();
		textArgF.setText("3");
		textArgF.setBounds(102, 68, 70, 19);
		panel.add(textArgF);
		textArgF.setColumns(10);
		
		JLabel lblArg = new JLabel("nTorneo");
		lblArg.setBounds(12, 74, 70, 15);
		panel.add(lblArg);
		
		JLabel lblArg_1 = new JLabel("Arg #");
		lblArg_1.setBounds(12, 47, 70, 15);
		panel.add(lblArg_1);
		
		textArgF2 = new JTextField();
		textArgF2.setText("4");
		textArgF2.setBounds(102, 39, 70, 17);
		panel.add(textArgF2);
		textArgF2.setColumns(10);

		JButton btnNewButton = new JButton("Ejecutar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal.this.invocarFuncion();
			}
		});
		btnNewButton.setBounds(244, 37, 117, 25);
		frame.getContentPane().add(btnNewButton);
	}

	void invocarFuncion() {
		int selec=Integer.valueOf(this.comboFuncion.getSelectedIndex());
		int argf1=Integer.valueOf(this.textArgF.getText());
		int argf2=Integer.valueOf(this.textArgF2.getText());
		double prec=Double.valueOf(this.textPrecision.getText());
		int tam_pob=Integer.valueOf(this.textPoblacion.getText());
		int num_iter=Integer.valueOf(this.textIteraciones.getText());
		double pCruces=Double.valueOf(this.textPcruces.getText());
		double pMut=Double.valueOf(this.textpMutacion.getText());
		//double semilla=Double.valueOf(this.textSemilla.getText());
		int tCruce= 0;
		int tSeleccion=Integer.valueOf(this.comboSeleccion.getSelectedIndex());
		boolean elitismo=this.checkElitismo.isSelected();		
		List<List<Object>> resultados=null;
			
		//calcular resultados
		switch (selec)
		{
		case 0:
		{
			Funcion1 unaFuncion1=new Funcion1(argf1,argf2,prec,tam_pob,num_iter,pCruces,pMut,
					tCruce,tSeleccion,elitismo);
			resultados=unaFuncion1.dameResultados();
					
		
		 
		if (resultados==null)
				return;
			
		//pintar gráfica.
	
		 double[] x = new double [resultados.get(0).size()];
		 for (int i=0;i<resultados.get(0).size();i++)
		 {
			 x[i]=i;
		 }
		 
		 double[] y = new double[resultados.get(0).size()];
		 for (int i=0;i<resultados.get(0).size();i++)
		 {
			 y[i]=(Double) resultados.get(0).get(i);
		 }
		 
		 double[] y2 = new double[resultados.get(1).size()];
		 for (int i=0;i<resultados.get(1).size();i++)
		 {
			 y2[i]=(Double) resultados.get(1).get(i);
		 }
		 
		 double[] y3 = new double[resultados.get(2).size()];
		 for (int i=0;i<resultados.get(2).size();i++)
		 {
			 y3[i]=(Double) resultados.get(2).get(i);
		 }
		 
		 //limpio resultados para siguiente invocación
		 resultados=null;
		 
		    // create your PlotPanel (you can use it as a JPanel)
		    Plot2DPanel plot = new Plot2DPanel();
	//	    plot.setFixedBounds(0,1,100);
		   
		    plot.setAxisLabels("Generación", "Adaptación");
		    plot.addLegend("SOUTH");
		    
            
		    
		    // add a line plot to the PlotPanel
		    
		    plot.addLinePlot("Mejor Absoluto",Color.BLUE, x, y);		   
		    plot.addLinePlot("Mejor de Generación",Color.RED, x, y2);
		    plot.addLinePlot("Media de Generación",Color.GREEN, x, y3);
		    
		    
		    
		    

		    // put the PlotPanel in a JFrame, as a JPanel
		    JFrame frame = new JFrame("Función 1");
		    frame.setSize(600, 600);
		    frame.setContentPane(plot);
		    frame.setVisible(true);
		}
		    break;
		    
		    
		case 1:
		{
		Funcion2 unaFuncion2=new Funcion2(argf1,argf2,prec,tam_pob,num_iter,pCruces,pMut,
				tCruce,tSeleccion,elitismo);
		resultados=unaFuncion2.dameResultados();
					
		
		 
		if (resultados==null)
				return;
			
		//pintar gráfica.
	
		 double[] x = new double [resultados.get(0).size()];
		 for (int i=0;i<resultados.get(0).size();i++)
		 {
			 x[i]=i;
		 }
		 
		 double[] y = new double[resultados.get(0).size()];
		 for (int i=0;i<resultados.get(0).size();i++)
		 {
			 y[i]=(Double) resultados.get(0).get(i);
		 }
		 
		 double[] y2 = new double[resultados.get(1).size()];
		 for (int i=0;i<resultados.get(1).size();i++)
		 {
			 y2[i]=(Double) resultados.get(1).get(i);
		 }
		 
		 double[] y3 = new double[resultados.get(2).size()];
		 for (int i=0;i<resultados.get(2).size();i++)
		 {
			 y3[i]=(Double) resultados.get(2).get(i);
		 }
		 
		 //limpio resultados para siguiente invocación
		 resultados=null;
		 
		    // create your PlotPanel (you can use it as a JPanel)
		    Plot2DPanel plot = new Plot2DPanel();
	//	    plot.setFixedBounds(0,1,100);
		   
		    plot.setAxisLabels("Generación", "Adaptación");
		    plot.addLegend("SOUTH");
		    
            
		    
		    // add a line plot to the PlotPanel
		    
		    plot.addLinePlot("Mejor Absoluto",Color.BLUE, x, y);		   
		    plot.addLinePlot("Mejor de Generación",Color.RED, x, y2);
		    plot.addLinePlot("Media de Generación",Color.GREEN, x, y3);
		    
		    
		    
		    

		    // put the PlotPanel in a JFrame, as a JPanel
		    JFrame frame = new JFrame("Función 2");
		    frame.setSize(600, 600);
		    frame.setContentPane(plot);
		    frame.setVisible(true);
		}
		    break;
		case 2:
		{
		Funcion3 unaFuncion3=new Funcion3(argf1,argf2,prec,tam_pob,num_iter,pCruces,pMut,
				tCruce,tSeleccion,elitismo);
		resultados=unaFuncion3.dameResultados();
					
		
		 
		if (resultados==null)
				return;
			
		//pintar gráfica.
	
		 double[] x = new double [resultados.get(0).size()];
		 for (int i=0;i<resultados.get(0).size();i++)
		 {
			 x[i]=i;
		 }
		 
		 double[] y = new double[resultados.get(0).size()];
		 for (int i=0;i<resultados.get(0).size();i++)
		 {
			 y[i]=(Double) resultados.get(0).get(i);
		 }
		 
		 double[] y2 = new double[resultados.get(1).size()];
		 for (int i=0;i<resultados.get(1).size();i++)
		 {
			 y2[i]=(Double) resultados.get(1).get(i);
		 }
		 
		 double[] y3 = new double[resultados.get(2).size()];
		 for (int i=0;i<resultados.get(2).size();i++)
		 {
			 y3[i]=(Double) resultados.get(2).get(i);
		 }
		 
		 //limpio resultados para siguiente invocación
		 resultados=null;
		 
		    // create your PlotPanel (you can use it as a JPanel)
		    Plot2DPanel plot = new Plot2DPanel();
	//	    plot.setFixedBounds(0,1,100);
		   
		    plot.setAxisLabels("Generación", "Adaptación");
		    plot.addLegend("SOUTH");
		    
            
		    
		    // add a line plot to the PlotPanel
		    
		    plot.addLinePlot("Mejor Absoluto",Color.BLUE, x, y);		   
		    plot.addLinePlot("Mejor de Generación",Color.RED, x, y2);
		    plot.addLinePlot("Media de Generación",Color.GREEN, x, y3);
		    
		    
		    
		    

		    // put the PlotPanel in a JFrame, as a JPanel
		    JFrame frame = new JFrame("Función 3");
		    frame.setSize(600, 600);
		    frame.setContentPane(plot);
		    frame.setVisible(true);
		}
		    break;
		case 3:
		{
		Funcion4 unaFuncion3=new Funcion4(argf1,argf2,prec,tam_pob,num_iter,pCruces,pMut,
				tCruce,tSeleccion,elitismo);
		resultados=unaFuncion3.dameResultados();
					
		
		 
		if (resultados==null)
				return;
			
		//pintar gráfica.
	
		 double[] x = new double [resultados.get(0).size()];
		 for (int i=0;i<resultados.get(0).size();i++)
		 {
			 x[i]=i;
		 }
		 
		 double[] y = new double[resultados.get(0).size()];
		 for (int i=0;i<resultados.get(0).size();i++)
		 {
			 y[i]=(Double) resultados.get(0).get(i);
		 }
		 
		 double[] y2 = new double[resultados.get(1).size()];
		 for (int i=0;i<resultados.get(1).size();i++)
		 {
			 y2[i]=(Double) resultados.get(1).get(i);
		 }
		 
		 double[] y3 = new double[resultados.get(2).size()];
		 for (int i=0;i<resultados.get(2).size();i++)
		 {
			 y3[i]=(Double) resultados.get(2).get(i);
		 }
		 
		 //limpio resultados para siguiente invocación
		 resultados=null;
		 
		    // create your PlotPanel (you can use it as a JPanel)
		    Plot2DPanel plot = new Plot2DPanel();
	//	    plot.setFixedBounds(0,1,100);
		   
		    plot.setAxisLabels("Generación", "Adaptación");
		    plot.addLegend("SOUTH");
		    
            
		    
		    // add a line plot to the PlotPanel
		    
		    plot.addLinePlot("Peor Absoluto",Color.BLUE, x, y);		   
		    plot.addLinePlot("Peor de Generación",Color.RED, x, y2);
		    plot.addLinePlot("Media de Generación",Color.GREEN, x, y3);
		    
		    
		    
		    

		    // put the PlotPanel in a JFrame, as a JPanel
		    JFrame frame = new JFrame("Función 4");
		    frame.setSize(600, 600);
		    frame.setContentPane(plot);
		    frame.setVisible(true);
		}
		    break;
		case 4:
		{
		Funcion5 unaFuncion5=new Funcion5(argf1,argf2,prec,tam_pob,num_iter,pCruces,pMut,
				tCruce,tSeleccion,elitismo);
		resultados=unaFuncion5.dameResultados();
					
		
		 
		if (resultados==null)
				return;
			
		//pintar gráfica.
	
		 double[] x = new double [resultados.get(0).size()];
		 for (int i=0;i<resultados.get(0).size();i++)
		 {
			 x[i]=i;
		 }
		 
		 double[] y = new double[resultados.get(0).size()];
		 for (int i=0;i<resultados.get(0).size();i++)
		 {
			 y[i]=(Double) resultados.get(0).get(i);
		 }
		 
		 double[] y2 = new double[resultados.get(1).size()];
		 for (int i=0;i<resultados.get(1).size();i++)
		 {
			 y2[i]=(Double) resultados.get(1).get(i);
		 }
		 
		 double[] y3 = new double[resultados.get(2).size()];
		 for (int i=0;i<resultados.get(2).size();i++)
		 {
			 y3[i]=(Double) resultados.get(2).get(i);
		 }
		 
		 //limpio resultados para siguiente invocación
		 resultados=null;
		 
		    // create your PlotPanel (you can use it as a JPanel)
		    Plot2DPanel plot = new Plot2DPanel();
	//	    plot.setFixedBounds(0,1,100);
		   
		    plot.setAxisLabels("Generación", "Adaptación");
		    plot.addLegend("SOUTH");
		    
            
		    
		    // add a line plot to the PlotPanel
		    
		    plot.addLinePlot("Peor Absoluto",Color.BLUE, x, y);		   
		    plot.addLinePlot("Peor de Generación",Color.RED, x, y2);
		    plot.addLinePlot("Media de Generación",Color.GREEN, x, y3);
		    
		    
		    
		    

		    // put the PlotPanel in a JFrame, as a JPanel
		    JFrame frame = new JFrame("Función 4");
		    frame.setSize(600, 600);
		    frame.setContentPane(plot);
		    frame.setVisible(true);
		}
		    break;
		    
		}
		    
		    
		
		    
	}
}
