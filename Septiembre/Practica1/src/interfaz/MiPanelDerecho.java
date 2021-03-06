package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

		// A�ADIMOS TODO
		pnlGraphics.add(tabbedPane);

	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public void addNewGrafic() {
		JComponent panel = crearNewPanel();
		tabbedPane.addTab("Tab "+count, null, panel);
		tabbedPane.setMnemonicAt(count, KeyEvent.VK_2);
		tabbedPane.setSelectedIndex(count);
		count++;
	}

	// =========================
	// == METODOS PRIVADOS
	// =========================

	private JComponent crearNewPanel() {
		//CREAMOS EL PANEL
		JPanel panelNew = new JPanel();
		panelNew.setLayout(new FlowLayout());
		panelNew.setPreferredSize(new Dimension (600, 720));
		panelNew.add(pnlIzquierdo.invocarFuncion(),BorderLayout.NORTH);
		//CREAMOS LA CAJA TEXT
		JTextArea textMejorIndividuo = new JTextArea();
		JScrollPane scrollV=new JScrollPane(textMejorIndividuo);
		String text = "****** PROBLEMA DEL VIAJANTE ******"+ System.getProperty( "line.separator" );
		text+= pnlIzquierdo.getMejorEntrePeores();
		textMejorIndividuo.setText(text);

		scrollV.setPreferredSize(new Dimension (500, 150));
		textMejorIndividuo.setPreferredSize(new Dimension (500, 500));
		panelNew.add(scrollV,BorderLayout.SOUTH);

        return panelNew;
    }

}
