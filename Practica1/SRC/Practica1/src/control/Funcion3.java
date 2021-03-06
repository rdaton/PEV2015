package control;

import individuo.Individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Funcion3 {
	double prec=0;
	int tam_pob=0;
	int num_iter=0;
	double pCruces=0;
	double pMut=0;
	double semilla=0;
	int tCruce=0;
	int tSeleccion=0;
	AGenetico unAlgoritmo;
	final Double[] x_min={Double.valueOf(-3),Double.valueOf(4.1)};
	final Double[] x_max={Double.valueOf(12.1),Double.valueOf(5.8)};
	public Funcion3 (int argf1,int argf2,double prec,int tam_pob, int num_iter,double pCruces,double pMut,
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
		 unAlgoritmo=new AGenetico(argf1,argf2,1,new individuo.Individuo_f3(),tam_pob,prec,pCruces,pMut,x_min,x_max,num_iter,elitismo,tSeleccion,tCruce);
	}
	
	public List<List<Object>> dameResultados()
	{
		Iterator<individuo.Individuo> unIterador=null;
		List<List<Object>> resultados=new ArrayList();;
		List<individuo.Individuo> resultadosMejores= unAlgoritmo.dameMejor();
		resultados.add (new ArrayList()); //mejor absoluto
		resultados.add(new ArrayList()); //mejor de generación
		resultados.add (new ArrayList()); //media de generación
		individuo.Individuo pIndividuo=null;
		
		unIterador=resultadosMejores.iterator();
		double max_adapt=0;
		
		int vueltas=0;
		double pDouble=0;
		Double x;
		Double y;
		while (unIterador.hasNext())
		{
			vueltas++;			
			pIndividuo=unIterador.next();
			pDouble=pIndividuo.getadaptacion_bruta();
			x=((Double[])pIndividuo.getX())[0];
			y=((Double[])pIndividuo.getX())[1];
			if (vueltas==1)
			{
			max_adapt=pDouble;					
			}
			else
				if (pDouble>max_adapt)
					max_adapt=pDouble;
			
			resultados.get(0).add(max_adapt);
			resultados.get(1).add(new Double(pDouble));
			resultados.get(2).add(unAlgoritmo.dameMedias().get(vueltas-1));
			
			System.out.print("x: " +String.format( "%.2f",  x));
			System.out.print(" ; y: "+ String.format( "%.2f", y));
			System.out.print(" ; z: "+ String.format( "%.2f", pDouble));
			System.out.println(" ; mejor "+String.format( "%.2f", max_adapt));			
		}
		
		
		return resultados;
	}
	
	

}
