package individuo;

public class Gen_f1 extends Gen {

	//==========================================
	//============= CONSTRUCTORA ===============
	//==========================================

	public Gen_f1() {
		bit = new Integer(-1);
	}

	public Gen_f1(Integer g) {
		bit = new Integer(g);
	}

	public Gen_f1 newInstance() {
		Gen_f1 unGen = new Gen_f1();
		return unGen;
	}

	// =========================
	// == METODOS PUBLICOS
	// =========================

	public Integer toInt() {
		return (Integer)bit;
	}

	Gen clone_aux() {
		Gen_f1 unGen = new Gen_f1((Integer)bit);
		return unGen;
	}

	public boolean equals (Gen g2) {
		return (((Integer)this.bit).intValue()==((Integer)g2.bit).intValue());
	}

	public boolean estaVacio() {
		return ((Integer)bit==-1);
	}

	public String toString()
	{		
		return  ((Integer)bit).toString();
	}
}
