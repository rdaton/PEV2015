package individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class Individuo_basico extends Individuo{
	
	
	
	

	public Individuo_basico (double x_min, double x_max,double prec)
	{		
		super(x_min,x_max,prec);
		Gen gen;		
		for (int i=0;i<lcrom;i++)
		{//genero 0 si mat.random<0.5; 1 e.o.c
			gen=new Gen_f1((Math.random()<0.5) ? 0 : 1);			
			genes.add(gen);
		}
		adaptacion_bruta=calculaadaptacion_bruta();
	}
	
	
	
	public Individuo clone_aux(Object x_min,Object x_max, double prec)
	{
		Individuo unIndividuo=new Individuo_basico ((Double)x_min,(Double)x_max,prec);
		return unIndividuo;
	}
	
		
	
	double miFuncion (Object un_valor)
	{
		double valor=(Double) un_valor;
		double res=0;
		res=Math.sqrt(Math.abs(valor));
		double unSin=Math.sin(res);
		res=valor*unSin;
		res=Math.abs(res);
		return -res;
	}
		
	void  decod()
	{//mucho casting ....
		Double x=(Double) this.x;
		x=(double)((double)bin_ent()[0]/(double)(Math.pow(2, lcrom)-1));
		x*=((Double)x_max - (Double) x_min);
		x+=(Double)x_min;
		this.x=x;
	}
	
	
	int[] bin_ent()
	{
		int[] nums= new int[1];
		nums[0]=0;
		int pot=1;
		for (int i=0;i<lcrom;i++)
		{
			nums[0]+=pot*((Integer)genes.get(lcrom-i-1).toInt()[0]);
			pot=pot*2;
		}		
		return nums;
	}
	
	int tamGen(Object x_min, Object x_max, double prec)
	{
	return logica.Calculadora.tamGen((Double)x_min,(Double)x_max, prec);
	};
	
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
