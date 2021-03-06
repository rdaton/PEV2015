package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public  class Individuo_basico extends Individuo{

	final static int [][] matrizDistancias = {
		{},
		{251},
		{422, 171},
		{563, 369, 294},
		{115, 366, 537, 663},
		{401, 525, 696, 604, 318},
		{621, 540, 515, 809, 717, 1022},
		{395, 646, 817, 958, 401, 694, 620},
		{237, 488, 659, 800, 243, 536, 583, 158},
		{297, 504, 675, 651, 229, 89, 918, 605, 447},
		{663, 617, 688, 484, 618, 342, 1284, 1058, 900, 369},
		{417, 256, 231, 525, 532, 805, 284, 607, 524, 701, 873},
		{190, 207, 378, 407, 256, 318, 811, 585, 427, 324, 464, 463},
		{400, 354, 525, 332, 457, 272, 908, 795, 637, 319, 263, 610, 201},
		{609, 860, 1031, 1172, 538, 772, 1118, 644, 535, 683, 1072, 1026, 799, 995},
		{167, 142, 313, 511, 282, 555, 562, 562, 404, 451, 708, 305, 244, 445, 776},
		{721, 640, 615, 909, 817, 1122, 100, 720, 683, 1018, 1384, 384, 911, 1008, 1218, 662},
		{434, 363, 353, 166, 534, 438, 868, 829, 671, 485, 335, 584, 278, 166, 1043, 479, 968},
		{58, 309, 480, 621, 173, 459, 563, 396, 238, 355, 721, 396, 248, 458, 667, 486, 663, 492},
		{632, 506, 703, 516, 552, 251, 1140, 939, 781, 323, 219, 856, 433, 232, 1006, 677, 1240, 350, 690},
		{397, 495, 570, 830, 490, 798, 274, 322, 359, 694, 1060, 355, 587, 797, 905, 406, 374, 831, 339, 1029},
		{335, 264, 415, 228, 435, 376, 804, 730, 572, 423, 367, 520, 179, 104, 944, 380, 904, 99, 393, 336, 732},
		{333, 584, 855, 896, 255, 496, 784, 359, 201, 407, 796, 725, 511, 733, 334, 500, 884, 761, 391, 730, 560, 668},
		{465, 515, 490, 802, 558, 866, 156, 464, 427, 762, 1128, 259, 655, 865, 973, 472, 256, 861, 407, 1097, 118, 779, 628},
		{336, 578, 653, 899, 358, 676, 468, 152, 115, 595, 999, 455, 526, 736, 650, 464, 568, 770, 278, 968, 244, 671, 316, 312},
		{511, 762, 933, 1074, 440, 674, 1020, 546, 437, 585, 974, 928, 696, 897, 98, 678, 1120, 945, 569, 908, 807, 846, 236, 875, 352},
		{544, 473, 482, 219, 644, 436, 997, 939, 781, 506, 265, 713, 388, 187, 1153, 615, 1097, 129, 602, 313, 941, 209, 877, 1009, 880, 1055},
		{401, 150, 75, 219, 516, 675, 590, 796, 638, 654, 613, 306, 357, 444, 1010, 292, 690, 278, 459, 628, 611, 340, 734, 583, 694, 912, 407}
	};

	List<Integer> listaCiudades;
	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public Individuo_basico() { }

	public Individuo_basico(Object x_min, Object x_max, double prec) {
		super(x_min, x_max, prec);
		Gen gen;
		for(int i=0;i<lcrom;i++) {
			gen = new Gen_f1( (Integer)x_min + (int)(Math.random() * (((Integer)x_max - (Integer)x_min) + 1)) );
			genes.add(gen);
		}
		decod();
		adaptacion_bruta = calculaadaptacion_bruta();
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public Individuo_basico newInstance(Object x_min, Object x_max,double prec) {
		Individuo_basico unIndividuo = new Individuo_basico(x_min, x_max, prec);
		return unIndividuo;
    }

	public Individuo clone_aux(Object x_min, Object x_max, double prec) {
		Individuo unIndividuo = new Individuo_basico (x_min, x_max, prec);
		return unIndividuo;
	}

	//convierte de codif ordinal a normal
	Integer[] convertirCromosoma ()
	{
		Integer[] otroCromosoma= new Integer[lcrom];
		Integer[] otroCrom= (Integer[] )this.x;

		List<Integer> unaLista=new ArrayList();
		Iterator<Integer> unIterator=this.listaCiudades.iterator();
		while (unIterator.hasNext())
			unaLista.add(unIterator.next());

		for (int i=0;i<lcrom;i++)
		{
			otroCromosoma[i]=unaLista.get(otroCrom[i]);
			unaLista.remove(otroCrom[i]); //íComprobar!
		}

		return otroCromosoma;
	}
	double miFuncion(Object un_valor) {
		double res = 0;
		Integer[] otroCrom= (Integer[] )this.x;
		//aqui ya tenemos cromosoma simple


		int acumulador=0;
		int a=0;
		int b=0;
		for (int i=0;i<lcrom-1;i++)
		{
			a=otroCrom[i];
			b=otroCrom[i+1];
			if (a<b)
			{
				acumulador+=matrizDistancias[b][a];
			}
			else {
				acumulador+=matrizDistancias[a][b];
			}

		}



		return res;
	}

	void decod()
	{
	listaCiudades = new ArrayList();
		for (int i=0;i<lcrom;i++)
		{
			listaCiudades.add(i);
		}

	}

//	int[] bin_ent() {
//		int[] nums = new int[1];
//		nums[0] = 0;
//		int pot = 1;
//		for (int i=0;i<lcrom;i++) {
//			nums[0] += pot*((Integer)genes.get(lcrom-i-1).toInt());
//			pot = pot*2;
//		}
//		return nums;
//	}

	public Integer tamGen(Object x_min, Object x_max, double prec) {
		Integer unTamanyo = logica.Calculadora.tamGen(((Double[])x_min)[0], ((Double[])x_max)[0], prec);
		return unTamanyo;
	};

	@Override
	public String toString() {
		StringBuffer unBuffer = new StringBuffer();
		//decod();
		//unBuffer.append(x).append(";  ");
		unBuffer.append(this.adaptacion_bruta);//.append(';');
		return unBuffer.toString();
	}

}
