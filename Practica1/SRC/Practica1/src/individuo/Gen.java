package individuo;

public abstract  class Gen 
{
	Object[] bits;
	abstract public  void muta ();
	abstract Gen clone_aux();
	
	
	public Object[] toInt()
	{
		return bits;
	}
	
	public Gen clone()
	{
		Gen unGen=clone_aux();
		for (int i=0;i<bits.length;i++)
		{
			unGen.bits[i]=bits[i];
		}
		return unGen;
	}
	
}
