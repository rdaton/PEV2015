package individuo;

import java.util.ArrayList;
import java.util.List;

public class TArbol 
{
TArbol padre;
List<TArbol> hijos;
logica.TipoOperador operador;
int num_nodos;// número de nodos
int profundidad; // profundidad del árbol

public TArbol(int prof_min,int prof_max)
{
	if (prof_min > 0)  //no puede ser hoja
	{
		// generación del subarbol de operador
		// símbolo de operador aleatorio
		operador=logica.TipoOperador.operadorAleatorio();
		num_nodos++;
		// se generan los hijos
		hijos=new ArrayList();
		TArbol HI=new TArbol(prof_min - 1, prof_max - 1);
		hijos.add(HI);
		num_nodos+=HI.num_nodos;
		if (operador.aridad()==3)
		{
			TArbol HC=new TArbol(prof_min - 1, prof_max - 1);
			hijos.add(HC);
			num_nodos+=HC.num_nodos;
		}
		TArbol HD=new TArbol(prof_min - 1, prof_max - 1);
		hijos.add(HD);
		num_nodos+=HD.num_nodos;
	} //prof_min==0
	else 
	{
		if (prof_max==0) // sólo puede ser hoja
		{
			// generación del subarbol de operando
			// símbolo de operando aleatorio
			operador=logica.TipoOperador.operandoAleatorio();
			num_nodos++;
		}	
		else
		{		
			// se decide aleatoriamente operando u operador
			int tipo = logica.Calculadora.dameRandom(0,1);
			if (tipo==1)
			{// generación del subarbol de operador
				// símbolo de operador aleatorio
				operador=logica.TipoOperador.operadorAleatorio();
				num_nodos++;
				// se generan los hijos
				hijos=new ArrayList();
				TArbol HI=new TArbol(prof_min - 1, prof_max - 1);
				hijos.add(HI);
				num_nodos+=HI.num_nodos;
				if (operador.aridad()==3)
				{
					TArbol HC=new TArbol(prof_min - 1, prof_max - 1);
					hijos.add(HC);
					num_nodos+=HC.num_nodos;
				}
				TArbol HD=new TArbol(prof_min - 1, prof_max - 1);
				hijos.add(HD);
				num_nodos+=HD.num_nodos;
			}
			
			else{
				// generación del subarbol de operando
				// símbolo de operando aleatorio
				operador=logica.TipoOperador.operandoAleatorio();
				num_nodos++;
			}
		}
	}
} //TArbol()


public TArbol [] cruce (TArbol padre1, TArbol padre2)
{
	TArbol hijo1=null;
	TArbol hijo2=null;
	TArbol[] hijos={hijo1,hijo2};
	
	
	
	return hijos;
}


}//class Tarbol
 

