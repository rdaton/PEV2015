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

import control.Funcion1;

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
	private JComboBox comboCruce;
	private JComboBox comboSeleccion;
	private JCheckBox checkElitismo;

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
		frame.setBounds(100, 100, 413, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(22, 12, 233, 311);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Función");
		lblNewLabel.setBounds(12, 12, 70, 15);
		panel.add(lblNewLabel);

		JLabel lblCromosomas = new JLabel("Precisión");
		lblCromosomas.setBounds(12, 39, 70, 15);
		panel.add(lblCromosomas);

		JLabel lblPob = new JLabel("Población");
		lblPob.setBounds(12, 66, 70, 15);
		panel.add(lblPob);

		JLabel lblNewLabel_1 = new JLabel("Iteraciones");
		lblNewLabel_1.setBounds(12, 93, 88, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("% Cruces");
		lblNewLabel_2.setBounds(12, 120, 70, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("% Mutación");
		lblNewLabel_3.setBounds(12, 152, 88, 15);
		panel.add(lblNewLabel_3);

		JLabel lblSemilla = new JLabel("Selección");
		lblSemilla.setBounds(12, 210, 70, 15);
		panel.add(lblSemilla);

		comboFuncion = new JComboBox();
		comboFuncion.setModel(new DefaultComboBoxModel(new String[] {
				"Función 1", "Función 2", "Función 3", "Función 4",
				"Función 5", "Función 6" }));
		comboFuncion.setBounds(82, 7, 107, 20);
		panel.add(comboFuncion);

		textIteraciones = new JTextField();
		textIteraciones.setText("100");
		textIteraciones.setBounds(102, 91, 70, 20);
		panel.add(textIteraciones);
		textIteraciones.setColumns(10);

		textPrecision = new JTextField();
		textPrecision.setText("0.1");
		textPrecision.setColumns(10);
		textPrecision.setBounds(100, 37, 70, 20);
		panel.add(textPrecision);

		textPoblacion = new JTextField();
		textPoblacion.setText("100");
		textPoblacion.setColumns(10);
		textPoblacion.setBounds(102, 66, 70, 20);
		panel.add(textPoblacion);

		textPcruces = new JTextField();
		textPcruces.setText("0.6");
		textPcruces.setColumns(10);
		textPcruces.setBounds(100, 118, 70, 20);
		panel.add(textPcruces);

		JLabel lblNewLabel_5 = new JLabel("Cruce");
		lblNewLabel_5.setBounds(12, 183, 70, 15);
		panel.add(lblNewLabel_5);

		textpMutacion = new JTextField();
		textpMutacion.setText("0.06");
		textpMutacion.setColumns(10);
		textpMutacion.setBounds(102, 150, 70, 20);
		panel.add(textpMutacion);

		comboCruce = new JComboBox();
		comboCruce.setModel(new DefaultComboBoxModel(
				new String[] { "Monopunto" }));
		comboCruce.setBounds(96, 178, 107, 20);
		panel.add(comboCruce);

		comboSeleccion = new JComboBox();
		comboSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Estadistico", "Torneo"}));
		comboSeleccion.setBounds(100, 207, 88, 20);
		panel.add(comboSeleccion);

		checkElitismo = new JCheckBox("Elitismo");
		checkElitismo.setBounds(41, 234, 129, 23);
		panel.add(checkElitismo);

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
		double prec=Double.valueOf(this.textPcruces.getText());
		int tam_pob=Integer.valueOf(this.textPoblacion.getText());
		int num_iter=Integer.valueOf(this.textIteraciones.getText());
		double pCruces=Double.valueOf(this.textPcruces.getText());
		double pMut=Double.valueOf(this.textpMutacion.getText());
		//double semilla=Double.valueOf(this.textSemilla.getText());
		int tCruce= Integer.valueOf(this.comboCruce.getSelectedIndex());
		int tSeleccion=Integer.valueOf(this.comboSeleccion.getSelectedIndex());
		
		List<List<Object>> resultados=null;
		//calcular resultados
		switch (selec)
		{
		case 0:
			Funcion1 unaFuncion1=new Funcion1(prec,tam_pob,num_iter,pCruces,pMut,
					tCruce,tSeleccion);
			resultados=unaFuncion1.dameResultados();
			break;		
		}
		 
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
		 
		 
		    // create your PlotPanel (you can use it as a JPanel)
		    Plot2DPanel plot = new Plot2DPanel();
	//	    plot.setFixedBounds(0,1,100);
		   
		    // add a line plot to the PlotPanel

		    plot.addScatterPlot("Mejor Absoluto", x, y);
		    plot.addScatterPlot("Mejor de Generación", x, y2);
		    plot.addScatterPlot("Mejor Media de Generación", x, y3);
		    
		    
		    
		    

		    // put the PlotPanel in a JFrame, as a JPanel
		    JFrame frame = new JFrame("Función 1");
		    frame.setSize(600, 600);
		    frame.setContentPane(plot);
		    frame.setVisible(true);
		    
		    
	}
	
	

}
