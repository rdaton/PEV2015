package individuo;

public class Gen_f1 extends Gen {

	public Gen_f1()
	{			
	};
	public Gen_f1(Integer g)
	{
		bit=new Integer(g);
	}
	
	public Integer toInt()
	{
		return (Integer)bit;
	}
	Gen clone_aux()
	{
		Gen_f1 unGen=new Gen_f1((Integer)bit);		
		return unGen;		
	}
	
	public  void muta ()
	{
		if ((Integer)bit==0)
			bit=1;
		else
			bit=0;				
	}
	
	
	
	
	
}
