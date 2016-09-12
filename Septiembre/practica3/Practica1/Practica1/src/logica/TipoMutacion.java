package logica;

public enum TipoMutacion {
	TERMINAL,FUNCION,INICIALIZACION;
	public String toString() {
		switch (this) {
		case TERMINAL:
			return "TERMINAL";
		case FUNCION:
			return "FUNCIÓN";
		case INICIALIZACION:
			return "INICIALIZACIÓN";
		default:
			return "TERMINAL";
		}
	}
	
	public int toInt()
	{
		switch (this) {
		case TERMINAL:
			return 0;
		case FUNCION:
			return 1;
		case INICIALIZACION:
			return 2;
		default:
			return 0;
		}
	}

}