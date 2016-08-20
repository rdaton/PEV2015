package logica;

public enum TipoSeleccion {
	RULETA, TORNEO, ESTADISTICO;
	public String toString() {
		switch (this) {
		case RULETA:
			return "RULETA";
		case TORNEO:
			return "TORNEO";
		case ESTADISTICO:
			return "ESTADISTICO";
		default:
			return "RULETA";
		}
	}

}
