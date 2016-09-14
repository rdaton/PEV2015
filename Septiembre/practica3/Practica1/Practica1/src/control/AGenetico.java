package control;

import java.util.ArrayList;
import java.util.List;

import individuo.Individuo;
import individuo.Poblacion;

public class AGenetico {

	private List <individuo.Individuo> mejores_cada_generacion;
	private List <individuo.Individuo> peores_cada_generacion;
	List <Double> medias_generaciones;

	private int tam_pob;
	private int x_min;
	private int x_max;

	private double prob_cruce;
	private double prob_mut;
	@SuppressWarnings("unused")
	private int num_max_gen;
	@SuppressWarnings("unused")
	private int tSeleccion;
	private int tCruce;
	private int tMutacion;
	@SuppressWarnings("unused")
	private int pos_mejor;
	@SuppressWarnings("unused")
	private double sumadaptacion_bruta;

	private Poblacion pob;

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public AGenetico (int torneo, int tipoAlgoritmo, individuo.Individuo prototipo, int tam_pob, double prob_cruce, double prob_mut, int x_min, int x_max, int num_max_gen, boolean elitismo, int tSeleccion, int tCruce, int tMutacion) {

		mejores_cada_generacion = new ArrayList<Individuo>();
		peores_cada_generacion = new ArrayList<Individuo>();
		medias_generaciones = new ArrayList<Double>();
		this.tam_pob = tam_pob;
		this.prob_cruce = prob_cruce;
		this.prob_mut = prob_mut;
		this.x_min = x_min;
		this.x_max = x_max;
		this.num_max_gen = num_max_gen;
		this.tSeleccion = tSeleccion;
		this.tCruce = tCruce;
		this.tMutacion = tMutacion;
		this.pob = new individuo.Poblacion(torneo, tipoAlgoritmo, prototipo, this.tam_pob, this.x_min, this.x_max, elitismo);

		this.pob.evaluacion();
		this.pob.desplazar();

		

		for (int i=0;i<num_max_gen;i++) {

			if(elitismo){ this.pob.generarElite(); }

			switch (tSeleccion) {
				case 0:
					this.pob.seleccionRuleta();
					break;
				case 1:
					this.pob.seleccionTorneo();
					break;
				case 2:
					this.pob.seleccionEstadistico();
					break;
				case 3:
					this.pob.seleccionRanking();
					break;
				case 4:
					this.pob.seleccionRestos();
					break;
				case 5:
					this.pob.seleccionTruncamiento();
					break;
				default:
					this.pob.seleccionRuleta();
			}

			this.pob.reproduccion(this.prob_cruce, this.x_min, this.x_max, this.tCruce);
			this.pob.mutacion(this.prob_mut, this.tMutacion);
			this.pob.evaluacion();
			this.pob.desplazar();

			mejores_cada_generacion.add(pob.dameMejor().clone());
			peores_cada_generacion.add(pob.damePeor().clone());
			medias_generaciones.add(pob.dameMediaAdaptacion());
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
				elMejor+= mejores_cada_generacion.get(i);
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
				elPeor+= peores_cada_generacion.get(i);
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

	public List<Double> dameMedias()
	{
		return medias_generaciones;
	}

}
