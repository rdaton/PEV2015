package individuo;

public class TipoFlecha {
	enum tipoEstado {NORTE,ESTE,SUR,OESTE};
	tipoEstado estado;
	
	public TipoFlecha()
	{
		estado=tipoEstado.ESTE;
	}
	
	private int [] dameIncrementos()
	{
		int[] salida={0,0};
		
		switch (estado)
		{
		case NORTE:
			salida[0]=-1;
			salida[1]=0;
			break;
		case SUR:
			salida[0]=1;
			salida[1]=0;
			break;
		case ESTE:
			salida[0]=0;
			salida[1]=1;
			break;		
		case OESTE:
			salida[0]=0;
			salida[1]=-1;
			break;			
		default:
			salida[0]=0;
			salida[1]=1;
			break;			
		}
		return salida;
	}
	
	public int[] avanza()
	{
		return dameIncrementos();
	}
	
	public void giraIzquierda()
	{
		switch (estado)
		{
		case NORTE:
			estado=tipoEstado.OESTE;
			break;
		case SUR:
			estado=tipoEstado.ESTE;
			break;
		case ESTE:
			estado=tipoEstado.NORTE;
			break;		
		case OESTE:
			estado=tipoEstado.SUR;
			break;			
		default:
			estado=tipoEstado.NORTE;
			break;			
		}
	}

	public void giraDerecha()
	{

		switch (estado)
		{
		case NORTE:
			estado=tipoEstado.ESTE;
			break;
		case SUR:
			estado=tipoEstado.OESTE;
			break;
		case ESTE:
			estado=tipoEstado.SUR;
			break;		
		case OESTE:
			estado=tipoEstado.NORTE;
			break;			
		default:
			estado=tipoEstado.SUR;
			break;			
		}
	}
}
