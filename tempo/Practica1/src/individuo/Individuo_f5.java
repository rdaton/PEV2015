package individuo;

public  class Individuo_f5 extends Individuo{

	private static final int N = 2;	//PARAMETRO N
	private int long_elem; 			//LONGITUD DE CADA ELEMENTO DEL VECTOR

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public Individuo_f5() { }

	public Individuo_f5(Object x_min, Object x_max, double prec) {
		super(x_min, x_max, prec);
		x = new Double[N];
		Gen gen;

		//GENERO 0 SI MAT.RANDOM<0.5; 1 E.O.C
		for (int i=0;i<lcrom;i++) {
			gen = new Gen_f1((Math.random()<0.5) ? 0 : 1);
			genes.add(gen);
		}

		decod();
		adaptacion_bruta = calculaadaptacion_bruta();
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public Individuo clone_aux(Object x_min, Object x_max, double prec) {
		Individuo unIndividuo = new Individuo_f5 (x_min,x_max,prec);
		return unIndividuo;
	}

	public double miFuncion(Object un_valor) {
		double[] res_aux = new double[2];
		for (int i=0;i<N;i++) {
			res_aux[i] = func_aux(i,i);
		}
		return res_aux[0] * res_aux[1];
	}

	private double func_aux(int g, int ind) {
		Double[] x = (Double[])this.x;
		double res = 0;
		for(int i=1;i<=5;i++) {
			res+= i * Math.cos(((i+1) * x[ind]) + 1);
		}
		return res;
	}

	public void  decod() {
		Double[] x = (Double[]) this.x;
		int[] unBinEnt = bin_ent();

		for (int i=0;i<N;i++) {
			x[i] = (double)(unBinEnt[i]/(double)(Math.pow(2, long_elem)-1));
			x[i]*= (((Double[])x_max)[0] - ((Double[]) x_min)[0]);
			x[i]+= ((Double[]) x_min)[0];
		}
		this.x = x;
	}

	public int[] bin_ent() {
		int[] nums = new int[N];
		for (int i=0;i<N;i++) {
			nums[i] = 0;
			int pot = 1;
			for (int s=(i*long_elem);s<((i+1)*long_elem);s++) {
				//int indice = s;
				int indice = lcrom - s - 1;
				nums[i]+= pot * ((Integer)genes.get(indice).toInt());
				pot = pot * 2;
			}
		}
		return nums;
	}

	public Integer tamGen(Object x_min, Object x_max, double prec) {
		Integer unTamanyo = logica.Calculadora.tamGen(((Double[])x_min)[0], ((Double[])x_max)[0], prec);
		long_elem = unTamanyo;
		return unTamanyo*N;
	}

	@Override
	public String toString() {
		StringBuffer unBuffer = new StringBuffer();
		decod();
		/*for(int i=0;i<N;i++) {
			unBuffer.append(((Double[])x)[i]).append(';');
			
		}*/
		unBuffer.append(this.adaptacion_bruta);
		return unBuffer.toString();
	}

}
