package control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Funcion5 {

	private double prec = 0;
	private int tam_pob = 0;
	private int num_iter = 0;
	private double pCruces = 0;
	private double pMut = 0;
	private int tCruce = 0;
	private int tSeleccion = 0;
	private final Double[] x_min = {Double.valueOf(-10)};
	private final Double[] x_max = {Double.valueOf(10)};
	private AGenetico unAlgoritmo;

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public Funcion5 (double prec, int tam_pob, int num_iter, double pCruces, double pMut, int tCruce, int tSeleccion, boolean elitismo) {
		this.prec = prec;
		this.tam_pob = tam_pob;
		this.num_iter = num_iter;
		this.pCruces = pCruces;
		this.pMut = pMut;
		this.tCruce = tCruce;
		this.tSeleccion = tSeleccion;
		this.unAlgoritmo = new AGenetico
			(0, new individuo.Individuo_f5(), this.tam_pob, this.prec, this.pCruces, this.pMut, this.x_min, this.x_max, this.num_iter, elitismo, this.tSeleccion, this.tCruce);
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

		while(unIterador.hasNext()) {
			vueltas++;
			pIndividuo = unIterador.next();
			pDouble = pIndividuo.getadaptacion_bruta();

			if(vueltas==1) {
				min_adapt = pDouble;
			} else if(pDouble<min_adapt) {
				min_adapt = pDouble;
			}

			resultados.get(0).add(min_adapt);
			resultados.get(1).add(new Double(pDouble));
			resultados.get(2).add(dameMedia(resultados.get(1)));
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
