package individuo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public  class Individuo_f5 extends Individuo{

//parametro n
static final int N=2;
//longitud de cada elemento del vector
int long_elem;	
	
public Individuo_f5() {
		
	}

	public Individuo_f5 (Object x_min, Object x_max,double prec)
	{			
		super(x_min,x_max,prec);		
		x=new Double[N];
		Gen gen;		
		for (int i=0;i<lcrom;i++)
		{//genero 0 si mat.random<0.5; 1 e.o.c
			gen=new Gen_f1((Math.random()<0.5) ? 0 : 1);			
			genes.add(gen);
		}
		decod();
		adaptacion_bruta=calculaadaptacion_bruta();
	}
	
	

	public Individuo_f5 newInstance(Object x_min, Object x_max,double prec) {
		Individuo_f5 unIndividuo=new Individuo_f5(x_min,x_max,prec);
		return unIndividuo;
		
    }
	
	public Individuo clone_aux(Object x_min,Object x_max, double prec)
	{
		Individuo unIndividuo=new Individuo_f5 (x_min,x_max,prec);
		return unIndividuo;
	}
	
		
	
	double miFuncion (Object un_valor)
	{
		Double[] valor=((Double[]) un_valor);
		double res=0;
		double[] res_aux=new double[2];
		//N==2
		for (int i=0;i<N;i++)
		{
			res_aux[i]=func_aux(i,i);
		}		
		
		return res_aux[0]*res_aux[1];
	}
	
	
	
	double func_aux(int g, int ind)
	{
		Double[] x= (Double[])this.x;
		double res=0;
		
		for (int i=1;i<=5;i++)
		{
			res+=i*Math.cos(((i+1)*x[ind])+1);
		}
		
		
		return res;
	}
	
	
		
	void  decod()
	{//mucho casting ....
		Double[] x = (Double[]) this.x; 
		int[] unBinEnt=bin_ent();
		
		for (int i=0;i<N;i++)
		{
		x[i]=(double)(unBinEnt[i]/(double)(Math.pow(2, long_elem)-1));
		x[i]*=(((Double[])x_max)[0] - ((Double[]) x_min)[0]);
		x[i]+=((Double[]) x_min)[0];
		//System.out.println(x[i]);
		}
		this.x=x;
	}
	
	int[] bin_ent()
	{
		int[] nums= new int[N];
		
		for (int i=0;i<N;i++)
		{
			nums[i]=0;
			int pot=1;
		
			for (int s=i*N;s<(i*N)+long_elem;s++)
			{
				int indice=lcrom-s-1;
				nums[i]+=pot*((Integer)genes.get(indice).toInt());
				pot=pot*2;
			}		
		}
		return nums;
	}
	
	Integer tamGen(Object x_min, Object x_max, double prec)
	{
		Integer unTamanyo=logica.Calculadora.tamGen(((Double[])x_min)[0],((Double[])x_max)[0], prec);
		long_elem=unTamanyo;
		return unTamanyo*N;
	};
	
	@Override
	public String toString()
	{
		StringBuffer unBuffer=new StringBuffer();
		decod();
		for (int i=0;i<N;i++)
		{
		unBuffer.append(((Double[])x)[i]).append(',');
		unBuffer.append(this.adaptacion_bruta).append(',');
		}
		return unBuffer.toString();
	}
		

	
	
}
