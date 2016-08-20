package control;

import java.awt.EventQueue;
import interfaz.VistaSwing;

public class Main {

	public static void main(String[] args) {

		final VistaSwing vistaSwing = new VistaSwing();

		EventQueue.invokeLater(new Runnable() {
			public void run() { vistaSwing.setVisible(true); }
		});

	}

}
