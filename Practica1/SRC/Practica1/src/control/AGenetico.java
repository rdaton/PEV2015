package control;

import java.util.ArrayList;
import java.util.List;

public class AGenetico {
	double x_min;
	double x_max;
	individuo.Poblacion pob; //población
	int tam_pob; //tamaño población
	double prec;
	int pos_mejor; // posición del mejor cromosoma
	double sumadaptacion; //adatpación de toda la  población
	double prob_cruce; //probabilidad de cruce
	double prob_mut; //probabilidad de mutación
	int num_max_gen; //numero de generaciones
	int tCruce; //tipo cruce 
	int tSeleccion; //tipo selección
	List <individuo.Individuo> individuos_mejores;
	public AGenetico (int tam_pob, double prec,double prob_cruce, double prob_mut,double x_min,double x_max, int num_max_gen)
	{		
		individuos_mejores=new ArrayList();
		this.num_max_gen=num_max_gen;
		this.prec=prec;
		this.x_min=x_min;
		this.x_max=x_max;
		this.prob_cruce=prob_cruce;
		this.prob_mut=prob_mut;
		individuo.Poblacion pob=new individuo.Poblacion(tam_pob,x_min,x_max,prec);
		pob.evaluacion();
		//bucle de evolución
		for (int i=0;i<num_max_gen;i++)
		{
			pob.seleccion();
			pob.reproduccion(prob_cruce,x_min,x_max);
			pob.mutacion(prob_mut);
			pob.evaluacion();		
			individuos_mejores.add(pob.dameMejor().clone());
		}
		
	}
	

	public List<individuo.Individuo> dameMejor()
	{
		return individuos_mejores;
	}
}
