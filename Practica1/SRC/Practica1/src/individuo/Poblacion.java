package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Poblacion {	
	private List<Individuo> individuos;
	int pos_mejor;
	double sumadap;//adaptación global de la población	
	
	public List dameIndividuos ()
	{
		return individuos;
	}
	
	public int size ()
	{
		return individuos.size();
	}
	
	public Poblacion(int tam_pob, int lcrom)
	{
		individuos=new ArrayList();
		Individuo unIndividuo=null;
		for (int i=0;i<tam_pob;i++)
		{
			unIndividuo=new Individuo(lcrom);
			individuos.add(unIndividuo);
		}
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
			i++;
			sumadap+=pIndividuo.getAdaptacion();
			if (pIndividuo.getAdaptacion()<individuos.get(pos_mejor).getAdaptacion())
			{
				pos_mejor=i;
			}
		}
		
		unIterador=individuos.iterator();
		while (unIterador.hasNext())
		{
			pIndividuo=unIterador.next();
			pIndividuo.setPuntuacion((double)pIndividuo.getAdaptacion()/(double)sumadap);
			punt_acu+=pIndividuo.getPuntuacion();
			pIndividuo.setPunt_acu(punt_acu);
		}
	}

	public void seleccion()
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
			pIndividuo=unIterador.next();
			j++;
			prob=Math.random();
			pos_super=0;
			while((prob>individuos.get(pos_super).getPunt_acu()) && (pos_super<this.size()))
			{
				pos_super++;				
			}
			sel_super[j]=pos_super;
		}
				//se genera la poblacion intermedia
				for (int i=0;i<this.size();i++)
		{
			pob_aux.add(individuos.get(sel_super[i]));
		}
			
	}
	double desplazar (double a, double max)
	{
		return max-a;
	}
}
