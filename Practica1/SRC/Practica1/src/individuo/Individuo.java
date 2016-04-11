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
	
	public Individuo (double x_min, double x_max,double prec)
	{		
		this.prec=prec;
		this.x_min=x_min;
		this.x_max=x_max;	
		lcrom=logica.Calculadora.tamGen(x_min,x_max, prec);
		
		
	}
	
	abstract public Individuo clone();
	
	
	public double getadaptacion_bruta() {
		return adaptacion_bruta;
	}	
	
	public double getadaptacion() {
		return adaptacion;
	}	
	
	
	abstract double calculaadaptacion_bruta();
	
	
	abstract double miFuncion (double valor);
		
	abstract void  decod();
	
	int bin_ent()
	{
		int d=0;
		int pot=1;
		for (int i=0;i<lcrom;i++)
		{
			d+=pot*genes.get(lcrom-i-1).toInt();
			pot=pot*2;
		}
		return d;
	}
	

	@Override
	abstract public String toString();
	
	
	
	
}
