package logica;

public enum TipoCruce {
	PMX, OX, OXPOSPRIOR, CRUCEOXORDPRIOR, CICLOS, ERX, ORDINAL, PROPIO;
	public String toString() {
		switch (this) {
		case PMX:
			return "PMX";
		case OX:
			return "OX";
		case OXPOSPRIOR:
			return "OXPOSPRIOR";
		case CRUCEOXORDPRIOR:
			return "CRUCEOXORDPRIOR";
		case CICLOS:
			return "CICLOS";
		case ERX:
			return "ERX";
		case ORDINAL:
			return "ORDINAL";
		case PROPIO:
			return "PROPIO";
		default:
			return "PMX";
		}
	}
}
