package individuo;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public  abstract class Individuo {

	private double adaptacion; 		     //ADAPTACION AJUSTADA
	private double puntuacion; 		     //PUNT. RELATIVA: ADAPTACION/SUMADAPTACION
	private double punt_acu; 		     //PUNTUACION ACUMULADA PARA SORTEOS

	protected double adaptacion_bruta;   //FUNCIoN DE EVALUACioN
	protected TArbol genes; 		 //GENOTIPOS
	protected int x_min;
	protected int x_max;
	protected Object x; 				 //FENOTIPO

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public Individuo(){ };

	public Individuo (int x_min, int x_max) {
		this.x_min = x_min;
		this.x_max = x_max;
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	@SuppressWarnings("rawtypes")
	public Individuo newInstance(int x_min, int x_max) {
        try {
        	Constructor unConstructor = this.getClass().getDeclaredConstructors()[1];
        	return (Individuo) unConstructor.newInstance(x_min, x_max);
        } catch (Exception e) {
    		e.printStackTrace();
    	} finally{
    	}
        return null;
    }

	

	

	public Individuo clone() {
		//UNICA PARTE QUE VARIA
		Individuo unIndividuo = this.newInstance(x_min, x_max);
		//FIN DE PARTE QUE VARIA
		
		unIndividuo.adaptacion_bruta = this.adaptacion_bruta;
		unIndividuo.adaptacion = this.adaptacion;
		unIndividuo.puntuacion = this.puntuacion;
		unIndividuo.punt_acu = this.punt_acu;
		return unIndividuo;
	}

	double calculaadaptacion_bruta() {
		adaptacion_bruta=miFuncion(x);
		return miFuncion(x);
		
	}

	abstract double miFuncion (Object valor);


	public abstract Individuo clone_aux(int x_min, int x_max);

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
	
	//métodos para codif. ordinal
	
	
	
}
