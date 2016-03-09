package individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public   class Individuo {
	List <Gen> genes; //genotipos
	double x; //fenotipo
	double adaptacion; //función de evaluación
	double puntuacion; //punt. relativa: adaptación/sumadaptación
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

	double punt_acu; //puntuación acumulada para sorteos
	
	public Individuo (int lcrom)
	{		
		Gen gen;
		genes=new ArrayList();
		for (int i=0;i<lcrom;i++)
		{
			if (Math.random()<0.5)
				gen=new Gen(0);
			else
				gen=new Gen(1);;
			//fi
			genes.add(gen);
		}
		adaptacion=calculaAdaptacion(lcrom);
	}
	
	public double getAdaptacion() {
		return adaptacion;
	}

	double miFuncion (double valor)
	{
		double res=0;
		res=Math.sqrt(Math.abs(valor));
		res=valor*Math.sin(res);
		res=(-1)*Math.abs(res);
		return res;
	}
	private double calculaAdaptacion(int lcrom)
	{
		decod(lcrom);
		return miFuncion(x);
	}
	
	void  decod(int lcrom)
	{
		x=(double)((double)bin_ent(lcrom)/(double)(Math.pow(2, lcrom)-1));
	}
	
	int bin_ent(int lcrom)
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
	
		
		
	
	
	
}
