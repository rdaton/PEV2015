package individuo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections4.iterators.PermutationIterator;

import logica.Calculadora;
import sun.awt.AWTAccessor.SystemColorAccessor;

public class Poblacion {

	private List<Individuo> individuos;
	private int pos_mejor;
	private int pos_peor;
	private int x_min;
	private int x_max;
	private double sumadap;
	private int torneo;
	private int tam_pob;
	private int tam_elite;
	private boolean[] elite;
	private int[] sel_elite;
	private boolean elitismo;
	private int tipoAlgoritmo; //0: MINIMIZACION, 1: MAXIMIZACION

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public Poblacion(int torneo,int tipoAlgoritmo, Individuo prototipo, int tam_pob, int x_min, int x_max, boolean elitismo) {
		this.tipoAlgoritmo = tipoAlgoritmo;
		System.out.println("tipoAlgoritmo => "+tipoAlgoritmo);
		init();
		Individuo unIndividuo = null;

		for(int i=0;i<tam_pob;i++) {
			unIndividuo = prototipo.newInstance(x_min, x_max);
			individuos.add(unIndividuo);
		}

		this.x_min = x_min;
		this.x_max = x_max;
		this.tam_pob = tam_pob;
		this.torneo = torneo;
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

	// =========================
	// == TIPOS DE SELECCION
	// =========================

	//MUESTREO ESTOCASTICO UNIVERSAL
	public void seleccionEstadistico() {
		int[] sel_super = new int[this.size()]; 								//SELECCIONADOS PARA SOBREVIVIR
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

	public void seleccionRanking() {
		int[] sel_super = new int[this.size()]; 				//SELECCIONADOS PARA SOBREVIVIR
		double prob; 											//PROBABILIDAD DE SELECCION
		List<Individuo> pob_aux = new ArrayList<Individuo>(); 	//POBLACION AUXILIAR

		Iterator<Individuo> unIterador = individuos.iterator();

		if(!elitismo) {
			ordenar_por_adaptacion_ranking();
		}

		int[] ruleta = new int[this.size()];

		//PRESION SELECTIVA DE RANKING
		double A = (double)1/(double)(this.size()*0.8);
		double B = 0;
		double n = this.size()/8;

		int z = 0;
		for (int i = 0; z < ruleta.length && n >= 0; i++) {
			double g = ((A*(n--)+B)*100);
			if ((g%1.0) >= 0.5) {
				g = (int)((A*(n+1)+B)*100)+1.0;
			} else if(g%1.0 >= 0) {
				g = (int)((A*((n+1))+B)*100);
			}
			for (int j = 0; z < this.size() && j < g ; j++, z++) {
				ruleta[z] = i;
			}
		}

		//SELECCIONAMOS PROPORCIONALMENTE SEGUN EL RANGO
		int j = 0;
		while(unIterador.hasNext()) {
			unIterador.next();
			if(this.elite[j])
				sel_super[j] = j;
			else {
				prob = Math.random()*(double)this.size();
				if (prob<0) { prob*=-1; }
				sel_super[j] = ruleta[(int) prob];
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

	public void seleccionRestos() {
		 //POBLACION AUXILIAR
        List<Individuo> pob_aux = new ArrayList<Individuo>();
        //calculo la media
        double mediaFitness=this.dameMediaAdaptacion();
        //saco valores relativos
        Double[] val_relativos= new Double[this.size()];
        //saco  parte entera de valores relavito
        int[] val_relativos_int= new int[this.size()];
        //array de restos
        double[] restos=new double[this.size()];
        //array de indices
        int indices[]=new int[this.size()];
        for (int i=0;i<this.size();i++)
        {
            val_relativos[i]=this.individuos.get(i).getadaptacion_bruta()/mediaFitness;
            val_relativos_int[i]=val_relativos[i].intValue();
            restos[i]=val_relativos[i]-val_relativos_int[i];
        }

        int n=0;

        for (int i=0;i<this.size();i++)
        {
            while(n != val_relativos_int[i] && n<this.size())
            {
                pob_aux.add(this.individuos.get(i));
                n++;
            }
        }

        int[] indicesOrdenados=bubbleSort(restos);


        int r=0;
        while(n<this.size())
        {
            pob_aux.set(n, individuos.get(indicesOrdenados[r]).clone());
            n++;
            r++;

        }
	}

	public void seleccionTruncamiento() {
		System.out.println("========= seleccionTruncamiento =========");
		int[] sel_super = new int[this.size()]; 				//SELECCIONADOS PARA SOBREVIVIR
		double prob; 											//PROBABILIDAD DE SELECCION
		List<Individuo> pob_aux = new ArrayList<Individuo>(); 	//POBLACION AUXILIAR

		Iterator<Individuo> unIterador = individuos.iterator();

		if(!elitismo) {
			ordenar_por_adaptacion_ranking();
		}

		double numerador = 1.0;
		double p = 3.0;
		double div = numerador/p;
		int max_poblacion = (int) ((div)*individuos.size());


		//A�ADIMOS PROPORCION DE LA POBLACION
		for(int i=0;i<max_poblacion;i++) {
			pob_aux.add(individuos.get(i).clone());
		}

		div = Math.ceil(div);
		int tam_pob_aux = pob_aux.size();
		System.out.println("div: "+div);

		System.out.println("clonamos 1/p veces");

		//POR CADA UNO CLONAMOS 1/P VECES
		for(int i=0;i<tam_pob_aux;i++) {
			for(int j=0;j<div;j++) {
				pob_aux.add(pob_aux.get(i).clone());
			}
		}

		tam_pob_aux = pob_aux.size();

		//SI LA POBLACION ES MENOR QUE EL TOTAL LLENMOS DESDE EL INICIO
		if(pob_aux.size() < individuos.size()) {
			System.out.println("La poblacion es menor");
			for(int i=0;i<tam_pob_aux && pob_aux.size() < individuos.size();i++) {
				for(int j=0;j<div;j++) {
					pob_aux.add(pob_aux.get(i).clone());
				}
			}
		}

		//MACHACO POBLACION ANTERIOR Y LA REEMPLAZO CON LA DE SUPERVIVIENTES
		init();
		unIterador = pob_aux.iterator();
		while(unIterador.hasNext()) {
			individuos.add(unIterador.next().clone());
		}
		pintarPoblacion();
	}

	public void reproduccion(double prob_cruce, int x_min, int x_max, int tCruce) {
		System.out.println("========= Reproduccion =========");
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
		System.out.println("tCruce: "+tCruce);
		System.out.println("num_sel_cruce: "+num_sel_cruce);
		for(int i=0;i<num_sel_cruce;i+=2) {
			switch (tCruce) {
				case 0:
					unReturn = crucePmxDaton(individuos.get(i), individuos.get(i+1), x_min, x_max);
					break;
				case 1:
					unReturn = cruceOx(individuos.get(i), individuos.get(i+1), punto_cruce, x_min, x_max);
					break;
				case 2:
					unReturn = cruceOxPosPrior(individuos.get(i), individuos.get(i+1), punto_cruce, x_min, x_max);
					break;
				case 3:
					unReturn = cruceOxOrdPrior(individuos.get(i), individuos.get(i+1), punto_cruce, x_min, x_max);
					break;
				case 4:
					unReturn = cruceCiclos(individuos.get(i), individuos.get(i+1), punto_cruce, x_min, x_max);
					break;
				case 5:
					unReturn = cruceRecombinacionRutas(individuos.get(i), individuos.get(i+1), punto_cruce, x_min, x_max);
					break;
				case 6:
					unReturn = cruceOrdinal(individuos.get(i), individuos.get(i+1), punto_cruce, x_min, x_max);
					break;
				case 7:
					unReturn = crucePropio(individuos.get(i), individuos.get(i+1), punto_cruce, x_min, x_max);
					break;
			}
			hijo1 = unReturn[0];
			hijo2 = unReturn[1];
			hijo1.decod();
			hijo1.calculaadaptacion_bruta();
			hijo2.decod();
			hijo1.calculaadaptacion_bruta();

			//LOS NUEVOS INDIVIDUOS SUTITUYEN A SUS PROGENITORES,RESPETANDO LA ELITE
			if(esMejor(hijo1.getadaptacion_bruta(),individuos.get(i).getadaptacion_bruta()) && !this.elite[i])
				individuos.set(i,hijo1);
			if(esMejor(hijo2.getadaptacion_bruta(),individuos.get(i+1).getadaptacion_bruta()) && !this.elite[i+1])
				individuos.set(i+1,hijo2);
		}
	}

	public void mutacion(double prob_mut,int tipoMutacion) {
		boolean mutado;
		double prob;

		for(int i=0;i<this.size();i++) {
			mutado = false;
			//for(int j=0;j<lcrom && !this.elite[i];j++) {
				//SE GENERA UN NUMERO ALEATORIO EN [0 1)
				prob = Math.random();
				//SE MUTAN AQUELLOS GENES CON PROB < QUE PROB_MUT
				if(prob<prob_mut && !this.elite[i]) {
					//individuos.get(i).genes.get(j).muta();
					//switch ....
					//MUTACION
					switch (tipoMutacion) {
						case 0:
							mutacionInsercion(i);
							break;
						case 1:
							mutacionIntercambio(i);
							break;
						case 2:
							mutacionInversion(i);
							break;
						case 3:
							mutacionHeuristicaDaton(i);
							break;
						case 4:
							mutacionPropia(i);
							break;
						default:
							mutacionInsercion(i);
							break;
					}
					mutado = true;
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

	public static boolean contiene(List<Gen> unCromosoma, Gen unGen, int a, int b , int lcrom) {
		boolean ret = false;
		int i = a;
		while((!ret) && i<=b && i<lcrom ) {
			ret=unCromosoma.get(i).equals(unGen);
			i++;
		}
		return ret;
	}

	public double dameMediaAdaptacion() {
		double unaMedia = 0;
		double total = 0;
		Iterator<Individuo> unIterador = individuos.iterator();
		while (unIterador.hasNext()) {
			total+=(Double)unIterador.next().getadaptacion_bruta();
		}
		unaMedia = (double)total/(double) this.size();
		return unaMedia;
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

	private void ordenar_por_adaptacion_ranking() {
		 int j;
	     boolean flag = true;
	     Individuo temp;
	     int tipoAlgoritmo = this.tipoAlgoritmo;
	     this.tipoAlgoritmo = 1;

	     while(flag) {
           flag = false;
           for(j=0;j<tam_pob-1;j++) {
              if ( esMejor (individuos.get(j).getadaptacion_bruta(), individuos.get(j+1).getadaptacion_bruta() )) {

            	//COPIAR j
        	  	temp = individuos.get(j).newInstance(x_min, x_max);
        	  	for (int i=0;i<individuos.get(j).lcrom;i++) {
        	  		temp.genes.set(i, individuos.get(j).genes.get(i));
				}
        	  	temp.decod();
        	  	temp.adaptacion_bruta = individuos.get(j).adaptacion_bruta;
        	  	temp.setadaptacion(individuos.get(j).getadaptacion());
        	  	temp.setPuntuacion( individuos.get(j).getPuntuacion());
        	  	temp.setPunt_acu( individuos.get(j).getPunt_acu());

        	  	//COPIAR j = j+1
				for (int i=0;i<individuos.get(j+1).lcrom;i++) {
					individuos.get(j).genes.set(i, individuos.get(j+1).genes.get(i));
				}
				individuos.get(j).decod();
				individuos.get(j).adaptacion_bruta = individuos.get(j+1).adaptacion_bruta;
				individuos.get(j).setadaptacion(individuos.get(j+1).getadaptacion());
				individuos.get(j).setPuntuacion( individuos.get(j+1).getPuntuacion());
				individuos.get(j).setPunt_acu( individuos.get(j+1).getPunt_acu());

				//COPIAR j+1 = TEMP
				for (int i=0;i<temp.lcrom;i++) {
					individuos.get(j+1).genes.set(i, temp.genes.get(i));
				}
				individuos.get(j+1).decod();
				individuos.get(j+1).adaptacion_bruta = temp.adaptacion_bruta;
				individuos.get(j+1).setadaptacion(temp.getadaptacion());
				individuos.get(j+1).setPuntuacion(temp.getPuntuacion());
				individuos.get(j+1).setPunt_acu(temp.getPunt_acu());

				flag = true;
             }
           }
	    }

	    this.tipoAlgoritmo = tipoAlgoritmo;
	}

	private int[] bubbleSort(double[] arr) {
        int indices[]=new int[this.size()];
        for (int i=0;i<this.size();i++)
        {
            indices[i]=i;
        }


          boolean swapped = true;
          int j = 0;
          int tmp;
          while (swapped) {
                swapped = false;
                j++;
                for (int i = 0; i < this.size() - j; i++) {
                      if (arr[i] <= arr[i+1]) {
                            tmp = indices[i];
                            indices[i]=indices[i + 1];
                            indices[i+1] = tmp;
                            swapped = true;
                      }
                }
          }

          return indices;

    }

	//RELLENA HUECOS .. DESDE EL PRINCIPIO
	private void cruceOx_aux(Individuo padre,Individuo hijo, int lcrom) {
		int r = 0;
		int i = 0;
		//RELLENO DESDE PRINCIPIO
		while(i<lcrom && r < lcrom) { //&&RELLENADOS<MAXRELLENAR)
			if (hijo.genes.get(r).estaVacio()) {
				if (!contiene(hijo.genes,padre.genes.get(i),0,lcrom-1,lcrom)) {
					hijo.genes.set(r, padre.genes.get(i).clone());
					r++;
				}
				i++;
			} else {
				r++;
			}
			hijo.decod(); //debug
		}
	}

	private int cruceOxPosPriorPunto_aux(Individuo padre, Individuo hijo,int lcrom) {
		int a = 0;
		boolean seguir = true;
		//SACO INDICES RANDOM QUE ME SIRVAN PARA HIJO1
		while (seguir) {
			a = Calculadora.dameRandom(0, lcrom-1);
			seguir = (!hijo.genes.get(a).estaVacio() || contiene(hijo.genes,padre.genes.get(a),0,lcrom-1,lcrom));
		}
		return a;
	}

	private boolean contiene (Integer[] cadena, int num) {
		boolean enc = false;
		int i = 0;
		while(!enc&&i<cadena.length) {
			enc = cadena[i].intValue() == num;
			i++;
		}
		return enc;
	}

	private void damePosiciones(Integer[] cadena, int lcrom) {
		for (int i=0;i<cadena.length;i++) {
			cadena[i] = -1;
		}
		int ret = 0;
		int a = Calculadora.dameRandom(0, lcrom-1);
		for (int i=0;i<cadena.length;i++) {
			while(contiene(cadena,a)) {
				a = Calculadora.dameRandom(0, lcrom-1);
			}
			cadena[i] = new Integer(a);
		}
	}

	//BUSCA LOS VALORES ELEGIDOS DE PADRE1, EN PADRE2
	private Integer[] cruceOxOrdPrior_transforma(Individuo padre1,Individuo padre2, Integer[] cadena,int lcrom) {
		Integer[] unaCadena=new Integer[cadena.length];
		for (int i=0;i<cadena.length;i++) {
			int r = 0;
			boolean enc = false;
			while (!enc && r<lcrom) {
				enc = padre1.genes.get(cadena[i]).equals(padre2.genes.get(r));
				if (!enc) r++;
			}
			unaCadena[i] = r;
		}
		return unaCadena;
	}

	private void antiCopiaOX(Individuo padre, Individuo hijo, Integer[] cadena, int lcrom) {
		for (int i=0;i<lcrom; i++) {
			if (!contiene(cadena,i)) {
				hijo.genes.set(i, padre.genes.get(i));
			}
		}
	}

	//PROFUNDIZA DEMASIADO, HACE FALTA ABSTRAER
	//¡GRAN CHAPUZA!
	private void construirRecomb(Individuo hijo,Gen unGen,int[][] unaMatriz,int lcrom) {
		Integer[] listaPos = new Integer[lcrom];
		for (int i=1;i<listaPos.length;i++) {
			listaPos[i] = -1;
		}
		//EMPIEZO CON PRIMERA CIUDAD DEL PADRE (unGen)
		listaPos[0] = (Integer)unGen.clone().bit;
		hijo.genes.set(0,unGen.clone());

		boolean bloqueo = true;
		while(bloqueo) {
			for (int i=1;i<lcrom;i++) {
				listaPos[i] = dameMenosConectada(i-1,unaMatriz,listaPos,lcrom);
			}
			bloqueo = esBloqueo(listaPos, unaMatriz);
		}

		//UNA VEZ GENERADO , CONVIERTO LA SECUENCIA A GENES
		//CHAPUZA
		for (int i=1;i<lcrom;i++) {
			hijo.genes.set(i, new Gen_f1(listaPos[i]));
		}
	}

	//BASICAMETNE NO HACE NADA, PORQUE TODAS ESTáN IGUA DE CONECTADAS
	//HACE RANDOMS'S
	private int dameMenosConectada(int ciudad, int[][] unaMatriz, Integer[] listaPos, int lcrom) {
		int a = Calculadora.dameRandom(0, lcrom-1);
		while(contiene(listaPos,a)) {
			a = Calculadora.dameRandom(0, lcrom-1);
		}
		listaPos[ciudad+1] = new Integer(a);
		return a;
	}

	//¿ES ESTE CROMOSOMA VIABLE?
	boolean esBloqueo(Integer[]ListaPos, int[][] unaMatriz) {
		return false;
	}

	private void swapGen(List<Gen> listaGenes ,int a, int b) {
		if (a==b) return;
		Gen g3 = listaGenes.get(a).clone();
		listaGenes.set(a, listaGenes.get(b).clone());
		listaGenes.set(b,g3);
	}

	// =========================
	// == TIPOS DE CRUCES
	// =========================

	private void crucePmx_aux(Individuo padre1, Individuo padre2, Individuo hijo, int lcrom) {
		
		
		//RELLENO DESDE PRINCIPIO
		for (int i=0;i<lcrom;i++) { 
			if (hijo.genes.get(i).estaVacio()  ) 
				{
				if (!contiene(hijo.genes,padre1.genes.get(i),0,lcrom-1,lcrom))				
					hijo.genes.set(i, padre1.genes.get(i).clone());			
				else
					hijo.genes.set(i, padre2.genes.get(i).clone());
				}				
			} 
		
			
		
	}
	private Individuo[] crucePmxDaton (Individuo padre1, Individuo padre2,int x_min, int x_max) {
		Integer lcrom = padre1.damelCrom();
		Individuo hijo1 = padre1.newInstance(x_min, x_max);
		hijo1.borraGenes(); //genes a -1
		Individuo hijo2 = padre2.newInstance(x_min, x_max);
		hijo2.borraGenes(); //genes a -1
		//Elegir aleatoriamente dos puntos de corte.
		//pCorte1<pCorte2
		int pCorte1 = Calculadora.dameRandom(0, lcrom-3);
		int pCorte2 = Calculadora.dameRandom(pCorte1+2, lcrom-1);
		//Copiar los valores de las subcadenas comprendidas entre
		//dichos puntos en los hijos que se generan.
		for (int i=pCorte1+1;i<pCorte2;i++)
		{
			hijo1.genes.set(i, padre2.genes.get(i).clone());
			hijo2.genes.set(i, padre1.genes.get(i).clone());
			hijo1.decod();
			hijo2.decod();
		}

		/*
		 * Para los valores que faltan en los hijos se copian los
		valores de los padres:
		 */
		/*
		 * a)Si un valor no está en la subcadena intercambiada, se copia igual
		 * 
		 * 
		 * b)Si está en la subcadena intercambiada, entonces se sustituye por el
		 * valor que tenga dicha subcadena en el otro padre
		 

		*/

		//relleno hijo1 con elementos de padre1 y padre2
		this.crucePmx_aux(padre1,padre2, hijo1, lcrom);		
		//relleno hijo2 con elementos de padre2 y padre1
		this.crucePmx_aux(padre2,padre1, hijo2,lcrom);
		
		
		Individuo[] unReturn = {hijo1,hijo2};
		hijo2.decod();
		return unReturn;
	}
	
	private Individuo[] crucePmx (Individuo padre1, Individuo padre2, int x_min, int x_max) {
		System.out.println("======== crucePmx ========");
		Integer lcrom = padre1.damelCrom();
		System.out.println("lcrom: "+lcrom);
		Individuo hijo1 = padre1.newInstance(x_min, x_max);
		Individuo hijo2 = padre2.newInstance(x_min, x_max);
		hijo1.borraGenes(); //genes a -1
		hijo2.borraGenes(); //genes a -1
		Individuo[] unReturn = {hijo1,hijo2};

		String padre1S = "{";
		for (int i=0;i<lcrom;i++) {
			padre1S += padre1.genes.get(i).bit+", ";
		}
		padre1S += "}";
		System.out.println("padre1 => "+padre1S);

		String padre2S = "{";
		for (int i=0;i<lcrom;i++) {
			padre2S += padre2.genes.get(i).bit+", ";
		}
		padre2S += "}";
		System.out.println("padre2 => "+padre2S);

		int pmx1 = Calculadora.dameRandom(0, lcrom-1);
		int pmx2 = Calculadora.dameRandom(0, lcrom-1);

		if(pmx1 > pmx2) {
			int aux = pmx1;
			pmx1 = pmx2;
			pmx2 = aux;
		}

		//System.out.println("pmx1: "+pmx1);
		//System.out.println("pmx2: "+pmx2);

		List<Object[]> parejas = new ArrayList<Object[]>();

		//RECORREMOS EL MEDIO Y GUARDAMOS LAS PAREJAS
		for(int i = pmx1;i <= pmx2;i++) {
			Integer[] pareja = {(Integer) padre2.genes.get(i).bit,(Integer) padre1.genes.get(i).bit};
			parejas.add(pareja);
			hijo1.genes.set(i, padre2.genes.get(i));
			hijo2.genes.set(i, padre1.genes.get(i));
		}

		for(int i = 0;i < parejas.size();i++) {
			//System.out.println("pareja: {"+parejas.get(i)[0]+", "+parejas.get(i)[1]+"}");
		}

		//RECORREMOS LA DERECHA
		for(int i = pmx2+1;i < lcrom;i++) {

			int valorIni1 =  (Integer) padre1.genes.get(i).bit;
			int valorIni2 =  (Integer) padre2.genes.get(i).bit;

			//Hijo1
			int j = pmx1;
			busquedaH1:
			while(j <= i) {
				if(valorIni1 ==  (Integer) hijo1.genes.get(j).bit || hijo1.genes.get(j).bit.equals(valorIni1)) {
					for(int l=0;l<parejas.size();l++) {
						if(parejas.get(l)[0].equals(valorIni1)) {
							valorIni1 = (Integer) parejas.get(l)[1];
							j = pmx1;
							continue busquedaH1;
						}
					}
				}
				j++;
			}
			hijo1.genes.get(i).bit = valorIni1;

			//Hijo2
			int k = pmx1;
			busquedaH2:
			while(k <= i) {
				if(valorIni2 ==  (Integer) hijo2.genes.get(k).bit || hijo2.genes.get(k).bit.equals(valorIni2)) {
					for(int l=0;l<parejas.size();l++) {
						if(parejas.get(l)[1].equals(valorIni2)) {
							valorIni2 = (Integer) parejas.get(l)[0];
							k = pmx1;
							continue busquedaH2;
						}
					}
				}
				k++;
			}
			hijo2.genes.get(i).bit = valorIni2;
		}

		//RECORREMOS LA IZQUIERDA
		for(int i = 0;i < pmx1;i++) {

			int valorIni1 =  (Integer) padre1.genes.get(i).bit;
			int valorIni2 =  (Integer) padre2.genes.get(i).bit;

			//Hijo1
			int j = 0;
			busquedaH1:
			while(j < lcrom) {
				if(valorIni1 ==  (Integer) hijo1.genes.get(j).bit || hijo1.genes.get(j).bit.equals(valorIni1)) {
					for(int l=0;l<parejas.size();l++) {
						if(parejas.get(l)[0].equals(valorIni1)) {
							valorIni1 = (Integer) parejas.get(l)[1];
							j = 0;
							continue busquedaH1;
						}
					}
				}
				j++;
			}
			hijo1.genes.get(i).bit = valorIni1;

			//Hijo2
			int k = 0;
			busquedaH2:
			while(k < lcrom) {
				if(valorIni2 ==  (Integer) hijo2.genes.get(k).bit || hijo2.genes.get(k).bit.equals(valorIni2)) {
					for(int l=0;l<parejas.size();l++) {
						if(parejas.get(l)[1].equals(valorIni2)) {
							valorIni2 = (Integer) parejas.get(l)[0];
							k = 0;
							continue busquedaH2;
						}
					}
				}
				k++;
			}
			hijo2.genes.get(i).bit = valorIni2;

		}

		String hijo1S = "{";
		for (int i=0;i<lcrom;i++) {
			hijo1S += hijo1.genes.get(i).bit+", ";
		}
		hijo1S += "}";
		System.out.println("hijo1 => "+hijo1S);

		String hijo2S = "{";
		for (int i=0;i<lcrom;i++) {
			hijo2S += hijo2.genes.get(i).bit+", ";
		}
		hijo2S += "}";
		System.out.println("hijo2 => "+hijo2S);

		return unReturn;
	}

	private Individuo[] cruceOx (Individuo padre1, Individuo padre2, int punto_cruce, int x_min, int x_max) {
		Integer lcrom = padre1.damelCrom();
		Individuo hijo1 = padre1.newInstance(x_min, x_max);
		hijo1.borraGenes(); //genes a -1
		Individuo hijo2 = padre2.newInstance(x_min, x_max);
		hijo2.borraGenes(); //genes a -1
		//Elegir aleatoriamente dos puntos de corte.
		//pCorte1<pCorte2
		int pCorte1 = Calculadora.dameRandom(0, lcrom-3);
		int pCorte2 = Calculadora.dameRandom(pCorte1+2, lcrom-1);
		//Copiar los valores de las subcadenas comprendidas entre
		//dichos puntos en los hijos que se generan. (hijo1 de padre2 , hijo2 de padre1)
		for (int i=pCorte1+1;i<pCorte2;i++)
		{
			hijo1.genes.set(i, padre1.genes.get(i).clone());
			hijo2.genes.set(i, padre2.genes.get(i).clone());
			hijo1.decod();
			hijo2.decod();
		}

		/*
		 * Para los valores que faltan en los hijos se copian los
		valores de los padres contrarios comenzando a partir de la zona
		copiada y respetando el orden:
		 */
		/*
		 * Si un valor no está en la subcadena intercambiada, se
		copia igual.
		 Si está en la subcadena intercambiada, entonces se
		pasa al siguiente posible.
		*/

		//relleno hijo1 con elementos de padre1
		this.cruceOx_aux(padre2, hijo1, lcrom);

		//relleno hijo2 con elementos de padre2
		this.cruceOx_aux(padre1, hijo2,lcrom);

		Individuo[] unReturn = {hijo1,hijo2};

		
		return unReturn;
	}

	private Individuo[] cruceOxPosPrior (Individuo padre1, Individuo padre2, int punto_cruce, int x_min, int x_max) {
		Integer lcrom = padre1.damelCrom();
		Individuo hijo1 = padre1.newInstance(x_min, x_max);
		hijo1.borraGenes(); //genes a -1
		Individuo hijo2 = padre2.newInstance(x_min, x_max);
		hijo2.borraGenes(); //genes a -1
		//puntos al azar, para cruzar
		int num_randoms=Calculadora.dameRandom(0, lcrom-1);
		for (int i=0;i<num_randoms;i++)
		{
		int a=cruceOxPosPriorPunto_aux(padre2,hijo1,lcrom);
		int b=cruceOxPosPriorPunto_aux(padre1,hijo2,lcrom);
		hijo1.genes.set(a, padre2.genes.get(a).clone());
		hijo2.genes.set(b, padre1.genes.get(b).clone());
		hijo1.decod();
		hijo2.decod();
		}

		/*
		 * Para los valores que faltan en los hijos se copian los
		valores de los padres comenzando a partir de la zona
		copiada y respetando el orden:
		 */
		/*
		 * Si un valor no está en la subcadena intercambiada, se
		copia igual.
		 Si está en la subcadena intercambiada, entonces se
		pasa al siguiente posible.
		*/


		//relleno hijo1 con elementos de padre1
		this.cruceOx_aux(padre1, hijo1,lcrom);
		//actualizo indices




		//relleno hijo2 con elementos de padre2
		this.cruceOx_aux(padre2, hijo2, lcrom);


		Individuo[] unReturn = {hijo1,hijo2};


		return unReturn;
	}

	private Individuo[] cruceOxOrdPrior (Individuo padre1, Individuo padre2, int punto_cruce, int x_min, int x_max) {

		/*
		 * Los individuos no intercambian ciudades, sino el orden
		relativo existente entre ellas.
		 */
		Integer lcrom = padre1.damelCrom();
		Individuo hijo1 = padre1.newInstance(x_min, x_max);
		hijo1.borraGenes(); //genes a -1
		Individuo hijo2 = padre2.newInstance(x_min, x_max);
		hijo2.borraGenes(); //genes a -1
		//Elegir aleatoriamente dos puntos de corte.
		//pCorte1<pCorte2
		int pCorte1 = Calculadora.dameRandom(0, lcrom-3);
		int pCorte2 = Calculadora.dameRandom(pCorte1+2, lcrom-1);
		//de estos punto de corte, salen las posiciones a intercambiar
		int num_randoms=pCorte2-pCorte1-1;


		//genero num_randoms posiciones, de padre1
		Integer[] conjuntoPosiciones=new Integer[num_randoms];
		damePosiciones(conjuntoPosiciones,lcrom);


		//obtengo posiciones en padre2
		Integer[] otrasPosiciones=
				cruceOxOrdPrior_transforma(padre1,padre2,conjuntoPosiciones,lcrom);
		//copio elementos no-descartados de padre2 en hijo1
		antiCopiaOX(padre2,hijo1,otrasPosiciones,lcrom);


		//hago lo mismo para hijo2
				//obtengo posiciones en padre2
				otrasPosiciones=
						cruceOxOrdPrior_transforma(padre2,padre1,conjuntoPosiciones,lcrom);
				//copio elementos no-descartados de padre2 en hijo1
				antiCopiaOX(padre1,hijo2,otrasPosiciones,lcrom);

		/*
		 * Para los valores que faltan en los hijos se copian los
		valores de los padres comenzando a partir de la zona
		copiada y respetando el orden:
		 */
		/*
		 * Si un valor no está en la subcadena intercambiada, se
		copia igual.
		 Si está en la subcadena intercambiada, entonces se
		pasa al siguiente posible.
		*/


		//relleno hijo1 con elementos de padre1
		this.cruceOx_aux(padre1, hijo1,lcrom);
		//actualizo indices


		//relleno hijo2 con elementos de padre2
		this.cruceOx_aux(padre2, hijo2,lcrom);

		hijo1.decod();
		hijo2.decod();
		Individuo[] unReturn = {hijo1,hijo2};


		return unReturn;

	}

	private Individuo[] cruceCiclos (Individuo padre1, Individuo padre2, int punto_cruce, int x_min, int x_max) {
		System.out.println("======== cruceCiclos ========");
		Integer lcrom = padre1.damelCrom();
		System.out.println("lcrom: "+lcrom);
		Individuo hijo1 = padre1.newInstance(x_min, x_max);
		Individuo hijo2 = padre2.newInstance(x_min, x_max);
		hijo1.borraGenes(); //genes a -1
		hijo2.borraGenes(); //genes a -1
		Individuo[] unReturn = {hijo1,hijo2};

		String padre1S = "{";
		for (int i=0;i<lcrom;i++) {
			padre1S += padre1.genes.get(i).bit+", ";
		}
		padre1S += "}";
		System.out.println("padre1 => "+padre1S);

		String padre2S = "{";
		for (int i=0;i<lcrom;i++) {
			padre2S += padre2.genes.get(i).bit+", ";
		}
		padre2S += "}";
		System.out.println("padre2 => "+padre2S);

		List<Object[]> parejas = new ArrayList<Object[]>();

		//GUARDAMOS LAS PAREJAS
		for(int i = 0;i < lcrom;i++) {
			Integer[] pareja = {(Integer) padre2.genes.get(i).bit,(Integer) padre1.genes.get(i).bit};
			parejas.add(pareja);
		}

		for(int i = 0;i < parejas.size();i++) {
			//System.out.println("pareja: {"+parejas.get(i)[0]+", "+parejas.get(i)[1]+"}");
		}

		int valorIni1 =  (Integer) padre1.genes.get(0).bit;
		int valorIni2 =  (Integer) padre2.genes.get(0).bit;

		hijo1.genes.get(0).bit = valorIni1;
		hijo2.genes.get(0).bit = valorIni2;

		// == HIJO 1 ==
		int posicion = (Integer) parejas.get(0)[0];
		boolean fin_ciclo = false;
		while (!fin_ciclo) {
			for(int k = 0;k<parejas.size();k++) {
				if((Integer)parejas.get(k)[1] == posicion) {
					hijo1.genes.get(k).bit = (Integer) parejas.get(k)[1];
					posicion = (Integer) parejas.get(k)[0];
					break;
				}
			}
			if( posicion == valorIni1 ) {
				fin_ciclo = true;
			}
		}

		//RELLENAMOS LOS VACIOS
		posicion = 0;
		for(int i=0;i<lcrom;i++) {
			if((Integer)hijo1.genes.get(i).bit == -1) {
				hijo1.genes.get(i).bit = (Integer) parejas.get(i)[0];
			}
		}

		String hijo1S = "{";
		for (int i=0;i<lcrom;i++) {
			hijo1S += hijo1.genes.get(i).bit+", ";
		}
		hijo1S += "}";
		System.out.println("hijo1 => "+hijo1S);

		// == HIJO 2 ==
		posicion = (Integer) parejas.get(0)[1];
		fin_ciclo = false;
		while (!fin_ciclo) {
			for(int k = 0;k<parejas.size();k++) {
				if((Integer)parejas.get(k)[0] == posicion) {
					hijo2.genes.get(k).bit = (Integer) parejas.get(k)[0];
					posicion = (Integer) parejas.get(k)[1];
					break;
				}
			}
			if( posicion == valorIni2 ) {
				fin_ciclo = true;
			}
		}

		//RELLENAMOS LOS VACIOS
		posicion = 0;
		for(int i=0;i<lcrom;i++) {
			if((Integer)hijo2.genes.get(i).bit == -1) {
				hijo2.genes.get(i).bit = (Integer) parejas.get(i)[1];
			}
		}

		String hijo2S = "{";
		for (int i=0;i<lcrom;i++) {
			hijo2S += hijo2.genes.get(i).bit+", ";
		}
		hijo2S += "}";
		System.out.println("hijo2 => "+hijo2S);

		return unReturn;
	}

	private Individuo[] cruceRecombinacionRutas (Individuo padre1, Individuo padre2, int punto_cruce, int x_min, int x_max) {
		Integer lcrom = padre1.damelCrom();
		Individuo hijo1 = padre1.newInstance(x_min, x_max);
		hijo1.borraGenes(); //genes a -1
		Individuo hijo2 = padre2.newInstance(x_min, x_max);
		hijo2.borraGenes(); //genes a -1

		//construyo matriz de adyacencia
		int[][] unaMatriz= new int[lcrom][lcrom];
		//da la casualidad de que en mi matriz, todas las ciudades están interconectadas entre sí
		for (int i=0;i<lcrom;i++)
		{
			for (int j=0;j<lcrom;j++)
			{
				unaMatriz[i][j]=j;
			}
		}

		//construyo hijo1, con primer elemento de padre2
		construirRecomb(hijo1,padre2.genes.get(0),unaMatriz,lcrom);

		//construyo hijo2, con primer elemento de padre1
		construirRecomb(hijo2,padre1.genes.get(0),unaMatriz,lcrom);

		hijo1.decod();
		hijo2.decod();
		Individuo[] unReturn = {hijo1,hijo2};

		return unReturn;
	}
	
	
	private Individuo[] cruceOrdinal (Individuo padre1, Individuo padre2, int punto_cruce, int x_min, int x_max)
	{
		int lcrom=padre1.lcrom;
		Individuo hijo1 = padre1.newInstance(x_min, x_max);
		Individuo hijo2 = padre2.newInstance(x_min, x_max);
		hijo1.borraGenes();
		hijo2.borraGenes();
		Individuo[] unReturn = {hijo1,hijo2};
		
		//paso de normal a ordinal
		List<Gen> genesPadre1=padre1.normalOrdinal();
		List<Gen> genesPadre2=padre2.normalOrdinal();
		
		
		//PRIMERA PARTE DEL INTERCAMBIO: 1 A 1 Y 2 A 2
		for (int i=0;i<punto_cruce;i++) {
			hijo1.genes.set(i,genesPadre1.get(i).clone());
			hijo2.genes.set(i,genesPadre2.get(i).clone());
		}

		//SEGUNDA PARTE: 1 A 2 Y 2 A 1
		for (int i=punto_cruce;i<lcrom;i++) {
			hijo2.genes.set(i,genesPadre1.get(i).clone());
			hijo1.genes.set(i,genesPadre2.get(i).clone());
		}

		
		
		
		//paso de ordinal a normal
		List<Gen> genesHijo1=hijo1.ordinalNormal();
		List<Gen> genesHijo2=hijo2.ordinalNormal();
		
		//vuelco los genes nuevos en los hijos
		for (int i=0;i<lcrom;i++)
		{
			hijo1.genes.set(i, genesHijo1.get((i)).clone());
			hijo2.genes.set(i, genesHijo2.get((i)).clone());
		}
		
		return unReturn;
	}

	private Individuo[] crucePropio (Individuo padre1, Individuo padre2, int punto_cruce, int x_min, int x_max) {
		System.out.println("======== crucePropio ========");
		Integer lcrom = padre1.damelCrom();
		System.out.println("lcrom: "+lcrom);
		Individuo hijo1 = padre1.newInstance(x_min, x_max);
		Individuo hijo2 = padre2.newInstance(x_min, x_max);
		hijo1.borraGenes(); //genes a -1
		hijo2.borraGenes(); //genes a -1
		Individuo[] unReturn = {hijo1,hijo2};

		String padre1S = "{";
		for (int i=0;i<lcrom;i++) {
			padre1S += padre1.genes.get(i).bit+", ";
		}
		padre1S += "}";
		System.out.println("padre1 => "+padre1S);

		String padre2S = "{";
		for (int i=0;i<lcrom;i++) {
			padre2S += padre2.genes.get(i).bit+", ";
		}
		padre2S += "}";
		System.out.println("padre2 => "+padre2S);

		//GUARDAMOS LAS PAREJAS
		List<Object[]> parejas = new ArrayList<Object[]>();
		for(int i = 0;i < lcrom;i++) {
			Integer[] pareja = {(Integer) padre2.genes.get(i).bit,(Integer) padre1.genes.get(i).bit};
			parejas.add(pareja);
		}

		for(int i = 0;i < parejas.size();i++) {
			//System.out.println("pareja: {"+parejas.get(i)[0]+", "+parejas.get(i)[1]+"}");
		}

		int max = 0;

		if( lcrom%2 == 0 ) {
			max = lcrom/2;
		} else {
			max = (lcrom-1)/2;
		}

		System.out.println("max: "+max);

		// == HIJO 1 ==
		for(int i=0;i<max;i++) {
			hijo1.genes.get(i).bit = (Integer) padre2.genes.get(i).bit;
		}

		for(int j=max;j<lcrom;j++) {
			//BUSCAMOS LA PAREJA
			int actualPareja = 0;
			for(int k=0;k<parejas.size();k++) {
				if((Integer)parejas.get(k)[0] == (Integer) padre1.genes.get(j).bit || parejas.get(k)[0].equals(padre1.genes.get(j).bit)) {
					actualPareja = (Integer)parejas.get(k)[1];
					break;
				}
			}
			//RECORREMOS EL HIJO
			boolean esta = false;
			for(int k = 0;k<hijo1.genes.size();k++) {
				if((Integer)hijo1.genes.get(k).bit == actualPareja){
					esta = true;
					break;
				}
			}
			//A�ADIMOS AL HIJO
			if(!esta) {
				hijo1.genes.get(j).bit = actualPareja;
			}
		}

		//RELLENAMOS LOS VACIOS
		for(int i=max;i<lcrom;i++) {
			boolean esta = false;
			for(int k=0;k<hijo1.genes.size();k++) {
				if((Integer)padre2.genes.get(i).bit == hijo1.genes.get(k).bit || hijo1.genes.get(k).bit.equals(padre2.genes.get(i).bit)) {
					esta = true;
					break;
				}
			}
			if(!esta) {
				for(int m=max;m<lcrom;m++) {
					if((Integer)hijo1.genes.get(m).bit == (-1) || hijo1.genes.get(m).bit.equals(-1)) {
						hijo1.genes.get(m).bit = (Integer)padre2.genes.get(i).bit;
						break;
					}
				}
			}
		}

		String hijo1S = "{";
		for (int i=0;i<lcrom;i++) {
			hijo1S += hijo1.genes.get(i).bit+", ";
		}
		hijo1S += "}";
		System.out.println("hijo1 => "+hijo1S);

		// == HIJO 2 ==
		for(int i=0;i<max;i++) {
			hijo2.genes.get(i).bit = (Integer) padre1.genes.get(i).bit;
		}

		for(int j=max;j<lcrom;j++) {
			//BUSCAMOS LA PAREJA
			int actualPareja = 0;
			for(int k=0;k<parejas.size();k++) {
				if((Integer)parejas.get(k)[1] == (Integer) padre2.genes.get(j).bit || parejas.get(k)[1].equals(padre2.genes.get(j).bit)) {
					actualPareja = (Integer)parejas.get(k)[0];
					break;
				}
			}
			//RECORREMOS EL HIJO
			boolean esta = false;
			for(int k = 0;k<hijo2.genes.size();k++) {
				if((Integer)hijo2.genes.get(k).bit == actualPareja){
					esta = true;
					break;
				}
			}
			//A�ADIMOS AL HIJO
			if(!esta) {
				hijo2.genes.get(j).bit = actualPareja;
			}
		}

		//RELLENAMOS LOS VACIOS
		for(int i=max;i<lcrom;i++) {
			boolean esta = false;
			for(int k=0;k<hijo2.genes.size();k++) {
				if((Integer)padre1.genes.get(i).bit == hijo2.genes.get(k).bit || hijo2.genes.get(k).bit.equals(padre1.genes.get(i).bit)) {
					esta = true;
					break;
				}
			}
			if(!esta) {
				for(int m=max;m<lcrom;m++) {
					if((Integer)hijo2.genes.get(m).bit == (-1) || hijo2.genes.get(m).bit.equals(-1)) {
						hijo2.genes.get(m).bit = (Integer)padre1.genes.get(i).bit;
						break;
					}
				}
			}
		}

		String hijo2S = "{";
		for (int i=0;i<lcrom;i++) {
			hijo2S += hijo2.genes.get(i).bit+", ";
		}
		hijo2S += "}";
		System.out.println("hijo2 => "+hijo2S);

		return unReturn;
	}

	// =========================
	// == TIPOS DE MUTACION
	// =========================

	private void mutacionInsercion(int ind) {
		Individuo unIndividuo = individuos.get(ind);
		int lcrom = unIndividuo.lcrom;
		int numeroInserciones = Calculadora.dameRandom(1, lcrom-1);
		int unIndice = 0;
		int otroIndice = 0;
		Gen unGen = null;
		List<Gen> listaAuxiliar = new ArrayList<Gen>();
		for (int i=0;i<numeroInserciones;i++) {
			unIndice = Calculadora.dameRandom(0,lcrom-1);
			if(unIndice+1 == lcrom) {
				otroIndice = Calculadora.dameRandom(unIndice,lcrom-1);
			} else {
				otroIndice = Calculadora.dameRandom(unIndice+1,lcrom-1);
			}
			//GUARDO COPIA DE ELEMENTO QUE VOY A MACHACAR
			unGen = unIndividuo.genes.get(otroIndice);
			//ENCADENO DOS LISTAS
			for (int k=0;k<=unIndice;k++) {
				if (k != unIndice)
					listaAuxiliar.add(unIndividuo.genes.get(k));
				else
					listaAuxiliar.add(unIndividuo.genes.get(otroIndice));
			}
		    for (int k=unIndice;k<lcrom;k++) {
		    	if (k != otroIndice)
		    		listaAuxiliar.add(unIndividuo.genes.get(k));

		    }
		    //COPIO LA LISTA ENCADENADA EN INDIVIDUO
		    for (int k=0;k<lcrom;k++) {
		    	unIndividuo.genes.set(k, listaAuxiliar.get(k));
		    }
		    unIndividuo.decod();
		}
	}

	private void mutacionIntercambio(int i) {
		Individuo unIndividuo=individuos.get(i);
		int lcrom = unIndividuo.lcrom;
		int a = Calculadora.dameRandom(0, lcrom-1);
		int b = Calculadora.dameRandom(0, lcrom-1);

		while (a==b)
			b = Calculadora.dameRandom(0, lcrom-1);

		swapGen(unIndividuo.genes,a,b);
		
		unIndividuo.decod();
	}

	private void mutacionInversion(int ind) {
		Individuo unIndividuo = individuos.get(ind);
		int lcrom = unIndividuo.lcrom;
		int unIndice = 0;
		int otroIndice = 0;
		unIndice = Calculadora.dameRandom(0,lcrom-1);
		if(unIndice == lcrom-1) {
			otroIndice = Calculadora.dameRandom(unIndice,lcrom-1);
		} else {
			otroIndice = Calculadora.dameRandom(unIndice+1,lcrom-1);
		}
		boolean esPar = ((otroIndice-unIndice+1) %2 ==0);
		int dist = ((otroIndice-unIndice+1)/2);
		if (!esPar) dist++;

		for (int i=0;i<dist;i++) {
			this.swapGen(unIndividuo.genes, unIndice+i, otroIndice-i);
		}
		unIndividuo.decod();
	}

	private static int getRandom(Individuo unIndividuo) {
		int limit = unIndividuo.lcrom-1;
	    int rnd = (int)(Math.random()*(limit-0+1)+0);
	    return rnd;
	    //return (Integer) unIndividuo.genes.get(rnd).bit;
	}

	private static int getFactorial (int n) {
		int result;
		if(n==1 || n==0) {
			return 1;
		}
		result = getFactorial(n-1)*n;
		return result;
	}

	private static String[] permutar(String cadena,int p) {
		String[] per = new String[p];
		int l = cadena.length();
		int d = p/l;
		String[] aux = permutacion(cadena);
		int pos = 0;

		if(p==1||l==1) {
			per[0] = cadena;
			return per;
		}

		for(int i=0;i<aux.length;i++) {
			String[] auxiliar = permutar(aux[i].substring(1),getFactorial(l-1));
			for(int j=0;j<auxiliar.length;j++) {
				per[pos]=aux[i].charAt(0)+auxiliar[j];
				pos++;
			}
		}
		return per;
	}

	private static String[] permutacion(String cadena) {
		int n = cadena.length();
		String temporal = "";
		String[] vector = new String[n];
		vector[0] = cadena;
		for(int i=1;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(j==n-1) {
					temporal = cadena.charAt(j)+temporal;
				} else {
					temporal += cadena.charAt(j);
				}
			}
			cadena = temporal;
			vector[i] = temporal;
			temporal = "";
		}
		return vector;
	}

	private static void mostrar (List<Integer> permutados) {
		for(int i= 0; i< permutados.size();i++) {
			System.out.println(permutados.get(i));
		}
	}

	private static void mostrarS (List<String> permutados) {
		for(int i= 0; i< permutados.size();i++) {
			System.out.println(permutados.get(i));
		}
	}

	private static void mostrar (String[] permutadosAux) {
		for(int i= 0; i< permutadosAux.length;i++) {
			System.out.println(permutadosAux[i]);
		}
	}

	private static void mostrar (Individuo unIndividuo) {
		String hijo1S = "{";
		for (int i=0;i<unIndividuo.lcrom;i++) {
			hijo1S += unIndividuo.genes.get(i).bit+", ";
		}
		hijo1S += "}";
		System.out.println("hijo1 => "+hijo1S);
	}

	List<List<Integer>> permutaDaton(List<Integer> unArray)
	{
		List<List<Integer>> unaLista=new ArrayList();
		Iterator<List<Integer>> unIterador= new PermutationIterator (unArray);
		while (unIterador.hasNext())
		{
			unaLista.add(unIterador.next());
		}
		
		return unaLista;
	}
	
	//reorganizo genes tal como me dice el indice
	private void heurAux (Individuo unIndividuo,Individuo otroIndividuo,List<Integer> original,List<Integer> mutado)
	{		
		for (int i=0;i<mutado.size();i++)
		{
			otroIndividuo.genes.set(mutado.get(i), unIndividuo.genes.get(original.get(i)));
		}
	}
	
	//usa indices... no entra a ver lo que contienen los genes
	//así es más generico y más sencillo
	private void mutacionHeuristicaDaton(int ind)
	{
		//puntero al individuo en cuestión
		Individuo unIndividuo=individuos.get(ind);
		int lcrom=unIndividuo.lcrom;
		
		//genero n posiciones aleatorias
		int n=3;
		List<Integer> pos=new ArrayList();
		for (int i=0;i<n;i++)
		{
			boolean enc=true;
			int num=0;
			while (enc)
			{
				num=Calculadora.dameRandom(0, lcrom-1);
				enc=pos.contains(num);
			}
			pos.add(num);
		}
		
		List<List<Integer>> listaPermutaciones=permutaDaton(pos);
		//ahora que tengo la lista de permutaciones, genero los posibles individuos
		List<Individuo> unaListaIndividuos=new ArrayList();
		Individuo otroIndividuo=null;
		//la primera permutación de todas, es la original... que no la toco
		unaListaIndividuos.add(unIndividuo);
		int posMejor=0;
		for (int i=1;i<listaPermutaciones.size();i++)
		{
			otroIndividuo=unIndividuo.clone();
			heurAux(unIndividuo,otroIndividuo,pos,listaPermutaciones.get(i));
			otroIndividuo.decod();
			otroIndividuo.calculaadaptacion_bruta();
			//si la adaptación bruta es más optimizada, me guardo el individuo como el mejor
			if (otroIndividuo.getadaptacion_bruta()<unaListaIndividuos.get(posMejor).getadaptacion_bruta())
				posMejor=i;
			unaListaIndividuos.add(otroIndividuo);
		}
		
		//ya sé cuál es el mejor indviduo de las permutaciones .... me lo guardo en ind
		if (posMejor>0)
			individuos.set(ind, unaListaIndividuos.get(posMejor));		
	}
	
	private void mutacionHeuristica(int i) {
		System.out.println(" ======= MUTACION HEURISTICA ===========");
		Individuo unIndividuo = individuos.get(i);
		int lcrom = unIndividuo.lcrom;
		int n = 3;

		String individuo = "{";
		for (int j=0;j<lcrom;j++) {
			individuo += unIndividuo.genes.get(j).bit+", ";
		}
		individuo += "}";
		System.out.println("Individuo => "+individuo);

		List<Integer> arrayEnteros = new ArrayList<Integer>();
		List<Integer> arrayPosiciones = new ArrayList<Integer>();

		//OBTENEMOS ELEMENTOS AL AZAR
		for(int j=0;j<n;j++) {
			int posicion = Calculadora.dameRandom(0, lcrom-1);
			int numero = (Integer) unIndividuo.genes.get(posicion).bit;
			boolean esta = false;
			for(int l=0;l<arrayEnteros.size();l++) {
				if(arrayEnteros.get(l) == numero) {
					esta = true;
					break;
				}
			}
			if(!esta) {
				arrayEnteros.add(numero);
				arrayPosiciones.add(posicion);
			} else {
				j--;
			}
		}

		//ORDENAMOS LAS POSICIONES
		Collections.sort(arrayPosiciones);

		//PASAMOS A STRING
		String permutar = "";
		for(int j=0;j<arrayEnteros.size();j++) {
			System.out.println("arrayEntero(j): "+arrayEnteros.get(j) + ", posicion(j): "+arrayPosiciones.get(j));
			permutar += arrayEnteros.get(j).toString();
		}
		System.out.println("p: "+permutar);

		//PERMUTAMOS
		int pr = getFactorial(permutar.length());
		String[] permutados = permutar(permutar,pr);

		System.out.println("PERMUTADOS String");
		mostrar(permutados);

		//CREAMOS LOS HIJOS MUTADOS
		List<Individuo> MutadosHeuristica = new ArrayList<Individuo>();
		for(int k=0;k<permutados.length;k++) {
			//SEPARAMOS
			String actual = permutados[k];
			char[] aCaracteres = actual.toCharArray();
			List<String> permutado = new ArrayList<String>();
			for (int x=0;x<aCaracteres.length;x++) {
				permutado.add(String.valueOf(aCaracteres[x]));
			}
			//CREAMOS UN INDIVIDUO
			Individuo unIndividuoAux = individuos.get(i).clone();
			unIndividuoAux.borraGenes();
			for (int m=0;m<lcrom;m++) {
				unIndividuoAux.genes.set(m, individuos.get(i).genes.get(m).clone());
			}
			for(int j=0;j<n;j++) {
				unIndividuoAux.genes.get(arrayPosiciones.get(j)).bit = Integer.parseInt(permutado.get(j));
			}
			//A�ADIMOS
			MutadosHeuristica.add(unIndividuoAux);
		}

		System.out.println("HIJOS PERMUTADOS");
		for(int p=0;p<MutadosHeuristica.size();p++){
			mostrar(MutadosHeuristica.get(p));
		}

		//CALCULAMOS LA ADAPTACION DE TODOS
		List<Double> adaptaciones = new ArrayList<Double>();
		for(int h=0;h<MutadosHeuristica.size();h++) {
			MutadosHeuristica.get(h).decod();
			MutadosHeuristica.get(h).adaptacion_bruta = MutadosHeuristica.get(h).calculaadaptacion_bruta();
			adaptaciones.add(MutadosHeuristica.get(h).adaptacion_bruta);
			System.out.println("Adaptacion: "+MutadosHeuristica.get(h).adaptacion_bruta);
		}

		//ORDENAMOS POR ADAPTACION
		Collections.sort(adaptaciones);
		double Mejor = adaptaciones.get(adaptaciones.size()-1);

		//OBTENEMOS EL MEJOR
		for(int h=0;h<MutadosHeuristica.size();h++) {
			if(MutadosHeuristica.get(h).adaptacion_bruta == Mejor) {
				for (int k=0;k<lcrom;k++) {
			    	unIndividuo.genes.set(k, MutadosHeuristica.get(h).genes.get(k));
			    }
			    unIndividuo.decod();
			    break;
			}
		}
		System.out.println("PERMUTADO MEJOR");
		mostrar( unIndividuo );
	}

	//mutación propia: inversión y luego inserción
	private void mutacionPropia(int ind) {
		Individuo unIndividuo = individuos.get(ind);
		int lcrom = unIndividuo.lcrom;
		int numeroInserciones = Calculadora.dameRandom(1, lcrom-1);
		int unIndice = 0;
		int otroIndice = 0;
		Gen unGen = null;
		List<Gen> listaAuxiliar = new ArrayList<Gen>();
		for (int i=0;i<numeroInserciones;i++) {
			unIndice = Calculadora.dameRandom(0,lcrom-1);
			if(unIndice+1 == lcrom) {
				otroIndice = Calculadora.dameRandom(unIndice,lcrom-1);
			} else {
				otroIndice = Calculadora.dameRandom(unIndice+1,lcrom-1);
			}
			//GUARDO COPIA DE ELEMENTO QUE VOY A MACHACAR
			unGen = unIndividuo.genes.get(otroIndice);
			//ENCADENO DOS LISTAS
			for (int k=0;k<=unIndice;k++) {
				if (k != unIndice)
					listaAuxiliar.add(unIndividuo.genes.get(k));
				else
					listaAuxiliar.add(unIndividuo.genes.get(otroIndice));
			}
		    for (int k=unIndice;k<lcrom;k++) {
		    	if (k != otroIndice)
		    		listaAuxiliar.add(unIndividuo.genes.get(k));

		    }
		    //COPIO LA LISTA ENCADENADA EN INDIVIDUO
		    for (int k=0;k<lcrom;k++) {
		    	unIndividuo.genes.set(k, listaAuxiliar.get(k));
		    }
		    unIndividuo.decod();
		}
		    //inversión
		    lcrom = unIndividuo.lcrom;
			unIndice = 0;
			otroIndice = 0;
			unIndice = Calculadora.dameRandom(0,lcrom-1);
			if(unIndice == lcrom-1) {
				otroIndice = Calculadora.dameRandom(unIndice,lcrom-1);
			} else {
				otroIndice = Calculadora.dameRandom(unIndice+1,lcrom-1);
			}
			boolean esPar = ((otroIndice-unIndice+1) %2 ==0);
			int dist = ((otroIndice-unIndice+1)/2);
			if (!esPar) dist++;

			for (int i=0;i<dist;i++) {
				this.swapGen(unIndividuo.genes, unIndice+i, otroIndice-i);
			}
			unIndividuo.decod();
		
		
		
	}







	public void pintarPoblacion() {
		System.out.println("==============================");
		System.out.println("---- pintarPoblacion -->      ");
		for(int i=0; i<tam_pob; i++) {
			System.out.println("Individuo:" +i);
			for(int j=0;j<individuos.get(i).genes.size();j++) {
				System.out.print(individuos.get(i).genes.get(j).bit);
			}
			System.out.println("");
			System.out.println("Aptitud: "+individuos.get(i).getadaptacion());
			System.out.println("");
		}
	}

}
