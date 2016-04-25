package individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class Individuo_f2 extends Individuo{
	

int long_x;
int long_y;
	
public Individuo_f2() {
		
	}
	

	public Individuo_f2 (Double[] x_min, Double[] x_max,double prec)
	{		
		super(x_min,x_max,prec);
		Gen gen;
		int bit;
		for (int i=0;i<lcrom;i++)
		{//genero 0 si mat.random<0.5; 1 e.o.c
			bit=(Math.random()<0.5) ? 0 : 1;
			gen=new Gen_f1(bit);			
			genes.add(gen);
		}
		decod();
		adaptacion_bruta=calculaadaptacion_bruta();
	}
	
	
	
	public Individuo clone_aux(Object x_min,Object x_max, double prec)
	{
		Individuo unIndividuo=new Individuo_f2 ((Double[])x_min,(Double[])x_max,prec);
		return unIndividuo;
	}
	
		
	
	double miFuncion (Object un_valor)
	{
		Double x =((Double[]) un_valor)[0];
		Double y =((Double[]) un_valor)[1];
		double res=0;
		
		res=x+Math.pow(y, 2)-7;
		res=-Math.pow(res, 2);
		
		res+=-Math.pow(Math.pow(x, 2)+y	-11, 2);
		
		res+=2186;
		
		res/=2186;
		return res;
	}
		
	
	void  decod()
	{//mucho casting y variables sin tipo
						
		Double x=(double)((double)bin_ent()[0]/(double)(Math.pow(2, long_x)-1));
		x*=(((Double[])x_max)[0] - ((Double[]) x_min)[0]);
		x+=((Double[]) x_min)[0];
		
		Double y=(double)((double)bin_ent()[1]/(double)(Math.pow(2, long_y)-1));
		y*=(((Double[])x_max)[1] - ((Double[]) x_min)[1]);
		y+=((Double[]) x_min)[1];
		
		Double[] xy={x,y};
		
		this.x=xy;
		
		
	}
	
	Integer tamGen(Object x_min, Object x_max, double prec)
	{
	Integer[] longitud=new Integer[2];
	longitud[0]=logica.Calculadora.tamGen(((Double[])x_min)[0],((Double[])x_max)[0], prec);
	longitud[1]=logica.Calculadora.tamGen(((Double[])x_min)[1],((Double[])x_max)[1], prec);
	this.long_x=longitud[0];
	this.long_y=longitud[1];
	
	return longitud[0]+longitud[1];
	};
	
	int[] bin_ent()
	{
		int[] nums= new int[2];
		nums[0]=0;
		nums[1]=0;
		int pot=1;
		for (int i=0;i<long_x;i++)
		{
			nums[0]+=pot*((Integer)genes.get(lcrom-i-1).toInt());
			pot=pot*2;
		}		
		pot=1;
		for (int i=long_x;i<long_x+long_y;i++)
		{
			nums[1]+=pot*((Integer)genes.get(lcrom-i-1).toInt());
		}
		return nums;
	}
	
	

	@Override
	public String toString()
	{
		StringBuffer unBuffer=new StringBuffer();
		decod();
		unBuffer.append(((Double[])x)[0]).append(',');
		unBuffer.append(((Double[])x)[1]).append(',');
		unBuffer.append(this.adaptacion_bruta).append(',');
		return unBuffer.toString();
	}
		

	
	
}
