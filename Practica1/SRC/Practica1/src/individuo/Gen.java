package individuo;

public class Gen {
	int bit;
	
	public Gen(int g)
	{
		bit=g;
	}

	public int toInt()
	{
		return bit;
	}
	
	public boolean muta (int g)
	{
		if (g==bit) 
			return false;
		
		bit=g;
		return true;
	}
	
}
