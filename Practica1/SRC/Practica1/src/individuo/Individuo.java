package individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public   class Individuo {
	List <Gen> genes; //genotipos
	double x; //fenotipo
	double adaptacion; //función de evaluación
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

	public void setAdaptacion (double adapt)
	{
		this.adaptacion=adapt;
	}
	
	public Individuo (double x_min, double x_max,double prec)
	{		
		this.prec=prec;
		this.x_min=x_min;
		this.x_max=x_max;	
		lcrom=logica.Calculadora.tamGen(x_min,x_max, prec);
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
		adaptacion=calculaAdaptacion();
	}
	
	public Individuo clone()
	{
		Individuo unIndividuo=new Individuo (this.x_min,this.x_max,prec);
		for (int i=0;i<this.genes.size();i++)
		{
			unIndividuo.genes.set(i, this.genes.get(i).clone());
		}
		unIndividuo.x=this.x;
		unIndividuo.adaptacion=this.adaptacion;
		unIndividuo.puntuacion=this.puntuacion;
		unIndividuo.punt_acu=this.punt_acu;
		return unIndividuo;
	}
	
	
	public double getAdaptacion() {
		return adaptacion;
	}	
	
	
	double calculaAdaptacion()
	{
		decod();
		return miFuncion(x);
	}
	
	//reflejada
	double miFuncion (double valor)
	{
		double res=0;
		res=Math.sqrt(Math.abs(valor));
		double unSin=Math.sin(res);
		res=valor*unSin;
		res=Math.abs(res);
		return -res;
	}
		
	void  decod()
	{
		x=(double)((double)bin_ent()/(double)(Math.pow(2, lcrom)-1));
		x*=(x_max - x_min);
		x+=x_min;
	}
	
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
	public String toString()
	{
		StringBuffer unBuffer=new StringBuffer();
		
		
		decod();
		unBuffer.append(x).append(';');
		return unBuffer.toString();
	}
		
	
	
	
}
