package control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Funcion1 {

	private int tam_pob = 0;
	private int num_iter = 0;
	private double pCruces = 0;
	private double pMut = 0;
	private int tCruce = 0;
	private int tSeleccion = 0;
	private int tMutacion = 0;
	private final int x_min = 0;
	private final int x_max = 9;
	private AGenetico unAlgoritmo;

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public Funcion1(int torneo, int tam_pob, int num_iter, double pCruces, double pMut, int tCruce, int tSeleccion, int tMutacion, boolean elitismo) {
		this.tam_pob = tam_pob;
		this.num_iter = num_iter;
		this.pCruces = pCruces;
		this.pMut = pMut;
		this.tCruce = tCruce;
		this.tSeleccion = tSeleccion;
		this.tMutacion = tMutacion;
		this.unAlgoritmo = new AGenetico
			 (torneo, 0, new individuo.Individuo_basico(), this.tam_pob, this.pCruces, this.pMut, this.x_min, this.x_max, this.num_iter, elitismo, this.tSeleccion, this.tCruce, this.tMutacion);
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public List<List<Object>> dameResultados() {
		Iterator<individuo.Individuo> unIterador = null;
		List<List<Object>> resultados = new ArrayList<List<Object>>();
		List<individuo.Individuo> resultadosPeores = unAlgoritmo.damePeor();

		resultados.add (new ArrayList<Object>()); //MENOR ABSOLUTO
		resultados.add (new ArrayList<Object>()); //MENOR DE GENERACION
		resultados.add (new ArrayList<Object>()); //MEDIA DE GENERACION

		individuo.Individuo pIndividuo = null;

		unIterador = resultadosPeores.iterator();
		double min_adapt = 0;
		int vueltas = 0;
		double pDouble = 0;
		//double x;
		while(unIterador.hasNext()) {
			vueltas++;
			pIndividuo = unIterador.next();
			pDouble = pIndividuo.getadaptacion_bruta();
			//x=(Double)pIndividuo.getX();
			if(vueltas == 1) {
				min_adapt = pDouble;
			} else if(pDouble < min_adapt) {
				min_adapt = pDouble;
			}

			resultados.get(0).add(min_adapt);
			resultados.get(1).add(new Double(pDouble));
			resultados.get(2).add(unAlgoritmo.dameMedias().get(vueltas-1));
			/*
			System.out.print("x: " +String.format( "%.2f",  x));
			System.out.print(" ; y: "+ String.format( "%.2f", pDouble));
			System.out.println(" ; peor "+String.format( "%.2f", min_adapt));*/
		}

		return resultados;
	}

	public double dameMedia(List<Object> unaLista) {
		double unaMedia = 0;
		double total = 0;

		Iterator<Object> unIterador = unaLista.iterator();
		while (unIterador.hasNext()) {
			total+= (Double)unIterador.next();
		}

		unaMedia = (double)total/(double) unaLista.size();
		return unaMedia;
	}

	// =========================
	// ======== GETTERS
	// =========================

	public String getMejorEntreMejores() {
		return this.unAlgoritmo.getMejorEntreMejores();
	}

	public String getMejorEntrePeores() {
		return this.unAlgoritmo.getMejorEntrePeores();
	}

}
