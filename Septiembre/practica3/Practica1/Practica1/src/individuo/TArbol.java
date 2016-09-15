package individuo;

import java.util.ArrayList;
import java.util.List;

import logica.TipoOperador;

public class TArbol 
{
List<TArbol> hijos;
logica.TipoOperador operador;
int num_nodos;// número de nodos
int profundidad;

//constructora que uso para clone()
public TArbol (logica.TipoOperador operador,List<TArbol> hijos)
{
	this.operador=operador;
	num_nodos=1;
	this.hijos=new ArrayList();	
	for (int i=0;i<hijos.size();i++)
	{
		TArbol unHijo=hijos.get(i).clone();
		this.hijos.add(unHijo);
		num_nodos+=unHijo.num_nodos;
	}
	
}
//initialización completa
public TArbol(int prof_min,int prof_max)
{
	  new TArbol(prof_min,prof_max,0);
}
//garantizamos un árbol de cierta profundiad
public TArbol(int prof_min,int prof_max,int prof)
{
	prof++;
	profundidad=prof;
	hijos=new ArrayList();
	if (prof_min>1)
	{
		if (prof<prof_max)
		{
			// se decide aleatoriamente operando u operador
						int tipo = logica.Calculadora.dameRandom(0,1);
						if (tipo==1)
						{//hacemos arbol
							// generación del subarbol de operador
							// símbolo de operador aleatorio
							operador=logica.TipoOperador.operadorAleatorio();
							num_nodos=1;
							// se generan los hijos
							TArbol HI=new TArbol(prof_min - 1, prof_max,prof);
							hijos.add(HI);
							num_nodos+=HI.num_nodos;
							if (operador.aridad()==3)
							{
								TArbol HC=new TArbol(prof_min - 1, prof_max,prof);
								hijos.add(HC);
								num_nodos+=HC.num_nodos;
							}
							TArbol HD=new TArbol(prof_min - 1, prof_max,prof);
							hijos.add(HD);
							num_nodos+=HD.num_nodos;
						}
						
						else{//hacemos hoja
							// generación del subarbol de operando
							// símbolo de operando aleatorio
							operador=logica.TipoOperador.operandoAleatorio();
							num_nodos=1;
						}
					
		}
		else
		{
			//hacemos hoja
			// generación del subarbol de operando
			// símbolo de operando aleatorio
			operador=logica.TipoOperador.operandoAleatorio();
			num_nodos=1;
		}
	}
	else
	{
		//hacemos hoja
		// generación del subarbol de operando
		// símbolo de operando aleatorio
		operador=logica.TipoOperador.operandoAleatorio();
		num_nodos=1;
	}
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	hijos=new ArrayList();
	profundidad=prof;
	prof++;
	if (prof_min > 0 && prof<prof_max-1)  //no puede ser hoja
	{
		// generación del subarbol de operador
		// símbolo de operador aleatorio
		operador=logica.TipoOperador.operadorAleatorio();
		num_nodos=1;
		// se generan los hijos
		TArbol HI=new TArbol(prof_min - 1, prof_max,prof);
		hijos.add(HI);
		num_nodos+=HI.num_nodos;
		if (operador.aridad()==3)
		{
			TArbol HC=new TArbol(prof_min - 1, prof_max,prof);
			hijos.add(HC);
			num_nodos+=HC.num_nodos;
		}
		TArbol HD=new TArbol(prof_min - 1, prof_max,prof);
		hijos.add(HD);
		num_nodos+=HD.num_nodos;
	} //prof_min==0
	else 
	{
		//if (prof_max==0) // sólo puede ser hoja
		if (prof==prof_max)
		{
			// generación del subarbol de operando
			// símbolo de operando aleatorio
			operador=logica.TipoOperador.operandoAleatorio();
			num_nodos=1;
		}	
		else
		{		
			// se decide aleatoriamente operando u operador
			int tipo = logica.Calculadora.dameRandom(0,1);
			if (tipo==1)
			{//hacemos arbol
				// generación del subarbol de operador
				// símbolo de operador aleatorio
				operador=logica.TipoOperador.operadorAleatorio();
				num_nodos=1;
				// se generan los hijos
				hijos=new ArrayList();
				TArbol HI=new TArbol(prof_min - 1, prof_max,prof);
				hijos.add(HI);
				num_nodos+=HI.num_nodos;
				if (operador.aridad()==3)
				{
					TArbol HC=new TArbol(prof_min - 1, prof_max,prof);
					hijos.add(HC);
					num_nodos+=HC.num_nodos;
				}
				TArbol HD=new TArbol(prof_min - 1, prof_max,prof);
				hijos.add(HD);
				num_nodos+=HD.num_nodos;
			}
			
			else{//hacemos hoja
				// generación del subarbol de operando
				// símbolo de operando aleatorio
				operador=logica.TipoOperador.operandoAleatorio();
				num_nodos=1;
			}
		}
	}
} //TArbol() */

//voy a presumir, que me darán una posición superior a 1
//el reemplazo lo hago desde el padre
boolean sustituirSubArbol(int nodo_cruce,TArbol entrada, int pos)
{
	boolean enc=false;	
	
	//caso base
	if (pos==nodo_cruce)
		return true;
	
	//caso recursivo
	int i=0;
	while (i<hijos.size()&&!enc)
	{		
		pos++;
		enc=hijos.get(i).sustituirSubArbol(nodo_cruce,entrada,pos);	
		i++;
	}	
	if (enc)
		hijos.set(i,entrada);
	
	return false;
}



//recorrido inorder
TArbol buscarNodo(int nodo_cruce,int pos)
{
	TArbol enc=null;	
	
	//caso base
	if (pos==nodo_cruce)
		return this;
	
	//caso recursivo
	for (int i=0;i<hijos.size()&&(enc==null);i++)
	{
		pos++;
		enc=hijos.get(i).buscarNodo(nodo_cruce,pos);
	}	
	return enc;
}

public static TArbol[] cruce (TArbol padre1, TArbol padre2)
{
	TArbol hijo1=null;
	TArbol hijo2=null;;
	TArbol[] hijos={hijo1,hijo2};
	int nom_nodos=Math.min(padre1.num_nodos,padre2.num_nodos);
	int nodo_cruce=logica.Calculadora.dameRandom(1, nom_nodos);
	if (nodo_cruce==1) //un cruce desde el primer nodo es un intercambio directamente
	{
		hijo1=padre2.clone();
		hijo2=padre1.clone();
		return hijos;
	};//eoc
	hijo1=padre1.clone();
	hijo2=padre2.clone();
	TArbol subarbol1=hijo1.buscarNodo(nodo_cruce,1);
	TArbol subarbol2=hijo2.buscarNodo(nodo_cruce,1);
	
	if (subarbol1==null || subarbol2==null)
		return hijos;
		
	hijo1.sustituirSubArbol(nodo_cruce,subarbol2.clone(),1);
	hijo2.sustituirSubArbol(nodo_cruce,subarbol1.clone(),1);	
	
	
	return hijos;
	
}


public TArbol clone()
{
	TArbol unArbol = new TArbol(this.operador,this.hijos);
	return unArbol;	
}

public void mutacionTerminal()
{
	int nodo_mutar=1;
	TArbol unArbol=null;
	boolean enc=false;
	//busco un nodo que sea terminal
	while (!enc)
	{
	nodo_mutar=logica.Calculadora.dameRandom(1, num_nodos);
	unArbol=this.buscarNodo(nodo_mutar, 1);
	enc=unArbol.operador.esTerminal();
	}
	//lo reemplazo por un oerando aleatorio
	unArbol.operador=logica.TipoOperador.operandoAleatorio();
	
}

public void mutacionFuncion()
{
	int nodo_mutar=1;
	TArbol unArbol=null;
	boolean enc=false;
	//busco un nodo que sea función
	while (!enc)
	{
	nodo_mutar=logica.Calculadora.dameRandom(1, num_nodos);
	unArbol=this.buscarNodo(nodo_mutar, 1);
	enc=unArbol.operador.esFuncion();
	}
	//lo reemplazo por un operando aleatorio, de menor o o igual aridad
	enc=false;
	TipoOperador otroOperador=null;
	while (!enc)
	{
	otroOperador = logica.TipoOperador.operadorAleatorio();
	enc=otroOperador.aridad()<=unArbol.operador.aridad();
	}
	//si nos sale un operando de menor aridad, borro el ultimo hijo
	if (otroOperador.aridad()<unArbol.operador.aridad())
		unArbol.hijos.remove(unArbol.hijos.size()-1);
	unArbol.operador=otroOperador;		
}

public void mutacionInicializacion()
{
	
}




}//class Tarbol
 


