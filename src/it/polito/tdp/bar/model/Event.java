package it.polito.tdp.bar.model;

import it.polito.tdp.bar.exception.BarManagementException;

public class Event implements Comparable<Event> {

	enum EventType {
		ATTESA {
			@Override
			void processEvent(Simulator sim, Event e) {
				GruppoClienti gc = e.getGp();

				// ci sono tavoli liberi con numero di posti adeguati?
				try {
					Tavolo t = sim.retrieveAvailableTableStrateg2(gc);
					// sì
					// assegno il tavolo
					gc.setTavolo(t);
					// occupo tavolo
					sim.reserveTable(t);
					// aggiungo evento
					int time = e.getTime() + ((int) (Math.random() * 60) + 60);
					sim.addEventForTavoloOccupato(time, gc);

				} catch (BarManagementException bme) {
					// no
					float tolleranza = gc.getTolleranza();

					int probability = (int) (tolleranza * 100);
					int decisione = (int) (Math.random() * 100);
					if (decisione <= probability) {
						sim.addEventForClientiAlBancone(gc);
					} else {
						sim.addEventForClientiInsoddisfatti(e.getTime(), gc);
					}
				}

			}
		},
		LIBERA_TAVOLO {
			@Override
			void processEvent(Simulator sim, Event e) {
				GruppoClienti gc = e.getGp();
				System.out.println("<LIBERA_TAVOLO.processEvent> gc " + gc);
				sim.releaseTable(gc.getTavolo());
				gc.rilasciaTavolo();
				sim.addEventForClientiCheLascianoTavolo(gc);

			}
			/*
			 * }, VA_AL_BANCONE {
			 * 
			 * @Override void processEvent(Simulator sim, Event e) { // TODO
			 * Auto-generated method stub
			 * 
			 * }
			 */
		},
		FUORI_SENZA_CONSUMARE {
			@Override
			void processEvent(Simulator sim, Event e) {
				// TODO Auto-generated method stub
				GruppoClienti gc = e.getGp();
				sim.incrementaClientiInsoddisfatti(gc);
			}
		};

		abstract void processEvent(Simulator sim, Event e);

	};

	private int time;
	private GruppoClienti gp;
	private EventType eventType;

	/**
	 * @param time
	 * @param gp
	 * @param eventType
	 */
	public Event(int time, GruppoClienti gp, EventType eventType) {
		this.time = time;
		this.gp = gp;
		this.eventType = eventType;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @return the gp
	 */
	public GruppoClienti getGp() {
		return gp;
	}

	/**
	 * @return the eventType
	 */
	public EventType getEventType() {
		return eventType;
	}

	@Override
	public int compareTo(Event other) {
		// TODO Auto-generated method stub
		return this.time - other.time;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [time=" + time + ", gp=" + gp + ", eventType=" + eventType + "]";
	}

}
