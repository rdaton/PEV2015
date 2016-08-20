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

}