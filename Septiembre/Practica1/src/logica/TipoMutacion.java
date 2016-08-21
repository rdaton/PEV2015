package logica;

public enum TipoMutacion {
	INSERCION, INTERCAMBIO, INVERSION, HEURISTICA, PROPIO;
	public String toString() {
		switch (this) {
		case INSERCION:
			return "INSERCIÓN";
		case INTERCAMBIO:
			return "INTERCAMBIO";
		case INVERSION:
			return "INVERSIÓN";
		case HEURISTICA:
			return "HEURÍSTICA";
		case PROPIO:
			return "PROPIO";
		default:
			return "INSERCIÓN";
		}
	}
	
	public int toInt()
	{
		switch (this) {
		case INSERCION:
			return 0;
		case INTERCAMBIO:
			return 1;
		case INVERSION:
			return 2;
		case HEURISTICA:
			return 3;
		case PROPIO:
			return 4;
		default:
			return 0;
		}
	}

}