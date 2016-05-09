package logica;

import java.util.Iterator;
import java.util.List;

public class Calculadora {
	
	public static int tamGen (double min, double max, double prec)
	{
	double unNumero=1+((max-min)/prec);
	unNumero=Math.log(unNumero)/Math.log(2);
	return (int) Math.ceil(unNumero);
	}


	
}
