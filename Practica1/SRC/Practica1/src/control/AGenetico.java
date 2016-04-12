package control;

import java.util.ArrayList;
import java.util.List;

public class AGenetico <T extends individuo.Individuo> {
	double x_min;
	double x_max;
	individuo.Poblacion pob; //población
	int tam_pob; //tamaño población
	double prec;
	int pos_mejor; // posición del mejor cromosoma
	double sumadaptacion_bruta; //adatpación de toda la  población
	double prob_cruce; //probabilidad de cruce
	double prob_mut; //probabilidad de mutación
	int num_max_gen; //numero de generaciones
	int tCruce; //tipo cruce 
	int tSeleccion; //tipo selección
	List <individuo.Individuo> mejores_cada_generacion;
	List <individuo.Individuo> peores_cada_generacion;
	public AGenetico (int tam_pob, double prec,double prob_cruce, double prob_mut,double x_min,double x_max, int num_max_gen,boolean elitismo, int tSeleccion, int tCruce)
	{		
		mejores_cada_generacion=new ArrayList();
		peores_cada_generacion=new ArrayList();
		this.num_max_gen=num_max_gen;
		this.prec=prec;
		this.x_min=x_min;
		this.x_max=x_max;
		this.prob_cruce=prob_cruce;
		this.prob_mut=prob_mut;
		individuo.Poblacion pob=new individuo.Poblacion<T>(tam_pob,x_min,x_max,prec,elitismo);	
		//bucle de evolución
		
		//prerparo la población para el bucle
		pob.evaluacion();	
		pob.desplazar();
		
		for (int i=0;i<num_max_gen;i++)
		{
			if (elitismo)
				pob.generarElite(); 
			switch (tSeleccion)
			{
			case 0:
				pob.seleccionRuleta();			
				break;
			case 1:
				pob.seleccionEstadistico();
				break;
			case 2:
				pob.seleccionTorneo();		
			
			default:
			{
				pob.seleccionRuleta();
			}			
			}
			
			pob.reproduccion(prob_cruce,x_min,x_max,tCruce);
			pob.mutacion(prob_mut);					
			pob.evaluacion();	
			pob.desplazar();		
			mejores_cada_generacion.add(pob.dameMejor().clone());
			peores_cada_generacion.add(pob.damePeor().clone());
		}
		
	}
	
	public List<individuo.Individuo> dameMejor()
	{
		return mejores_cada_generacion;
	}
	
	public List<individuo.Individuo> damePeor()
	{
		return peores_cada_generacion;
	}
}
