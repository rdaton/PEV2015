package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VistaSwing extends JFrame {

	protected MiPanelIzquierdo pnlIzquierdo;
	protected MiPanelDerecho pnlDerecho;

	public VistaSwing () {
		super("Pr�ctica 1 - PEV");

		getContentPane().setLayout(new FlowLayout());
		this.setSize(new Dimension(900, 720));
		this.setMinimumSize(new Dimension(900, 720));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnlIzquierdo = new MiPanelIzquierdo();
		pnlIzquierdo.buttonCopia.setEnabled(true);
		pnlIzquierdo.buttonCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		pnlDerecho = new MiPanelDerecho(pnlIzquierdo);
		pnlIzquierdo.setPnlDerecho(pnlDerecho);

		this.getContentPane().add(pnlIzquierdo,BorderLayout.EAST);
		this.getContentPane().add(pnlDerecho,BorderLayout.WEST);

		this.revalidate();
	}

}
