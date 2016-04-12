package individuo;

public class Gen_f2 extends Gen {

	public Gen_f2()
	{			
	};
	
	public Gen_f2(int g, int h)
	{
		bits=new Integer[2];
		bits[0]=new Integer(g);
		bits[1]=new Integer(h);
	}
	
	Gen clone_aux()
	{
		Gen unGen=new Gen_f1();
		unGen.bits=new Integer[2];
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
