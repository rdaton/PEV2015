package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Poblacion<T extends Individuo> {	
	private List<Individuo> individuos;
	int pos_mejor;
	int pos_peor;
	double sumadap;//adaptación global de la población	
	double prec;
	int lcrom;
	int torneo;
	int tam_elite;
	boolean[] elite;
	int[] sel_elite;
	boolean elitismo;
	public List dameIndividuos ()
	{
		return individuos;
	}
	
	public Individuo dameMejor()
	{	
		return individuos.get(pos_mejor);
	}
	
	public Individuo damePeor()
	{
		return individuos.get(pos_peor);
	}
	
	public int size ()
	{
		return individuos.size();
	}
	
	public Poblacion(int tam_pob,double x_min,double x_max,double prec,boolean elitismo)
	{
		init();
		this.prec=prec;
		lcrom=logica.Calculadora.tamGen(x_min,x_max, prec);
		Individuo unIndividuo=new Individuo_basico(x_min,x_max,prec);
		for (int i=0;i<tam_pob;i++)
		{
			unIndividuo=((T)unIndividuo).clone_aux(x_min,x_max,prec);
			individuos.add(unIndividuo);
		}			
		torneo=3;
		this.elitismo=elitismo;
		tam_elite=this.size()*2/100;
		elite=new boolean[this.size()];
		for (int i=0;i<this.size();i++)
			{
			elite[i]=false;
			};
		
	}
	void init()
	{			
		individuos=new ArrayList();
		pos_mejor=0;
		pos_peor=0;
		sumadap=0;
	}
	
	public void evaluacion()
	{		
		sumadap=0; //suma de la adaptacion
		double punt_acu=0; //puntuacion acumulada de los individuos
		Individuo pIndividuo=null;
		Iterator<Individuo> unIterador=individuos.iterator();
		int i=0;
		while (unIterador.hasNext())		
		{
			pIndividuo=unIterador.next();			
			sumadap+=pIndividuo.getadaptacion();
			if ((i==0) || pIndividuo.getadaptacion_bruta()>individuos.get(pos_mejor).getadaptacion_bruta())
				{
				pos_mejor=i;
				}
			
			if ((i==0) || pIndividuo.getadaptacion_bruta()<individuos.get(pos_peor).getadaptacion_bruta())
				{
				pos_peor=i;
				}
			
			i++;
		}		
		unIterador=individuos.iterator();
		while (unIterador.hasNext())
		{
			pIndividuo=unIterador.next();
			pIndividuo.setPuntuacion((double)pIndividuo.getadaptacion()/(double)sumadap);
			punt_acu+=pIndividuo.getPuntuacion();
			pIndividuo.setPunt_acu(punt_acu);
		}
	}

	public void desplazar()
	{
		
		//margen para evitar sumadaptacion = 0 si converte la población
		double maximo=individuos.get(pos_mejor).getadaptacion_bruta()*1.05;
		
		Iterator<Individuo>unIterador=individuos.iterator();
		Individuo pIndividuo=null;	
		while (unIterador.hasNext())		
		{
			pIndividuo=unIterador.next();
			pIndividuo.setadaptacion(maximo-pIndividuo.getadaptacion_bruta());		
		}
	}
	
	
	//muestreo estocastico universal
	public void seleccionEstadistico() {
		int[] sel_super= new int[this.size()]; //seleccionados para sobrevivir
		double a = new Random().nextDouble() * (((double) 1) / this.size());
		double prob; //probabilidad de seleccion
		int pos_super; //posicion del superviviente
		List<Individuo> pob_aux=new ArrayList(); //poblacion auxiliar
		Individuo pIndividuo=null;
		Iterator<Individuo> unIterador=individuos.iterator();
		int j=0;
		while (unIterador.hasNext())
		{
			unIterador.next();	
			
			if (this.elite[j])
				sel_super[j]=j;
			else
				{
				prob=(a + j -1)/this.size();
				pos_super=0;
				while((pos_super<this.size()) && (prob>individuos.get(pos_super).getPunt_acu())  )
				{
					pos_super++;	
					if (pos_super==this.size()) break;
				}
				if (pos_super==this.size())
					pos_super--;
				sel_super[j]=pos_super;
				}
			j++;
		}
		//se genera la poblacion intermedia
		for (int i=0;i<this.size();i++)
		{
			pob_aux.add(individuos.get(sel_super[i]).clone());
		}
		//machaco población anterior y la reemplazo con la de supervivientes
		init();
		unIterador=pob_aux.iterator();
		while (unIterador.hasNext())
		{
			individuos.add(unIterador.next().clone());
		}
				
	}
	

	public void seleccionTorneo()
	{
		double aptitud_mejor = 0;
		int[] sel_super= new int[this.size()]; //seleccionados para sobrevivir
		int  prob; //probabilidad de seleccion
		int pos_super=0; //posicion del superviviente
		List<Individuo> pob_aux=new ArrayList(); //poblacion auxiliar
		Individuo pIndividuo=null;
		Iterator<Individuo> unIterador=individuos.iterator();
		
		for(int i=0;i<this.size();i++) {
			if (this.elite[i])
				sel_super[i]=i;
			else
				{
				for(int j=0;j<this.torneo;j++) {
				prob = new Random().nextInt(this.size());
				if(j == 0) {
					aptitud_mejor = individuos.get(prob).getadaptacion();
					pos_super = prob;
					} 
				else 
					if(aptitud_mejor < individuos.get(prob).getadaptacion()) {
						aptitud_mejor = individuos.get(prob).getadaptacion();
						pos_super = prob;
						}
				}
				sel_super[i] = pos_super;
				}
		}
		
		//se genera la poblacion intermedia
		for (int i=0;i<this.size();i++)
		{
			pob_aux.add(individuos.get(sel_super[i]).clone());
		}
		//machaco población anterior y la reemplazo con la de supervivientes
		init();
		unIterador=pob_aux.iterator();
		while (unIterador.hasNext())
		{
			individuos.add(unIterador.next().clone());
		}
				
				
			
	}
	public void seleccionRuleta()
	{
		int[] sel_super= new int[this.size()]; //seleccionados para sobrevivir
		double prob; //probabilidad de seleccion
		int pos_super; //posicion del superviviente
		List<Individuo> pob_aux=new ArrayList(); //poblacion auxiliar
		Individuo pIndividuo=null;
		Iterator<Individuo> unIterador=individuos.iterator();
		int j=0;
		while (unIterador.hasNext())
		{			
			unIterador.next();
			if (this.elite[j])
				sel_super[j]=j;
			else{
				prob=Math.random();
				pos_super=0;
				while((pos_super<this.size()) && (prob>individuos.get(pos_super).getPunt_acu())  )
				{
					pos_super++;	
					if (pos_super==this.size()) break;
				}
				if (pos_super==this.size())
					pos_super--;
				sel_super[j]=pos_super;
				}
			j++;
		}
		//se genera la poblacion intermedia
		for (int i=0;i<this.size();i++)
		{
			pob_aux.add(individuos.get(sel_super[i]).clone());
		}
		//machaco población anterior y la reemplazo con la de supervivientes
		init();
		unIterador=pob_aux.iterator();
		while (unIterador.hasNext())
		{
			individuos.add(unIterador.next().clone());
		}
				
				
			
	}
	
	public void reproduccion (double prob_cruce,double x_min, double x_max, int tCruce) {
		double sel_cruce[]= new double [this.size()];// seleccionados para reproducirse
		int num_sel_cruce=0;//contador de seleccionados
		double prob;
		int puntos_cruce[]= new int[4];
		Individuo hijo1, hijo2;
		//se eligen los individuos a cruzar
		for (int i=0;i<this.size();i++)
		{
			//se generan tam_pob números aletatorios 1, en [0,1]
			prob=Math.random();
			//se eligen los individuos de las posiciones i
			//con ai< prob_cruce
			if (prob<prob_cruce){
				sel_cruce[num_sel_cruce]=i;
				num_sel_cruce++;
			}
		}
		//el número de seleccionados se hace par
		if ((num_sel_cruce % 2)==1)
			num_sel_cruce--;
		//se cruzan los individuos elegidos en un punto al azar
		puntos_cruce[0] = 0 + (int)(Math.random() * ((lcrom-0) + 1));
		Individuo[] unReturn= new Individuo[2];
		for (int i=0;i<num_sel_cruce;i+=2)
		{
			switch (tCruce)
			{
			case 0:
				unReturn=cruce(individuos.get(i),individuos.get(i+1),puntos_cruce, x_min, x_max);
				break;
			case 1:
				unReturn=cruce(individuos.get(i),individuos.get(i+1),puntos_cruce, x_min, x_max);
			}
			hijo1=unReturn[0];
			hijo2=unReturn[1];
			//los nuevos individuos sutituyen a sus progenitores,respetando la elite
			if (hijo1.getadaptacion()>individuos.get(i).getadaptacion() && !this.elite[i])
				individuos.set(i,hijo1);
			
			if (hijo1.getadaptacion()>individuos.get(i+1).getadaptacion() && !this.elite[i+1])
				individuos.set(i+1,hijo2);
		}
		
	}
	
	Individuo[] cruce (Individuo padre1, Individuo padre2, int[] punto_cruce,double x_min, double x_max)
	{
		Individuo hijo1=new Individuo_basico(x_min,x_max,prec);
		hijo1=((T)hijo1).clone_aux(x_min,x_max,prec);
		Individuo hijo2=new Individuo_basico(x_min,x_max,prec);
		hijo1=((T)hijo2).clone_aux(x_min,x_max,prec);
		Individuo[] unReturn={hijo1,hijo2};
		//primera parte del intercambio: 1 a 1 y 2 a 2
		for (int i=0;i<punto_cruce[0];i++)
		{
			hijo1.genes.set(i, padre1.genes.get(i));
			hijo2.genes.set(i, padre2.genes.get(i));
		}
		
		//segunda parte: 1 a 2 y 2 a 1
		for (int i=punto_cruce[0];i<lcrom;i++)
		{
			hijo1.genes.set(i, padre2.genes.get(i).clone());
			hijo2.genes.set(i, padre1.genes.get(i).clone());
		}
		//se evalúan
		hijo1.adaptacion_bruta=hijo1.calculaadaptacion_bruta();
		hijo2.adaptacion_bruta=hijo2.calculaadaptacion_bruta();		
		return unReturn;
	}
	
	public void mutacion(double prob_mut)
	{
		boolean mutado;
		double prob;
		for (int i=0;i<this.size();i++)
		{
			mutado=false;
			for (int j=0;j<lcrom;j++)
			{
				//se genera un numero aleatorio en [0 1)
				prob=Math.random();
				//se mutan aquellos genes con prob < que prob_mut
				if (prob<prob_mut)
				{
					individuos.get(i).genes.get(j).muta();
					mutado=true;
				}				
			}
			if (mutado)
				individuos.get(i).adaptacion_bruta=individuos.get(i).calculaadaptacion_bruta();				
		}
	}
	
	public void generarElite()
	{
		if (!elitismo) return;
		
		sel_elite=new int[this.tam_elite]; //seleccionados como la elite
		elite=new boolean[this.size()];
				
		for (int i=0;i<tam_elite;i++)
		{
			sel_elite[i]=i;			
		}
		ordenar_por_adaptacion();
		
		for (int i=tam_elite;i<this.size();i++)
		{
			int j=0;
			while (j<tam_elite && individuos.get(sel_elite[j]).getadaptacion()>individuos.get(i).getadaptacion())
			{
				j++;
			}
			if (j<tam_elite)
			{
				insertar_ordenadamente (j, i);
			}
		}	
		for (int i=0;i<tam_elite;i++)
		{
			this.elite[sel_elite[i]]=true;
		}
		
	}
	//push
	void insertar_ordenadamente(int pos, int valor)
	{
		for (int i=pos+1;i<this.tam_elite;i++)
		{
			this.sel_elite[i]=sel_elite[i-1];
		};
		
		sel_elite[pos]=valor;
		
	}
	
	//bubble sort (a mejorar)
	void ordenar_por_adaptacion()	
	{
		  int j;
		     boolean flag = true;   // set flag to true to begin first pass
		     int temp;   //holding variable

		     while ( flag )
		     {
		            flag= false;    //set flag to false awaiting a possible swap
		            for( j=0;  j < tam_elite -1;  j++ )
		            {
		                   if ( individuos.get( sel_elite[ j ]) .getadaptacion() < individuos.get( sel_elite[ j+1 ]) .getadaptacion()  )   // change to > for ascending sort
		                   {
		                           temp = sel_elite[ j ];                //swap elements
		                           sel_elite[ j ] = sel_elite[ j+1 ];
		                           sel_elite[ j+1 ] = temp;
		                           flag = true;              //shows a swap occurred 
		                  }
		            }
		      } 
	}
	
}
