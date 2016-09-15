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
		tablero=new Tablero();
		this.genes = new TArbol(x_max);
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
	tablero=new Tablero();
	
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
		
return 0;		
}



	@Override
	public String toString() {
		StringBuffer unBuffer = new StringBuffer();
		//decod();
		//unBuffer.append(x).append(";  ");
		unBuffer.append(tablero);
		unBuffer.append(this.adaptacion_bruta);//.append(';');
		return unBuffer.toString();
	}

}
