package individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  abstract class Individuo {
	List <Gen> genes; //genotipos	
	double adaptacion_bruta; //función de evaluación
	double adaptacion; //adaptación ajustada
	double puntuacion; //punt. relativa: adaptación/sumadaptación
	double punt_acu; //puntuación acumulada para sorteos
	double x_min;
	double x_max;
	double prec;
	int lcrom;
	Object x; //fenotipo
	public double getPuntuacion() {
		return puntuacion;
	}

	
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public double getPunt_acu() {
		return punt_acu;
	}

	public void setPunt_acu(double punt_acu) {
		this.punt_acu = punt_acu;
	}

	public void setadaptacion (double adapt)
	{
		this.adaptacion=adapt;
	}
	public Object getX()
	{
		return  x;
	}
	
	public Individuo (double x_min, double x_max,double prec)
	{		
		this.prec=prec;
		this.x_min=x_min;
		this.x_max=x_max;	
		lcrom=logica.Calculadora.tamGen(x_min,x_max, prec);
		genes=new ArrayList();
		
		
	}
	
	public Individuo clone()
	{
		//única parte que varía
		Individuo unIndividuo=clone_aux(x_min,x_max, prec);
		//fin de parte que varía
		
		for (int i=0;i<this.genes.size();i++)
		{
			unIndividuo.genes.set(i, this.genes.get(i).clone());
		}
		unIndividuo.x=this.x;
		unIndividuo.adaptacion_bruta=this.adaptacion_bruta;
		unIndividuo.adaptacion=this.adaptacion;
		unIndividuo.puntuacion=this.puntuacion;
		unIndividuo.punt_acu=this.punt_acu;
		return unIndividuo;
	}
	
	
	public double getadaptacion_bruta() {
		return adaptacion_bruta;
	}	
	
	public double getadaptacion() {
		return adaptacion;
	}	
	
	abstract int[] bin_ent();	
	
	double calculaadaptacion_bruta()
	{
		decod();
		return miFuncion(x);
	}
	
	
	abstract double miFuncion (Object valor);
		
	abstract void  decod();	
	
	public abstract Individuo clone_aux(double x_min,double x_max, double prec);
	
	@Override
	abstract public String toString();
	
	
	
	
}
