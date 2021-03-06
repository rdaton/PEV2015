package individuo;

public class Tablero {
	private static final int tamTablero=32;
	
	private static final char[][] modeloTablero=
	    {
	    ("@ # # # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
	    ("0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
	    ("0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # # # 0 0 0 0").toCharArray(), 
	    ("0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 # 0 0").toCharArray(),
	    ("0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 # 0 0").toCharArray(), 
	    ("0 0 0 # # # # 0 # # # # # 0 0 0 0 0 0 0 0 # # 0 0 0 0 0 0 0 0 0").toCharArray(),
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 # 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 # 0 0 0 0 0 # # # 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 # 0 0 # 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 # 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 # 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 # # 0 0 # # # # # 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 # 0 0 0 0 0 0 # # # # # # # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 # 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(), 
		("0 0 # # # # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray(),
		("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0").toCharArray()
		};
	
	
	int nPasos;
	int fila;
	int columna;
	TipoFlecha flecha;
	char[][] tableroComida;
	int[][]	 tableroPasos;
	
    

public Tablero()
{
	nPasos=0;
	fila=0;
	columna=0;
	flecha=new TipoFlecha();
	tableroComida=copiarModeloTablero();
	tableroPasos=new int[tamTablero][tamTablero];
}

char[][] copiarModeloTablero()
{
	char[][] salida=new char[32][32];
	for (int i=0;i<tamTablero;i++)
		for (int j=0;j<tamTablero;j++)
		{
			salida[i][j]=modeloTablero[i][j];
		}
	return salida;
}



boolean esComida(int i, int j)
{
	return tableroComida[i][j]=='#';
}
boolean haSidoComida(int i, int j)
{
	return tableroPasos[i][j]==-1;
}

void visita (int i, int j)
{
	nPasos++;
	
	int unPaso=nPasos;	
	if (esComida(i,j))
	{
		unPaso*=-1;
		tableroComida[i][j]=0;
	};	
	tableroPasos[i][j]=unPaso;	
	
}

//es un toroide
private int corregir(int a)
{	
	if (a==-1)
		return tamTablero-1;
	else 
		if (a==tamTablero)			
			return 0;
		else 
			return a;	
}

void avanza()
{
	int[] coord=flecha.avanza();
	fila+=coord[0];
	corregir(fila);
	columna+=coord[1];
	corregir(columna);	
	visita(fila,columna);
}

void derecha()
{
	flecha.giraDerecha();
	avanza();
}

void izquierda()
{
	flecha.giraIzquierda();
	avanza();
}
boolean hayComidaDelante()
{
	int[] coord=flecha.avanza();
	int otraFila=fila+coord[0];
	corregir(otraFila);
	int otraColumna=columna+coord[1];
	corregir(otraColumna);	
	return esComida(otraFila, otraColumna);
}
public String toString()
{
	StringBuffer unBuffer=new StringBuffer();
	for (int i=0;i<tamTablero;i++)
	{
		for (int j=0;j<tamTablero;j++)
		{
			String letra="u";;
			if (esComida(i,j))
				letra="#";
			else 
				if (haSidoComida(i,j))
					letra="c";
				else
					letra=new Integer(tableroPasos[i][j]).toString();
			String letraFormateada=new String(new char[4 - letra.length()]).replace('\0', ' ') + letra;
			unBuffer.append(letraFormateada);			
		}
		unBuffer.append("\n");
	}
	return unBuffer.toString();
}
}
