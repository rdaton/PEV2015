package logica;

public class Calculadora {

	public static int tamGen (double min, double max, double prec) {
		double unNumero = (double) 1 + ((double) (max-min) / (double) prec);
		unNumero = Math.log(unNumero) / Math.log(2);
		return (int) Math.ceil(unNumero);
	}

}
