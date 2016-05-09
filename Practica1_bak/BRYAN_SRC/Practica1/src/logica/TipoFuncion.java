package logica;

public enum TipoFuncion {
	FUNCION_1, FUNCION_2, FUNCION_3, FUNCION_4, FUNCION_5, FUNCION_6;
	public String toString() {
		switch (this) {
		case FUNCION_1:
			return "FUNCION 1";
		case FUNCION_2:
			return "FUNCION 2";
		case FUNCION_3:
			return "FUNCION 3";
		case FUNCION_4:
			return "FUNCION 4";
		case FUNCION_5:
			return "FUNCION 5";
		case FUNCION_6:
			return "FUNCION 6";
		default:
			return "FUNCION 1";
		}
	}

}