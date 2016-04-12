package control;

import individuo.Individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Funcion1 {
	double prec=0;
	int tam_pob=0;
	int num_iter=0;
	double pCruces=0;
	double pMut=0;
	double semilla=0;
	int tCruce=0;
	int tSeleccion=0;
	AGenetico unAlgoritmo;
	final int x_min=-250;
	final int x_max=250;
	public Funcion1 (double prec,int tam_pob, int num_iter,double pCruces,double pMut,
		int tCruce,int tSeleccion,boolean elitismo)
	{
		 this.prec=prec;
		 this.tam_pob=tam_pob;
		 this.num_iter=num_iter;
		 this.pCruces=pCruces;
		 this.pMut=pMut;
		 //this.semilla=semilla;
		 this.tCruce=tCruce;
		 this.tSeleccion=tSeleccion;
		 unAlgoritmo=new AGenetico<individuo.Individuo_basico>(tam_pob,prec,pCruces,pMut,x_min,x_max,num_iter,elitismo,tSeleccion,tCruce);
	}
	
	public List<List<Object>> dameResultados()
	{
		Iterator<individuo.Individuo> unIterador=null;
		List<List<Object>> resultados=new ArrayList();;
		List<individuo.Individuo> resultadosPeores= unAlgoritmo.damePeor();
		resultados.add (new ArrayList()); //menor absoluto
		resultados.add(new ArrayList()); //menor de generación
		resultados.add (new ArrayList()); //media de generación
		individuo.Individuo pIndividuo=null;
		
		unIterador=resultadosPeores.iterator();
		double min_adapt=0;
		
		int vueltas=0;
		double pDouble=0;
		double x=0;
		while (unIterador.hasNext())
		{
			vueltas++;			
			pIndividuo=unIterador.next();
			pDouble=pIndividuo.getadaptacion_bruta();
			x=(Double)pIndividuo.getX();
			if (vueltas==1)
			{
			min_adapt=pDouble;					
			}
			else
				if (pDouble<min_adapt)
					min_adapt=pDouble;
			
			resultados.get(0).add(min_adapt);
			resultados.get(1).add(new Double(pDouble));
			resultados.get(2).add(dameMedia(resultados.get(1)));
			
			System.out.print("x: " +String.format( "%.2f",  x));
			System.out.print(" ; y: "+ String.format( "%.2f", pDouble));
			System.out.println(" ; peor "+String.format( "%.2f", min_adapt));			
		}
		
		
		return resultados;
	}
	
	double dameMedia(List<Object> unaLista)
	{
		double unaMedia=0;
		double total=0;
		Iterator<Object>  unIterador=unaLista.iterator();		
		
		while (unIterador.hasNext())
		{			
			total+=(Double)unIterador.next();
		}		
		unaMedia=(double)total/(double) unaLista.size();
		
		return unaMedia;
	}

}
