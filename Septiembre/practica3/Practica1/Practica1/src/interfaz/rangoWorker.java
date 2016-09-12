package interfaz;

import java.util.ArrayList;
import java.util.List;

import logica.TipoCruce;

import control.Funcion1;

//me defino una clase de thread para usar más de un cpu
public class rangoWorker implements Runnable
		{
			Funcion1 unaFuncion1=null;
			String elMejor=null;
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
			List<List> structResultados;
			
			public rangoWorker(List<List> structResultados,int torneo, int tam_pob, int num_iter, double pCruces, double pMut, int tCruce, int tSeleccion, int tMutacion, boolean elitismo)
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
				this.structResultados=structResultados;
			}
			
			public void run()
			{
				Funcion1 unaFuncion1 = new Funcion1(torneo, tam_pob, num_iter, pCruces, pMut, tCruce, tSeleccion, tMutacion, elitismo);
				elMejor=unaFuncion1.getMejorEntreMejores();
				resultados=unaFuncion1.dameResultados();
				
				StringBuffer unString=new StringBuffer().append(elMejor).append("\n");
				unString.append("PARAMÉTROS UTILIZADOS: ").append("\n");
				unString.append(" nTorneo: ").append(torneo).append("\n");
				unString.append(" Generaciones: ").append(num_iter).append("\n");
				unString.append(" Prob. Cruces: ").append(pCruces).append("\n");
				unString.append(" Prob. Mut: ").append(pMut).append("\n");
				unString.append(" Tipo Cruce ").append(logica.TipoCruce.values()[tCruce].toString()).append("\n");
				unString.append(" Tipo Selecc. ").append(logica.TipoSeleccion.values()[tSeleccion].toString()).append("\n");
				unString.append(" Tipo Mutac. ").append(logica.TipoMutacion.values()[tMutacion].toString()).append("\n");
				unString.append("Elitismo: ").append(elitismo? "Sí" : "No" ).append("\n");
				
				synchronized(structResultados)
				{
					structResultados.get(0).add(unString.toString());
					structResultados.get(1).add(resultados);
					
				}
			}
			
			
			
		}