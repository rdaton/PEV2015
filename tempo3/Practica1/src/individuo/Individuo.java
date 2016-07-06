package individuo;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public  abstract class Individuo {

	private double adaptacion; 		     //ADAPTACION AJUSTADA
	private double puntuacion; 		     //PUNT. RELATIVA: ADAPTACION/SUMADAPTACION
	private double punt_acu; 		     //PUNTUACION ACUMULADA PARA SORTEOS
	private double prec;

	protected double adaptacion_bruta;   //FUNCIoN DE EVALUACioN
	protected List <Gen> genes; 		 //GENOTIPOS
	protected Object x_min;
	protected Object x_max;
	protected Integer lcrom;
	protected Object x; 				 //FENOTIPO

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public Individuo(){ };

	public Individuo (Object x_min, Object x_max, double prec) {
		this.prec  = prec;
		this.x_min = x_min;
		this.x_max = x_max;
		this.lcrom = tamGen(x_min,x_max, prec);
		this.genes = new ArrayList<Gen>();
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	@SuppressWarnings("rawtypes")
	public Individuo newInstance(Object x_min, Object x_max, double prec) {
        try {
        	Constructor unConstructor = this.getClass().getDeclaredConstructors()[1];
        	return (Individuo) unConstructor.newInstance(x_min,x_max,prec);
        } catch (Exception e) {
    		e.printStackTrace();
    	} finally{
    	}
        return null;
    }

	abstract Integer tamGen(Object x_min, Object x_max, double prec);

	public Integer damelCrom() {
		return lcrom;
	}

	public Individuo clone() {
		//UNICA PARTE QUE VARIA
		Individuo unIndividuo = clone_aux(x_min,x_max, prec);		
		//FIN DE PARTE QUE VARIA

		for (int i=0;i<this.lcrom;i++) {
			unIndividuo.genes.set(i,this.genes.get(i).clone());
		}

		unIndividuo.decod();
		unIndividuo.adaptacion_bruta = this.adaptacion_bruta;
		unIndividuo.adaptacion = this.adaptacion;
		unIndividuo.puntuacion = this.puntuacion;
		unIndividuo.punt_acu = this.punt_acu;
		return unIndividuo;
	}

	//abstract int[] bin_ent();

	double calculaadaptacion_bruta() {
		decod();
		return miFuncion(x);
	}

	abstract double miFuncion (Object valor);

	abstract void  decod();

	public abstract Individuo clone_aux(Object x_min, Object x_max, double prec);

	@Override
	abstract public String toString();

	// =========================
	// ======== GETTERS
	// =========================

	public double getadaptacion_bruta() {
		return adaptacion_bruta;
	}

	public double getadaptacion() {
		return adaptacion;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public double getPunt_acu() {
		return punt_acu;
	}

	public Object getX() {
		return  x;
	}

	// =========================
	// ======== SETTERS
	// =========================

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public void setPunt_acu(double punt_acu) {
		this.punt_acu = punt_acu;
	}

	public void setadaptacion (double adapt) {
		this.adaptacion=adapt;
	}
	
	void borraGenes()
	{
		for (int i=0;i<lcrom;i++)
		{
			genes.set(i,genes.get(0).newInstance());
		};
		decod();
	}

}
