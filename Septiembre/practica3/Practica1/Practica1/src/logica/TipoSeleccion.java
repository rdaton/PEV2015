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
	public int toInt()
	{
		switch (this) {
		case RULETA:
			return 0;
		case TORNEO:
			return 1;
		case ESTADISTICO:
			return 2;
		case RANKING:
			return 3;
		case RESTOS:
			return 4;
		case TRUCAMIENTO:
			return 5;
		default:
			return 0;
		}
	}

}
