package it.polito.tdp.bar.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.bar.exception.BarManagementException;
import it.polito.tdp.bar.exception.NoTableAvailableException;
import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	// Simulation parameters
	private final static int MIN_TEMPO_TRA_EVENTI_SUCCESSIVI = 1;
	private final static int MAX_TEMPO_TRA_EVENTI_SUCCESSIVI = 10;
	private final static int MIN_NUMERO_PERSONE_IN_UN_GRUPPO = 1;
	private final static int MAX_NUMERO_PERSONE_IN_UN_GRUPPO = 10;
	private final static int MIN_TEMPO_PERMANENA_IN_UN_TAVOLO = 60;
	private final static int MAX_TEMPO_PERMANENA_IN_UN_TAVOLO = 120;
	private final static float MIN_TOLLERANZA = 0.0f;
	private final static float MAX_TOLLERANZA = 0.9f;


	// World model
	private PriorityQueue<GruppoClienti> waitingCustomersGroups;
	private Map<Integer, Tavolo> tavoli;

	// Measures of Interest
	private int numeroTotaleClienti;
	private int numeroClientiSoddisfatti;
	private int numeroClientiInsoddisfatti;

	// Event queue
	private PriorityQueue<Event> queue;

	public Simulator() {
		init();
		this.queue = new PriorityQueue<Event>();
	}

	public void init() {
		this.tavoli = new HashMap<Integer, Tavolo>();
		this.tavoli.put(1, new Tavolo(1, 10));
		this.tavoli.put(2, new Tavolo(2, 10));
		this.tavoli.put(3, new Tavolo(3, 8));
		this.tavoli.put(4, new Tavolo(4, 8));
		this.tavoli.put(5, new Tavolo(5, 8));
		this.tavoli.put(6, new Tavolo(6, 8));
		this.tavoli.put(7, new Tavolo(7, 6));
		this.tavoli.put(8, new Tavolo(8, 6));
		this.tavoli.put(9, new Tavolo(9, 6));
		this.tavoli.put(10, new Tavolo(10, 6));
		this.tavoli.put(11, new Tavolo(11, 4));
		this.tavoli.put(12, new Tavolo(12, 4));
		this.tavoli.put(13, new Tavolo(13, 4));
		this.tavoli.put(14, new Tavolo(14, 4));
		this.tavoli.put(15, new Tavolo(15, 4));
	}

	public void run() {
		while (!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			System.out.println("<run> " + e);

			e.getEventType().processEvent(this, e);

		}

	}

	public void cleanup(){
		init();
		this.queue.clear();
		this.numeroTotaleClienti=0;
		this.numeroClientiSoddisfatti=0;
		this.numeroClientiInsoddisfatti=0;
		
	}
	
	public void addGruppoClienti(GruppoClienti gc, int time) {
		gc.setStatus(GruppoClienti.Status.NEW);
		Event e = new Event(time, gc, EventType.ATTESA);
		this.queue.add(e);
		this.numeroTotaleClienti += gc.getNumeroClienti();
	}

	/**
	 * @return the numeroTotaleClienti
	 */
	public int getNumeroTotaleClienti() {
		return numeroTotaleClienti;
	}

	/**
	 * @return the numeroClientiSoddisfatti
	 */
	public int getNumeroClientiSoddisfatti() {
		return numeroClientiSoddisfatti;
	}

	/**
	 * @return the numeroClientiInsoddisfatti
	 */
	public int getNumeroClientiInsoddisfatti() {
		return numeroClientiInsoddisfatti;
	}

	public Tavolo retrieveAvailableTable(GruppoClienti gc) throws BarManagementException {
		// TODO Auto-generated method stub
		int numeroClienti = gc.getNumeroClienti();
		for (Tavolo t : this.tavoli.values()) {
			if (t.isFree() && t.getPosti() >= numeroClienti) {
				return t;
			}
		}
		throw new NoTableAvailableException(
				"non ci sono tavoli disponibili per un gruppo di " + gc.getNumeroClienti() + " clienti");

	}
	
	/**
	 * Associare ad ogni gruppo di clienti in arrivo il tavolo libero più piccolo che sia in grado di accoglierli.
	 * @param gc
	 * @return
	 * @throws BarManagementException
	 */
	public Tavolo retrieveAvailableTableStrateg1(GruppoClienti gc) throws BarManagementException {
		// TODO Auto-generated method stub
		int numeroClienti = gc.getNumeroClienti();
		List<Tavolo> tavoliList = new ArrayList<Tavolo>(this.tavoli.values());
		Collections.sort(tavoliList, new TavoliComparatorByNumeroPosti());
		for (Tavolo t : tavoliList) {
			if (t.isFree() && t.getPosti() >= numeroClienti) {
				return t;
			}
		}
		throw new NoTableAvailableException(
				"non ci sono tavoli disponibili per un gruppo di " + gc.getNumeroClienti() + " clienti");

	}
	
	/**
	 * Far accomodare i clienti ai tavoli in modo tale da occupare almeno il 50% dei posti disponibili del tavolo. 
	 * Altrimenti cercare di indirizzarli verso il bancone.
	 * 
	 * @param gc
	 * @return
	 * @throws BarManagementException
	 */
	public Tavolo retrieveAvailableTableStrateg2(GruppoClienti gc) throws BarManagementException {
		// TODO Auto-generated method stub
		int numeroClienti = gc.getNumeroClienti();
		List<Tavolo> tavoliList = new ArrayList<Tavolo>(this.tavoli.values());
		Collections.sort(tavoliList, new TavoliComparatorByNumeroPosti());
		for (Tavolo t : tavoliList) {
			if (t.isFree() && (0.5*t.getPosti()) <= numeroClienti) {
				return t;
			}
		}
		throw new NoTableAvailableException(
				"non ci sono tavoli disponibili per un gruppo di " + gc.getNumeroClienti() + " clienti");

	}
	

	public void reserveTable(Tavolo t) {
		Tavolo tavolo = this.tavoli.get(t.getCode());
		tavolo.setFree(false);
	}

	public void addEventForTavoloOccupato(int time, GruppoClienti gc) {
		queue.add(new Event(time, gc, EventType.LIBERA_TAVOLO)) ;
			
	}

	public void addEventForClientiAlBancone(GruppoClienti gc) {
		this.numeroClientiSoddisfatti += gc.getNumeroClienti();
	}

	public void releaseTable(Tavolo t) {
		// TODO Auto-generated method stub
		int code = t.getCode();
	//	System.out.println("<releaseTable> " + code);
		Tavolo tavolo = this.tavoli.get(code);
		tavolo.setFree(true);
		
	}

	public void addEventForClientiCheLascianoTavolo(GruppoClienti gc) {
		this.numeroClientiSoddisfatti += gc.getNumeroClienti();
	}

	public void incrementaClientiInsoddisfatti(GruppoClienti gc) {
		this.numeroClientiInsoddisfatti += gc.getNumeroClienti();
	}

	public void addEventForClientiInsoddisfatti(int time, GruppoClienti gc) {
		queue.add(new Event(time, gc, EventType.FUORI_SENZA_CONSUMARE)) ;
	}
	/**
	 * @return the minTempoTraEventiSuccessivi
	 */
	public int getMinTempoTraEventiSuccessivi() {
		return MIN_TEMPO_TRA_EVENTI_SUCCESSIVI;
	}
	
	/**
	 * @return the maxTempoTraEventiSuccessivi
	 */
	public int getMaxTempoTraEventiSuccessivi() {
		return MAX_TEMPO_TRA_EVENTI_SUCCESSIVI;
	}
	
	/**
	 * @return the minNumeroPersoneInUnGruppo
	 */
	public int getMinNumeroPersoneInUnGruppo() {
		return MIN_NUMERO_PERSONE_IN_UN_GRUPPO;
	}
	
	/**
	 * @return the maxNumeroPersoneInUnGruppo
	 */
	public int getMaxNumeroPersoneInUnGruppo() {
		return MAX_NUMERO_PERSONE_IN_UN_GRUPPO;
	}
	
	/**
	 * @return the minTempoPermanenaInUnTavolo
	 */
	public int getMinTempoPermanenaInUnTavolo() {
		return MIN_TEMPO_PERMANENA_IN_UN_TAVOLO;
	}
	
	/**
	 * @return the maxTempoPermanenaInUnTavolo
	 */
	public int getMaxTempoPermanenaInUnTavolo() {
		return MAX_TEMPO_PERMANENA_IN_UN_TAVOLO;
	}
	
	/**
	 * @return the minTolleranza
	 */
	public float getMinTolleranza() {
		return MIN_TOLLERANZA;
	}
	
	/**
	 * @return the maxTolleranza
	 */
	public float getMaxTolleranza() {
		return MAX_TOLLERANZA;
	}
		
	
}
