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
	
	public  void muta ()
	{
		if (bit==0)
			bit=1;
		else
			bit=1;
		
		
	}
	
	public Gen clone(){
		Gen unGen=new Gen(bit);
		return unGen;
	}
	
}