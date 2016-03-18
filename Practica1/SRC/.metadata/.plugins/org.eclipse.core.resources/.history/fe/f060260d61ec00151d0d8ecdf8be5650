package control;

public class AGenetico {
	double x_min;
	double x_max;
	individuo.Poblacion pob; //población
	int tam_pob; //tamaño población
	int lcrom; //tamaño población (?)
	int pos_mejor; // posición del mejor cromosoma
	double sumadaptacion; //adatpación de toda la  población
	double prob_cruce; //probabilidad de cruce
	double prob_mut; //probabilidad de mutación
	int num_max_gen; //numero de generaciones
	individuo.Individuo individuo_mejor;
	public AGenetico (int tam_pob, int lcrom,double prob_cruce, double prob_mut,double x_min,double x_max)
	{
		this.x_min=x_min;
		this.x_max=x_max;
		this.prob_cruce=prob_cruce;
		this.prob_mut=prob_mut;
		individuo.Poblacion pob=new individuo.Poblacion(tam_pob, lcrom,x_min,x_max);
		pob.evaluacion();
		//bucle de evolución
		for (int i=0;i<num_max_gen;i++)
		{
			pob.seleccion();
			pob.reproduccion(lcrom, prob_cruce,x_min,x_max);
			pob.mutacion(lcrom,prob_mut);
			pob.evaluacion();						
		}
		individuo_mejor=pob.dameMejor().clone();
	}
	

	public individuo.Individuo dameMejor()
	{
		return individuo_mejor;
	}
}
