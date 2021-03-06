package individuo;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  abstract class Individuo {
	List <Gen> genes; //genotipos	
	double adaptacion_bruta; //función de evaluación
	double adaptacion; //adaptación ajustada
	double puntuacion; //punt. relativa: adaptación/sumadaptación
	double punt_acu; //puntuación acumulada para sorteos
	Object x_min;
	Object x_max;
	double prec;
	Integer lcrom;
	Object x; //fenotipo
	
	public Individuo(){};
	
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
	
	public Individuo (Object x_min, Object x_max,double prec)
	{		
		this.prec=prec;
		this.x_min=x_min;
		this.x_max=x_max;	
		lcrom=tamGen(x_min,x_max, prec);
		genes=new ArrayList();
		
		
	}
	
	public Individuo newInstance(Object x_min, Object x_max,double prec) {
        try {
        	Constructor unConstructor=this.getClass().getDeclaredConstructors()[1];
        	return (Individuo) unConstructor.newInstance(x_min,x_max,prec);
        	}
        	catch (Exception e)
        	{
        		e.printStackTrace();
        	}
        	finally{        	
        	}
        return null;
        	
        
    }
	
   
	abstract Integer tamGen(Object x_min, Object x_max, double prec);
	
	public Integer damelCrom()
	{
		return lcrom;
	}
	public Individuo clone()
	{
		//única parte que varía
		Individuo unIndividuo=clone_aux(x_min,x_max, prec);
		//fin de parte que varía
		
		for (int i=0;i<this.genes.size();i++)
		{
			unIndividuo.genes.add(this.genes.get(i).clone());
		}
		unIndividuo.decod();
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
		return miFuncion(x);
	}
	
	
	abstract double miFuncion (Object valor);
		
	abstract void  decod();	
	
	public abstract Individuo clone_aux(Object x_min,Object x_max, double prec);
	
	@Override
	abstract public String toString();
	
	
	
	
}
