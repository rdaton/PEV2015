package individuo;

public class Gen_f1 extends Gen {

	public Gen_f1()
	{			
	};
	public Gen_f1(int g)
	{
		bits=new Integer[1];
		bits[0]=new Integer(g);
	}
	
	Gen clone_aux()
	{
		Gen unGen=new Gen_f1();
		unGen.bits=new Integer[1];
		return unGen;		
	}
	
	public  void muta ()
	{
		if ((Integer)bits[0]==0)
			bits[0]=1;
		else
			bits[0]=1;				
	}
	
	
	
	
	
}
