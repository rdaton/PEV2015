package interfaz;

import java.util.ArrayList;
import java.util.List;

import control.Funcion1;

//me defino una clase de thread para usar más de un cpu
public class rangoWorker implements Runnable
		{
			Funcion1 unaFuncion1=null;
			STring elPeor=new ArrayList();
			List<List<Object>> resultados=null;
			int torneo;
			int tam_pob;
			int num_iter;
			double pCruces; 
			double pMut; 
			int tCruce; 
			int tSeleccion;
			int tMutacion;
			boolean elitismo;
			
			public rangoWorker(int torneo, int tam_pob, int num_iter, double pCruces, double pMut, int tCruce, int tSeleccion, int tMutacion, boolean elitismo)
			{
				this.torneo=torneo;
				this.tam_pob=tam_pob;
				this.num_iter=num_iter;
				this.pCruces=pCruces; 
				this.pMut=pMut; 
				this.tCruce=tCruce; 
				this.tSeleccion=tSeleccion;
				this.tMutacion=tMutacion;
				this.elitismo=elitismo;
			}
			
			public void run()
			{
				Funcion1 unaFuncion1 = new Funcion1(torneo, tam_pob, num_iter, pCruces, pMut, tCruce, tSeleccion, tMutacion, elitismo);
				elPeor.add(unaFuncion1.getMejorEntrePeores());
				resultados.add(unaFuncion1.dameResultados());
			}
			
			public Object[] dameResultados()
			{
				Object[] unArray= {elPeor,resultados};
				return unArray ;
				
			}
			
		}