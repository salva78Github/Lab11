package it.polito.tdp.bar.model;

import java.util.Random;

public class TestSimulator {

	private static final int NUMERO_EVENTI = 2000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * 
		 * Generare in modo random 2000 eventi di tipo “ARRIVO_GRUPPO_CLIENTI”,
		 * ognuno dei quali caratterizzato dei seguenti valori: 
		 * § time: istante temporale in cui si verificherà l’evento creato (in minuti).
		 * L’intervallo tra due eventi dovrà essere compreso tra 1 e 10 minuti;
		 * § num_persone: indica il numero di persone facenti parte del gruppo
		 * che vogliono sedersi al tavolo. Valore casuale compreso tra 1 e 10; 
		 * § durata: tempo in minuti indicante la permanenza dei clienti al tavolo
		 * del bar (tra 60 e 120 minuti); 
		 * § tolleranza: indica la tolleranza di ogni gruppo di clienti a restare al bar accomodandosi al bancone, nel
		 * caso in cui il tavolo richiesto non sia disponibile. Valore float tra
		 * 0.0 (se trovano il posto al tavolo restano al bar, altrimenti vanno
		 * via immediatamente insoddisfatti) e 0.9 (90% di probabilità di
		 * accomodarsi al bancone del bar anche senza potersi sedere al tavolo,
		 * restando comunque soddisfatti).
		 * 
		 */
		
		Simulator sim = new Simulator();
		int time = 0;
		for(int i = 0; i<NUMERO_EVENTI; i++){
			int numeroClienti = (int)(sim.getMinNumeroPersoneInUnGruppo()+Math.random()*sim.getMaxNumeroPersoneInUnGruppo());
			int tempoPermanenzaTavolo = (int)(sim.getMinTempoPermanenaInUnTavolo()+Math.random()*(sim.getMaxTempoPermanenaInUnTavolo()-sim.getMinTempoPermanenaInUnTavolo()));
			Random r = new Random();
			float tolleranza = r.nextFloat()*sim.getMaxTolleranza();
			
			GruppoClienti gc = new GruppoClienti(numeroClienti, tempoPermanenzaTavolo, tolleranza);
			
			time += (int)(sim.getMinTempoTraEventiSuccessivi()+Math.random()*(sim.getMaxTempoTraEventiSuccessivi()-sim.getMinTempoTraEventiSuccessivi()));
			sim.addGruppoClienti(gc, time);
		}
		
		sim.run();
		
		System.out.println("<numero totale di clienti> " + sim.getNumeroTotaleClienti());
		System.out.println("<numero clienti soddisfatti> " + sim.getNumeroClientiSoddisfatti());
		System.out.println("<numero di clienti insoddisfatti> " + sim.getNumeroClientiInsoddisfatti());
		
	}

}
