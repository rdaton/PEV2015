package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Poblacion {

	private List<Individuo> individuos;
	private int pos_mejor;
	private int pos_peor;
	private double sumadap;
	private double prec;
	private int torneo;
	private int tam_elite;
	private boolean[] elite;
	private int[] sel_elite;
	private boolean elitismo;
	private int tipoAlgoritmo; //0: MINIMIZACION, 1: MAXIMIZACION

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public Poblacion(int argf1, int argf2,int tipoAlgoritmo, Individuo prototipo, int tam_pob, Object x_min, Object x_max, double prec, boolean elitismo) {
		this.tipoAlgoritmo = tipoAlgoritmo;
		init();
		this.prec = prec;
		Individuo unIndividuo = null;

		for(int i=0;i<tam_pob;i++) {
			unIndividuo = prototipo.newInstance(x_min, x_max, prec);
			individuos.add(unIndividuo);
		}

		this.torneo = argf1;
		this.elitismo = elitismo;
		this.tam_elite = this.size()*2/100;
		this.elite = new boolean[this.size()];

		for(int i=0;i<this.size();i++) {
			elite[i] = false;
		};
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public Individuo dameMejor() {
		return individuos.get(pos_mejor);
	}

	public Individuo damePeor() {
		return individuos.get(pos_peor);
	}

	public void evaluacion() {
		sumadap = 0; 			//SUMA DE LA ADAPTACION
		double punt_acu = 0;	//PUNTUACION ACUMULADA DE LOS INDIVIDUOS
		Individuo pIndividuo = null;

		Iterator<Individuo> unIterador = individuos.iterator();

		int i = 0;
		while(unIterador.hasNext()) {
			pIndividuo = unIterador.next();
			sumadap+= pIndividuo.getadaptacion();
			if((i==0) || pIndividuo.getadaptacion_bruta()>individuos.get(pos_mejor).getadaptacion_bruta()) {
				pos_mejor=i;
			}
			if((i==0) || pIndividuo.getadaptacion_bruta()<individuos.get(pos_peor).getadaptacion_bruta()) {
				pos_peor=i;
			}
			i++;
		}

		unIterador = individuos.iterator();
		while(unIterador.hasNext()) {
			pIndividuo = unIterador.next();
			pIndividuo.setPuntuacion((double)pIndividuo.getadaptacion()/(double)sumadap);
			punt_acu+= pIndividuo.getPuntuacion();
			pIndividuo.setPunt_acu(punt_acu);
		}
	}

	public void desplazar() {
		//MARGEN PARA EVITAR SUMADAPTACION = 0 SI CONVERGE LA POBLACION
		double maximo = individuos.get(pos_mejor).getadaptacion_bruta()*1.05;

		Iterator<Individuo> unIterador = individuos.iterator();
		Individuo pIndividuo = null;
		while(unIterador.hasNext()) {
			pIndividuo = unIterador.next();
			pIndividuo.setadaptacion(maximo-pIndividuo.getadaptacion_bruta());
		}
	}

	//MUESTREO ESTOCASTICO UNIVERSAL
	public void seleccionEstadistico() {
		int[] sel_super = new int[this.size()]; 									//SELECCIONADOS PARA SOBREVIVIR
		double a = new Random().nextDouble() * (((double) 1) / this.size());
		double prob; 															//PROBABILIDAD DE SELECCION
		int pos_super; 															//POSICION DEL SUPERVIVIENTE
		List<Individuo> pob_aux = new ArrayList<Individuo>(); 					//POBLACION AUXILIAR

		Iterator<Individuo> unIterador = individuos.iterator();

		int j = 0;
		while(unIterador.hasNext()) {
			unIterador.next();

			if(this.elite[j])
				sel_super[j] = j;
			else {
				prob = (a + j -1)/this.size();
				pos_super = 0;
				while((pos_super<this.size()) && (prob>individuos.get(pos_super).getPunt_acu())) {
					pos_super++;
					if(pos_super == this.size()) break;
				}
				if(pos_super == this.size())
					pos_super--;
				sel_super[j] = pos_super;
			}
			j++;
		}

		//SE GENERA LA POBLACION INTERMEDIA
		for(int i=0;i<this.size();i++) {
			pob_aux.add(individuos.get(sel_super[i]).clone());
		}

		//MACHACO POBLACION ANTERIOR Y LA REEMPLAZO CON LA DE SUPERVIVIENTES
		init();
		unIterador = pob_aux.iterator();
		while(unIterador.hasNext()) {
			individuos.add(unIterador.next().clone());
		}
	}

	public void seleccionTorneo() {
		double aptitud_mejor = 0;
		int[] sel_super = new int[this.size()]; 				//SELECCIONADOS PARA SOBREVIVIR
		int  prob; 												//PROBABILIDAD DE SELECCION
		int pos_super = 0; 										//POSICION DEL SUPERVIVIENTE
		List<Individuo> pob_aux = new ArrayList<Individuo>();	//POBLACION AUXILIAR

		Iterator<Individuo> unIterador=individuos.iterator();

		for(int i=0;i<this.size();i++) {
			if (this.elite[i])
				sel_super[i]=i;
			else {
				for(int j=0;j<this.torneo;j++) {
					prob = new Random().nextInt(this.size());
					if(j == 0) {
						aptitud_mejor = individuos.get(prob).getadaptacion();
						pos_super = prob;
					} else if(aptitud_mejor < individuos.get(prob).getadaptacion()) {
						aptitud_mejor = individuos.get(prob).getadaptacion();
						pos_super = prob;
					}
				}
				sel_super[i] = pos_super;
			}
		}

		//SE GENERA LA POBLACION INTERMEDIA
		for(int i=0;i<this.size();i++) {
			pob_aux.add(individuos.get(sel_super[i]).clone());
		}

		//MACHACO POBLACION ANTERIOR Y LA REEMPLAZO CON LA DE SUPERVIVIENTES
		init();
		unIterador = pob_aux.iterator();
		while(unIterador.hasNext()) {
			individuos.add(unIterador.next().clone());
		}
	}

	public void seleccionRuleta() {
		int[] sel_super = new int[this.size()]; 				//SELECCIONADOS PARA SOBREVIVIR
		double prob; 											//PROBABILIDAD DE SELECCION
		int pos_super; 											//POSICION DEL SUPERVIVIENTE
		List<Individuo> pob_aux = new ArrayList<Individuo>(); 	//POBLACION AUXILIAR

		Iterator<Individuo> unIterador = individuos.iterator();

		int j = 0;
		while(unIterador.hasNext()) {
			unIterador.next();
			if(this.elite[j])
				sel_super[j] = j;
			else {
				prob = Math.random();
				pos_super = 0;
				while((pos_super<this.size()) && (prob>individuos.get(pos_super).getPunt_acu())) {
					pos_super++;
					if(pos_super == this.size()) break;
				}
				if (pos_super == this.size())
					pos_super--;
				sel_super[j] = pos_super;
			}
			j++;
		}

		//SE GENERA LA POBLACION INTERMEDIA
		for(int i=0;i<this.size();i++) {
			pob_aux.add(individuos.get(sel_super[i]).clone());
		}

		//MACHACO POBLACION ANTERIOR Y LA REEMPLAZO CON LA DE SUPERVIVIENTES
		init();
		unIterador = pob_aux.iterator();
		while(unIterador.hasNext()) {
			individuos.add(unIterador.next().clone());
		}
	}

	public void reproduccion(double prob_cruce, Object x_min, Object x_max, int tCruce) {
		int lcrom = individuos.get(0).damelCrom();
		double sel_cruce[] = new double [this.size()];	//SELECCIONADOS PARA REPRODUCIRSE
		int num_sel_cruce = 0;							//CONTADOR DE SELECCIONADOS
		double prob;
		Individuo hijo1, hijo2;

		//SE ELIGEN LOS INDIVIDUOS A CRUZAR
		for(int i=0;i<this.size();i++) {
			//SE GENERAN TAM_POB NúMEROS ALETATORIOS 1, EN [0,1]
			prob = Math.random();
			//SE ELIGEN LOS INDIVIDUOS DE LAS POSICIONES I
			//CON AI< PROB_CRUCE
			if(prob < prob_cruce) {
				sel_cruce[num_sel_cruce] = i;
				num_sel_cruce++;
			}
		}

		//EL NUMERO DE SELECCIONADOS SE HACE PAR
		if ((num_sel_cruce % 2) == 1)
			num_sel_cruce--;

		//SE CRUZAN LOS INDIVIDUOS ELEGIDOS EN UN PUNTO AL AZAR
		int punto_cruce = 0 + (int)(Math.random() * ((lcrom-0) + 1));
		Individuo[] unReturn = new Individuo[2];
		for(int i=0;i<num_sel_cruce;i+=2) {
			switch (tCruce) {
				case 0:
					unReturn = cruce(individuos.get(i), individuos.get(i+1), punto_cruce, x_min, x_max);
					break;
				case 1:
					unReturn = cruce(individuos.get(i), individuos.get(i+1), punto_cruce, x_min, x_max);
			}
			hijo1 = unReturn[0];
			hijo2 = unReturn[1];

			//LOS NUEVOS INDIVIDUOS SUTITUYEN A SUS PROGENITORES,RESPETANDO LA ELITE
			if(esMejor(hijo1.getadaptacion_bruta(),individuos.get(i).getadaptacion_bruta()) && !this.elite[i])
				individuos.set(i,hijo1);
			if(esMejor(hijo2.getadaptacion_bruta(),individuos.get(i+1).getadaptacion_bruta()) && !this.elite[i+1])
				individuos.set(i+1,hijo2);
		}
	}

	public void mutacion(double prob_mut) {
		Integer lcrom = individuos.get(0).damelCrom();
		boolean mutado;
		double prob;

		for(int i=0;i<this.size();i++) {
			mutado = false;
			for(int j=0;j<lcrom && !this.elite[i];j++) {
				//SE GENERA UN NUMERO ALEATORIO EN [0 1)
				prob = Math.random();
				//SE MUTAN AQUELLOS GENES CON PROB < QUE PROB_MUT
				if(prob<prob_mut) {
					individuos.get(i).genes.get(j).muta();
					mutado = true;
				}
			}
			if(mutado)
				individuos.get(i).adaptacion_bruta = individuos.get(i).calculaadaptacion_bruta();
		}
	}

	public void generarElite() {
		if(!elitismo) return;

		sel_elite = new int[this.tam_elite]; //seleccionados como la elite
		elite = new boolean[this.size()];

		for(int i=0;i<tam_elite;i++) {
			sel_elite[i] = i;
		}

		ordenar_por_adaptacion();

		for(int i=tam_elite;i<this.size();i++) {
			int j = 0;
			while(j<tam_elite && esMejor(individuos.get(sel_elite[j]).getadaptacion_bruta(),individuos.get(i).getadaptacion_bruta())) {
				j++;
			}
			if (j < tam_elite) {
				insertar_ordenadamente (j, i);
			}
		}

		for(int i=0;i<tam_elite;i++) {
			this.elite[sel_elite[i]] = true;
		}
	}

	// =========================
	// == METODOS PRIVADOS
	// =========================

	private int size() {
		return individuos.size();
	}

	private void init() {
		individuos = new ArrayList<Individuo>();
		pos_mejor = 0;
		pos_peor = 0;
		sumadap = 0;
	}

	private boolean esMejor(Double a, Double b) {
		switch (this.tipoAlgoritmo) {
			case 0:
				return a < b;
			case 1:
				return a > b;
			default:
				return a < b;
		}
	}

	private Individuo[] cruce (Individuo padre1, Individuo padre2, int punto_cruce, Object x_min, Object x_max) {
		Integer lcrom = padre1.damelCrom();
		Individuo hijo1 = padre1.newInstance(x_min, x_max, prec);
		Individuo hijo2 = padre2.newInstance(x_min, x_max, prec);
		Individuo[] unReturn = {hijo1,hijo2};

		//PRIMERA PARTE DEL INTERCAMBIO: 1 A 1 Y 2 A 2
		for (int i=0;i<punto_cruce;i++) {
			hijo1.genes.set(i, padre1.genes.get(i));
			hijo2.genes.set(i, padre2.genes.get(i));
		}

		//SEGUNDA PARTE: 1 A 2 Y 2 A 1
		for (int i=punto_cruce;i<lcrom;i++) {
			hijo1.genes.set(i, padre2.genes.get(i).clone());
			hijo2.genes.set(i, padre1.genes.get(i).clone());
		}

		return unReturn;
	}

	private void insertar_ordenadamente(int pos, int valor) {
		for (int i=pos+1;i<this.tam_elite;i++) {
			this.sel_elite[i] = sel_elite[i-1];
		}
		sel_elite[pos] = valor;
	}

	private void ordenar_por_adaptacion() {
		 int j;
	     boolean flag = true;
	     int temp;

	     while(flag) {
            flag = false;
            for(j=0;j<tam_elite-1;j++) {
               if ( esMejor (individuos.get( sel_elite[ j ]).getadaptacion_bruta(), individuos.get( sel_elite[ j+1 ]).getadaptacion_bruta() )) {
	               temp = sel_elite[ j ];
	               sel_elite[ j ] = sel_elite[ j+1 ];
	               sel_elite[ j+1 ] = temp;
	               flag = true;
              }
            }
	    }
	}

	public  double dameMediaAdaptacion()
	{
		double unaMedia=0;
		double total=0;
		Iterator<Individuo>  unIterador=individuos.iterator();		
		
		while (unIterador.hasNext())
		{			
			
			total+=(Double)unIterador.next().getadaptacion_bruta();
		}		
		unaMedia=(double)total/(double) this.size();
		
		return unaMedia;
	}
}