package individuo;

public class Gen_f1 implements Gen {

	int bit;
	
	public Gen_f1(int g)
	{
		bit=g;
	}

	public int toInt()
	{
		return bit;
	}
	
	public  void muta ()
	{
		if (bit==0)
			bit=1;
		else
			bit=1;		
		
	}
	
	public Gen clone()
	{
		Gen_f1 unGen=new Gen_f1(bit);
		return unGen;
	}
	
}
