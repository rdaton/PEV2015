package individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class Individuo_f1 extends Individuo{
	List <Gen> genes; //genotipos
	double x; //fenotipo
	
	public double getPuntuacion() {
		return puntuacion;
	}

	public double getX()
	{
		return x;
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
	
	public Individuo_f1 (double x_min, double x_max,double prec)
	{		
		super(x_min,x_max,prec);
		Gen gen;
		genes=new ArrayList();
		for (int i=0;i<lcrom;i++)
		{
			if (Math.random()<0.5)
				gen=new Gen_f1(0);
			else
				gen=new Gen_f1(1);;
			//fi
			genes.add(gen);
		}
		adaptacion_bruta=calculaadaptacion_bruta();
	}
	
	public Individuo_f1 clone()
	{
		Individuo_f1 unIndividuo=new Individuo_f1 (this.x_min,this.x_max,prec);
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
	
	
	double calculaadaptacion_bruta()
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
		unBuffer.append(x).append(',');
		unBuffer.append(this.adaptacion_bruta).append(',');
		return unBuffer.toString();
	}
		
	
	
	
}
