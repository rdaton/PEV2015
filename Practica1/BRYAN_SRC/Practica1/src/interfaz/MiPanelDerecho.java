package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MiPanelDerecho extends JPanel {

	private MiPanelIzquierdo pnlIzquierdo;
	private JTabbedPane tabbedPane;
	private int count = 0;

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public MiPanelDerecho(MiPanelIzquierdo pnlIzquierdo) {
		this.pnlIzquierdo = pnlIzquierdo;

		// ===========================
		// ------ PANEL DERECHO ------
		// ===========================

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600, 720));

		// ****** GRAFICAS ******
		JPanel pnlGraphics = new JPanel();
		pnlGraphics.setBackground(Color.blue);

		this.add(pnlGraphics, BorderLayout.CENTER);

		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(600,670));

		JComponent panelInicial = crearPanel();
		panelInicial.setBackground(Color.green);
		count++;
		tabbedPane.addTab("Tab "+count, null, panelInicial);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		// AÑADIMOS TODO
		pnlGraphics.add(tabbedPane);

	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public void addNewGrafic() {
		JComponent panel = crearNewPanel();
		count++;
		tabbedPane.addTab("Tab "+count, null, panel);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	}

	// =========================
	// == METODOS PRIVADOS
	// =========================

	private JComponent crearPanel() {
		final JPanel panel = new JPanel();
        final JButton buttonIniciar = new JButton("Lanzar un nuevo AG con las opciones actuales");
		buttonIniciar.setSize(new Dimension (10, 60));
		buttonIniciar.setEnabled(true);
		buttonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlIzquierdo.buttonCopia.setEnabled(true);
				buttonIniciar.setVisible(false);
				//CREAMOS EL PANEL
				JPanel panelNew = new JPanel();
				panelNew.setLayout(new FlowLayout());
				panelNew.setPreferredSize(new Dimension (600, 720));
				panelNew.add(pnlIzquierdo.invocarFuncion(),BorderLayout.NORTH);
				//CREAMOS LA CAJA TEXT
				JTextArea textMejorIndividuo = new JTextArea();
				if(pnlIzquierdo.comboFuncion.getSelectedIndex() == 0) {
					String text = "****** FUNCION 1 ******"+ System.getProperty( "line.separator" );
					text+= pnlIzquierdo.getMejorEntrePeores();
					textMejorIndividuo.setText(text);
				}
				if(pnlIzquierdo.comboFuncion.getSelectedIndex() == 1) {
					String text = "****** FUNCION 2 ******"+ System.getProperty( "line.separator" );
					text+= pnlIzquierdo.getMejorEntreMejores();
					textMejorIndividuo.setText(text);
				}
				if(pnlIzquierdo.comboFuncion.getSelectedIndex() == 2) {
					String text = "****** FUNCION 3 ******"+ System.getProperty( "line.separator" );
					text+= pnlIzquierdo.getMejorEntreMejores();
					textMejorIndividuo.setText(text);
				}
				if(pnlIzquierdo.comboFuncion.getSelectedIndex() == 3) {
					String text = "****** FUNCION 4 ******"+ System.getProperty( "line.separator" );
					text+= pnlIzquierdo.getMejorEntrePeores();
					textMejorIndividuo.setText(text);
				}
				if(pnlIzquierdo.comboFuncion.getSelectedIndex() == 4) {
					String text = "****** FUNCION 5 ******"+ System.getProperty( "line.separator" );
					text+= pnlIzquierdo.getMejorEntreMejores();
					textMejorIndividuo.setText(text);
				}
				textMejorIndividuo.setPreferredSize(new Dimension (500, 150));
				panelNew.add(textMejorIndividuo,BorderLayout.SOUTH);

				panel.add(panelNew);
			}
		});

		panel.add(buttonIniciar);
        return panel;
    }

	private JComponent crearNewPanel() {
		//CREAMOS EL PANEL
		JPanel panelNew = new JPanel();
		panelNew.setLayout(new FlowLayout());
		panelNew.setPreferredSize(new Dimension (600, 720));
		panelNew.add(pnlIzquierdo.invocarFuncion(),BorderLayout.NORTH);
		//CREAMOS LA CAJA TEXT
		JTextArea textMejorIndividuo = new JTextArea();
		if(pnlIzquierdo.comboFuncion.getSelectedIndex() == 0) {
			String text = "****** FUNCION 1 ******"+ System.getProperty( "line.separator" );
			text+= pnlIzquierdo.getMejorEntrePeores();
			textMejorIndividuo.setText(text);
		}
		if(pnlIzquierdo.comboFuncion.getSelectedIndex() == 1) {
			String text = "****** FUNCION 2 ******"+ System.getProperty( "line.separator" );
			text+= pnlIzquierdo.getMejorEntreMejores();
			textMejorIndividuo.setText(text);
		}
		if(pnlIzquierdo.comboFuncion.getSelectedIndex() == 2) {
			String text = "****** FUNCION 3 ******"+ System.getProperty( "line.separator" );
			text+= pnlIzquierdo.getMejorEntreMejores();
			textMejorIndividuo.setText(text);
		}
		if(pnlIzquierdo.comboFuncion.getSelectedIndex() == 3) {
			String text = "****** FUNCION 4 ******"+ System.getProperty( "line.separator" );
			text+= pnlIzquierdo.getMejorEntrePeores();
			textMejorIndividuo.setText(text);
		}
		if(pnlIzquierdo.comboFuncion.getSelectedIndex() == 4) {
			String text = "****** FUNCION 5 ******"+ System.getProperty( "line.separator" );
			text+= pnlIzquierdo.getMejorEntreMejores();
			textMejorIndividuo.setText(text);
		}
		textMejorIndividuo.setPreferredSize(new Dimension (500, 150));
		panelNew.add(textMejorIndividuo,BorderLayout.SOUTH);

        return panelNew;
    }

}
