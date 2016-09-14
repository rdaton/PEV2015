package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logica.Calculadora;

public  class Individuo_basico extends Individuo{

	

	private List<Integer> listaCiudades;

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public Individuo_basico() { }

	public Individuo_basico(int x_min, int x_max) {
		super(x_min, x_max);
		Gen gen = null;
		for(int i=0;i<lcrom;i++) {
			boolean cont = true;
			while(cont) {
				gen = new Gen_f1(Calculadora.dameRandom(x_min, x_max-1));
				cont = Poblacion.contiene(genes, gen, 0, lcrom,genes.size());
			}
			genes.add(gen);
		}
		decod();
		adaptacion_bruta = calculaadaptacion_bruta();
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public Individuo_basico newInstance(int x_min, int x_max) {
		Individuo_basico unIndividuo = new Individuo_basico(x_min, x_max);
		return unIndividuo;
    }

	public Individuo clone_aux(int x_min, int x_max) {
		Individuo unIndividuo = new Individuo_basico (x_min, x_max);
		return unIndividuo;
	}

	

	double miFuncion(Object un_valor) {
		/*
		 * funcion adaptacion(TIndividuo individuo, TMapa mapa) {
. . .
mientras (pasos < 400 y bocados < 90)
ejecutaArbol(individuo.arbol);
. . .
}
public void ejecutaArbol(Arbol A){
//mientras no se haya acabado el tiempo ni la comida
si (pasos < 400 && bocados <90) entonces {
//si estamos encima de comida comemos
si (matriz[posicionX][posicionY]== 1) entonces {
matriz[posicionX][posicionY] = 0;
bocados++;
}
//acciones a realizar en función del nodo en el que estemos
si A.getValor()== "PROGN3"){
ejecutaArbol(A.getHi());
ejecutaArbol(A.getHc());
ejecutaArbol(A.getHd());
}
eoc si (A.getValor()== "PPROGN2"){
ejecutaArbol(A.getHi());
ejecutaArbol(A.getHc());
}
eoc if(A.getValor()== "SIC"){
si (hayComida()) ejecutaArbol(A.getHi());
eoc ejecutaArbol(A.getHc());
}
eoc si A.getValor()== "AVANZA") Avanza();
eoc si A.getValor()== "DERECHA") Derecha();
eoc si(A.getValor()== "IZQUIERDA") Izquierda();
}
}
		 */
		
		
	}

	void decod() {
		listaCiudades = new ArrayList<Integer>();
		for (int i=0;i<lcrom;i++) {
			listaCiudades.add(i);
		}
		Integer[] arrayInt = new Integer[lcrom];
		for (int i=0;i<lcrom;i++) {
			arrayInt[i] = (Integer) genes.get(i).clone().bit;
		}
		this.x = arrayInt;
		
	}

	Integer damePosGen(List<Gen> unaLista, Gen unGen )
	{
		int pos=0;
		boolean enc=false;
		while (!enc)
		{
			enc=unaLista.get(pos).equals(unGen);
			if (!enc) pos++;
		}
		
		return pos;
		
	}
	
	List<Gen> dameListaCiudades()
	{
		List<Gen> unaLista=new ArrayList();
		
		for (int i=0;i<lcrom;i++)
		{
			unaLista.add(new Gen_f1(i));
		}
		
		return unaLista;
	}
	
	/*
	//CONVIERTE DE CODIF ORDINAL A NORMAL
		
	private Integer[] ordinalNormal () {
		Integer[] otroCromosoma = new Integer[lcrom];
		Integer[] otroCrom = (Integer[] )this.x;

		List<Integer> unaLista = new ArrayList<Integer> ();
		Iterator<Integer> unIterator = this.listaCiudades.iterator();
		while (unIterator.hasNext())
			unaLista.add(unIterator.next());
		for (int i=0;i<lcrom;i++) {
			otroCromosoma[i]=unaLista.get(otroCrom[i]);
			unaLista.remove(otroCrom[i]);
		}
		return otroCromosoma;
	}
	*/
	
	List<Gen> ordinalNormal()
	{		
		List<Gen> otraLista=new ArrayList();
		List<Gen> listaCiudades=dameListaCiudades();
		Integer[] listaEnteros=new Integer[lcrom];
		int ind=0;
		for (int i=0;i<lcrom;i++)
		{
			ind=(Integer)genes.get(i).bit;
			otraLista.add(listaCiudades.get(ind-1));
			listaCiudades.remove(ind-1);
		}
		
		return otraLista;
	}
	
	List<Gen> normalOrdinal()
	{			
		List<Gen> otraLista=new ArrayList();
		List<Gen> listaCiudades=dameListaCiudades();
		Integer[] listaEnteros=new Integer[lcrom];
		int pos=0;
		for (int i=0;i<lcrom;i++)
		{
			pos=damePosGen(listaCiudades,genes.get(i));
			listaEnteros[i]=pos+1;
			listaCiudades.remove(pos);
		}
		
		for (int i=0;i<lcrom;i++)
		{
			otraLista.add(new Gen_f1(listaEnteros[i]));
		}
		return otraLista;
	}
	
	
	public Integer tamGen(int x_min, int x_max) {
		Integer unTamanyo = logica.Calculadora.tamGen(x_min, x_max);
		return unTamanyo;
	}

	@Override
	public String toString() {
		StringBuffer unBuffer = new StringBuffer();
		//decod();
		//unBuffer.append(x).append(";  ");
		unBuffer.append(this.adaptacion_bruta);//.append(';');
		return unBuffer.toString();
	}

}
