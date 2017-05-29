package it.polito.tdp.bar.model;

public class Event implements Comparable<Event> {
	
	public enum eventTypeEnum {
		ARRIVO_GRUPPO_CLIENTI, PARTENZA_GRUPPO_CLIENTI
	};

	public eventTypeEnum eventType;
	
	private long timeStamp;
	private GruppoCustomer customer;

	public Event(long timeStamp, eventTypeEnum eventType, GruppoCustomer customer) {
		this.timeStamp = timeStamp;
		this.eventType = eventType;
		this.customer = customer;
	}

	
	public long getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}


	public GruppoCustomer getCustomer() {
		return customer;
	}


	public void setCustomer(GruppoCustomer customer) {
		this.customer = customer;
	}


	@Override
	public int compareTo(Event e) {
		return Long.compare(this.timeStamp, e.timeStamp);
	}
}
