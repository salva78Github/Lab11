package it.polito.tdp.bar.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Simulation {

	private Random rd;
	private Stats statistiche;
	private Queue<Event> eventList;
	private Map<Integer, Table> tables;

	public Simulation() {
		rd = new Random(42);
		statistiche = new Stats();
		eventList = new PriorityQueue<Event>();
		tables = new HashMap<Integer, Table>();
	}

	public void addEvent(Event e) {
		eventList.add(e);
	}

	public void simulate() {
		while (!eventList.isEmpty()) {
			doSimulationStep();
		}
	}

	public long doSimulationStep() {

		if (eventList.isEmpty())
			return Long.MAX_VALUE;

		Event e = eventList.remove();

		switch (e.eventType) {

		case ARRIVO_GRUPPO_CLIENTI:

			Table table = findAvailableTable(e.getCustomer().getNum_persone());

			if (table != null) {

				// Assegno il tavolo ai clienti
				table.setIdCustomers(e.getCustomer().getId());
				table.setLibero(false);
				e.getCustomer().setSoddisfatti(true);

				// Creo un nuovo evento per simulare i clienti che escono dal
				// locale
				Event eventCustomersLeave = new Event(e.getTimeStamp() + e.getCustomer().getDurata(),
						Event.eventTypeEnum.PARTENZA_GRUPPO_CLIENTI, e.getCustomer());

				addEvent(eventCustomersLeave);

				System.out.println("Minuto: " + e.getTimeStamp() + " - Tavolo " + table.getIdTable() + " (Capienza: "
						+ table.getNumPostiASedere() + ") Occupato da Gruppo  " + e.getCustomer().getId() + " (Gruppo da "
						+ e.getCustomer().getNum_persone() + " Persone). Se ne vanno tra " + e.getCustomer().getDurata()
						+ " minuti");

			} else {

				float tolleranza = e.getCustomer().getTolleranza();
				float probabilita = rd.nextFloat();

				if (probabilita <= tolleranza) {

					// I clienti vengono serviti al bancone
					e.getCustomer().setSoddisfatti(true);
					System.out.println("Minuto: " + e.getTimeStamp() + " - Il gruppo  " + e.getCustomer().getId() + " (Gruppo da "
							+ e.getCustomer().getNum_persone() + " Persone) e' servito al bancone");

				} else {

					// I clienti escono dal locale non soddisfatti.
					e.getCustomer().setSoddisfatti(false);
					System.out.println("Minuto: " + e.getTimeStamp() + " - Il gruppo  " + e.getCustomer().getId() + " (Gruppo da "
							+ e.getCustomer().getNum_persone() + " Persone) se n'e' andato insoddisfatto");
				}
			}

			statistiche.aggiungiClienti(e.getCustomer());
			break;

		case PARTENZA_GRUPPO_CLIENTI:

			// I clienti lasciano il locale.
			// Libero il tavolo.
			Table freeTable = this.trovaTavolo(e.getCustomer().getId());
			freeTable.setLibero(true);
			freeTable.setIdCustomers(-1);

			System.out.println("Minuto: " + e.getTimeStamp() + " - Tavolo " + freeTable.getIdTable() + " (Capienza: "
					+ freeTable.getNumPostiASedere() + ") Liberato da gruppo " + e.getCustomer().getId()
					+ ". Ritorna disponibile!");

			break;

		default:
			throw new IllegalArgumentException();

		}

		return e.getTimeStamp();
	}

	private Table findAvailableTable(int numPersone) {

		int postiTavoloMin = Integer.MAX_VALUE;
		Table returnTable = null;

		// Itero su tutti i tavoli
		for (Table table : new LinkedList<Table>(tables.values())) {

			// Controllo i requisiti minimi
			if (table.isLibero() && numPersone >= 0.5 * table.getNumPostiASedere()) {

				// Cerco il tavolo con il minimo numero di posti a sedere.
				if (postiTavoloMin > table.getNumPostiASedere()) {
					returnTable = table;
					postiTavoloMin = table.getNumPostiASedere();
				}
			}
		}

		return returnTable;
	}

	public Table trovaTavolo(int idCustomers) {

		for (Table table : tables.values()) {
			if (table.getIdCustomers() == idCustomers)
				return table;
		}
		return null;
	}

	public void addTable(int numPosti) {
		Table temp = new Table(numPosti);
		tables.put(temp.getIdTable(), temp);
		statistiche.setNumTavoli(tables.size());
	}

	public Stats getStats() {
		return this.statistiche;
	}

	public void cleanup() {
		eventList.clear();
		statistiche.cleanup();
	}

}