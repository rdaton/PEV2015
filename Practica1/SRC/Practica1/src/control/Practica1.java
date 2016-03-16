package control;

import logica.Calculadora;

public class Practica1 {
	
	public static void main(String [ ] args)
	{
		int lcrom=logica.Calculadora.tamGen(0, 500, 0.1);
		AGenetico unAlg = new AGenetico(100,lcrom,0.6,0.05,0,500);
		System.out.println(unAlg.dameMejor());
	}
	
	

}
