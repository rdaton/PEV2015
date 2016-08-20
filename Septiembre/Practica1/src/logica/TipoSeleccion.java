package logica;

public enum TipoSeleccion {
	RULETA, TORNEO, ESTADISTICO, RANKING, RESTOS, TRUCAMIENTO;
	public String toString() {
		switch (this) {
		case RULETA:
			return "RULETA";
		case TORNEO:
			return "TORNEO";
		case ESTADISTICO:
			return "ESTADISTICO";
		case RANKING:
			return "RANKING";
		case RESTOS:
			return "RESTOS";
		case TRUCAMIENTO:
			return "TRUCAMIENTO";
		default:
			return "RULETA";
		}
	}

}
