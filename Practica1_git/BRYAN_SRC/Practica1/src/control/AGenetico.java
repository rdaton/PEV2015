package control;

import java.util.ArrayList;
import java.util.List;

import individuo.Individuo;
import individuo.Poblacion;

public class AGenetico {

	private List <individuo.Individuo> mejores_cada_generacion;
	private List <individuo.Individuo> peores_cada_generacion;

	private int tam_pob;
	private double prec;
	private Object x_min;
	private Object x_max;

	@SuppressWarnings("unused")
	private double prob_cruce;
	@SuppressWarnings("unused")
	private double prob_mut;
	@SuppressWarnings("unused")
	private int num_max_gen;
	@SuppressWarnings("unused")
	private int tSeleccion;
	@SuppressWarnings("unused")
	private int tCruce;
	@SuppressWarnings("unused")
	private int pos_mejor;
	@SuppressWarnings("unused")
	private double sumadaptacion_bruta;

	private Poblacion pob;

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	/*
	 * tipoAlgoritmo:
	 * 0: Minimización
	 * 1: Maximización
	 */
	public AGenetico (int tipoAlgoritmo, individuo.Individuo prototipo, int tam_pob, double prec, double prob_cruce, double prob_mut, Object x_min, Object x_max, int num_max_gen, boolean elitismo, int tSeleccion, int tCruce) {
		mejores_cada_generacion = new ArrayList<Individuo>();
		peores_cada_generacion = new ArrayList<Individuo>();
		this.tam_pob = tam_pob;
		this.prec = prec;
		this.prob_cruce = prob_cruce;
		this.prob_mut = prob_mut;
		this.x_min = x_min;
		this.x_max = x_max;
		this.num_max_gen = num_max_gen;
		this.tSeleccion = tSeleccion;
		this.tCruce = tCruce;
		this.pob = new individuo.Poblacion(tipoAlgoritmo, prototipo, this.tam_pob, this.x_min, this.x_max, this.prec, elitismo);

		this.pob.evaluacion();
		this.pob.desplazar();

		for (int i=0;i<num_max_gen;i++) {

			if(elitismo){ this.pob.generarElite(); }

			switch (tSeleccion) {
				case 0:
					this.pob.seleccionRuleta();
					break;
				case 1:
					this.pob.seleccionEstadistico();
					break;
				case 2:
					this.pob.seleccionTorneo();
				default:
					this.pob.seleccionRuleta();
			}

			this.pob.reproduccion(prob_cruce, x_min, x_max, tCruce);
			this.pob.mutacion(prob_mut);
			this.pob.evaluacion();
			this.pob.desplazar();

			mejores_cada_generacion.add(pob.dameMejor().clone());
			peores_cada_generacion.add(pob.damePeor().clone());
		}

	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public String getMejorEntreMejores() {
		String elMejor = "";
		double adaptIni = mejores_cada_generacion.get(0).getadaptacion_bruta();
		for (int i = 0; i < mejores_cada_generacion.size(); i++) {
			if(mejores_cada_generacion.get(i).getadaptacion_bruta() > adaptIni || i==0){
				adaptIni = mejores_cada_generacion.get(i).getadaptacion_bruta();
				elMejor = "Mejor Individuo" + System.getProperty( "line.separator" );
				//elMejor+= "Adaptacion: " + mejores_cada_generacion.get(i).getadaptacion_bruta() + System.getProperty( "line.separator" );
				elMejor+=mejores_cada_generacion.get(i); 
				//elMejor+= "Puntuacion: " + mejores_cada_generacion.get(i).getPuntuacion() + System.getProperty( "line.separator" );
				//elMejor+= "Puntuacion Acumulada: " + mejores_cada_generacion.get(i).getPunt_acu();
			}
		}
		return elMejor;
	}

	public String getMejorEntrePeores() {
		String elPeor = "";
		double adaptIni = peores_cada_generacion.get(0).getadaptacion_bruta();
		for (int i = 0; i < peores_cada_generacion.size(); i++) {
			if(peores_cada_generacion.get(i).getadaptacion_bruta() < adaptIni || i==0){
				adaptIni = peores_cada_generacion.get(i).getadaptacion_bruta();
				elPeor = "Mejor Individuo" + System.getProperty( "line.separator" );
				//elPeor+= "Adaptacion: " + peores_cada_generacion.get(i).getadaptacion_bruta() + System.getProperty( "line.separator" );
				elPeor+=peores_cada_generacion.get(i);
				//elPeor+= "Puntuacion: " + peores_cada_generacion.get(i).getPuntuacion() + System.getProperty( "line.separator" );
				//elPeor+= "Puntuacion Acumulada: " + peores_cada_generacion.get(i).getPunt_acu();
			}
		}
		return elPeor;
	}

	// =========================
	// ======== GETTERS
	// =========================

	public List<individuo.Individuo> dameMejor() {
		return mejores_cada_generacion;
	}

	public List<individuo.Individuo> damePeor() {
		return peores_cada_generacion;
	}
}
