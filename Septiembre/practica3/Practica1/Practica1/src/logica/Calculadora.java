package logica;

public class Calculadora {

	public static int tamGen (int min, int max) {
		return (max - min);
	}

	public static int dameRandom(int a, int b) {
		if (a>b) {
			int c = a;
			a = b;
			b = c;
		}
		int d = (int)(Math.random()*(b-a+1)+a);
		return d;
	}

}
