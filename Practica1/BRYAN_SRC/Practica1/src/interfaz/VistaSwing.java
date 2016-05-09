package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class VistaSwing extends JFrame {

	private MiPanelIzquierdo pnlIzquierdo;
	private MiPanelDerecho pnlDerecho;

	public VistaSwing () {
		super("Práctica 1 - PEV");

		this.setLayout(new FlowLayout());
		this.setSize(new Dimension(900, 720));
		this.setMinimumSize(new Dimension(900, 720));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnlIzquierdo = new MiPanelIzquierdo();
		pnlDerecho = new MiPanelDerecho(pnlIzquierdo);
		pnlIzquierdo.setPnlDerecho(pnlDerecho);

		this.getContentPane().add(pnlIzquierdo,BorderLayout.EAST);
		this.getContentPane().add(pnlDerecho,BorderLayout.WEST);

		this.revalidate();
	}

}
