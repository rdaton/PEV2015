package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Poblacion {	
	private List<Individuo> individuos;
	int pos_mejor;	
	double sumadap;//adaptación global de la población	
	double prec;
	int lcrom;
	
	public List dameIndividuos ()
	{
		return individuos;
	}
	
	public Individuo dameMejor()
	{		
		return individuos.get(pos_mejor);
	}
	public int size ()
	{
		return individuos.size();
	}
	
	public Poblacion(int tam_pob,double x_min,double x_max,double prec)
	{
		init();
		this.prec=prec;
		lcrom=logica.Calculadora.tamGen(x_min,x_max, prec);
		Individuo unIndividuo=null;
		for (int i=0;i<tam_pob;i++)
		{
			unIndividuo=new Individuo(x_min,x_max,prec);
			individuos.add(unIndividuo);
		}
	}
	void init()
	{
		individuos=new ArrayList();
		pos_mejor=0;
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
			sumadap+=pIndividuo.getAdaptacion();
			if ((i==0) || pIndividuo.getAdaptacion()>individuos.get(pos_mejor).getAdaptacion())
			{
				pos_mejor=i;
			}
			i++;
		}
		desplazar();
		unIterador=individuos.iterator();
		while (unIterador.hasNext())
		{
			pIndividuo=unIterador.next();
			pIndividuo.setPuntuacion((double)pIndividuo.getAdaptacion()/(double)sumadap);
			punt_acu+=pIndividuo.getPuntuacion();
			pIndividuo.setPunt_acu(punt_acu);
		}
	}

	void desplazar()
	{
		
		//margen para evitar sumadaptacion = 0 si converte la población
		double maximo=individuos.get(pos_mejor).getAdaptacion();		  
		Iterator<Individuo>unIterador=individuos.iterator();
		Individuo pIndividuo=null;	
		while (unIterador.hasNext())		
		{
			pIndividuo=unIterador.next();
			pIndividuo.setAdaptacion(maximo-pIndividuo.getAdaptacion());		
		}
		
		
		
	}
	
	
	private void seleccionEstadistico() {
		/*int[] sel_super = new int[this.size()]; //seleccionados para sobrevivir
		double a = new Random().nextDouble() * (((double) 1) / this.size());
		int pos_super;
		double prob;


		System.out.println("");
		System.out.println("-- Estadistico --");
		System.out.println("a: "+a);
		
		for(int i=1;i<=this.size();i++) {
			prob = (a + i -1)/this.size();
			//System.out.println("Prob "+i+": "+prob);
			pos_super = 0;
			while ( (pos_super < this.size()) && (prob > (prob>individuos.get(pos_super).getPunt_acu())  ) {
				pos_super++;
			}
			sel_super[i-1] = pos_super;
			//System.out.println("sel_super["+(i-1)+"]: "+sel_super[i-1]);
		}*/
		
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
			//pIndividuo=unIterador.next();
			unIterador.next();	
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
			//pIndividuo=unIterador.next();
			unIterador.next();	
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
	
	public void reproduccion (double prob_cruce,double x_min, double x_max) {
		double sel_cruce[]= new double [this.size()];// seleccionados para reproducirse
		int num_sel_cruce=0;//contador de sleccionados
		double prob;
		int punto_cruce;
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
		punto_cruce = 0 + (int)(Math.random() * ((lcrom-0) + 1));
		for (int i=0;i<num_sel_cruce;i+=2)
		{
			Individuo[] unReturn=cruce(individuos.get(i),individuos.get(i+1),punto_cruce, x_min, x_max);
			hijo1=unReturn[0];
			hijo2=unReturn[1];
			//los nuevos individuos sutituyen a sus progenitores
			individuos.set(i,hijo1);
			individuos.set(i+1,hijo1);
		}
		
	}
	
	Individuo[] cruce (Individuo padre1, Individuo padre2, int punto_cruce,double x_min, double x_max)
	{
		Individuo hijo1=new Individuo(x_min,x_max,prec);
		Individuo hijo2=new Individuo(x_min,x_max,prec);
		Individuo[] unReturn={hijo1,hijo2};
		//primera parte del intercambio: 1 a 1 y 2 a 2
		for (int i=0;i<punto_cruce;i++)
		{
			hijo1.genes.set(i, padre1.genes.get(i));
			hijo2.genes.set(i, padre2.genes.get(i));
		}
		
		//segunda parte: 1 a 2 y 2 a 1
		for (int i=punto_cruce;i<lcrom;i++)
		{
			hijo1.genes.set(i, padre2.genes.get(i).clone());
			hijo2.genes.set(i, padre1.genes.get(i).clone());
		}
		//se evalúan
		hijo1.adaptacion=hijo1.calculaAdaptacion();
		hijo2.adaptacion=hijo2.calculaAdaptacion();		
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
				individuos.get(i).adaptacion=individuos.get(i).calculaAdaptacion();				
		}
	}
	
	
	
}
