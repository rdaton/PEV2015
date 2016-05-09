package logica;

public enum TipoCruce {
	MONOPUNTO, MULTIPUNTO;
	public String toString() {
		switch (this) {
		case MONOPUNTO:
			return "MONOPUNTO";
		case MULTIPUNTO:
			return "MULTIPUNTO";
		default:
			return "MONOPUNTO";
		}
	}
}
