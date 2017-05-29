package it.polito.tdp.bar.model;

public class GruppoCustomer {
	static private int idGruppoCustomer = 0;
	private int id;
	private long timeArrival;
	private long durata;
	private float tolleranza;
	private int num_persone;
	private boolean soddisfatti;

	public GruppoCustomer(long timeOfArrival, long durata, float tolleranza, int num_persone) {
		this.id = ++idGruppoCustomer;
		this.timeArrival = timeOfArrival;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.num_persone = num_persone;
		this.soddisfatti = false;
	}

	@Override
	public String toString() {
		return "Customer#" + id;
	}

	public static int getIdGruppoCustomer() {
		return idGruppoCustomer;
	}

	public static void setIdGruppoCustomer(int idGruppoCustomer) {
		GruppoCustomer.idGruppoCustomer = idGruppoCustomer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimeArrival() {
		return timeArrival;
	}

	public void setTimeArrival(long timeArrival) {
		this.timeArrival = timeArrival;
	}

	public long getDurata() {
		return durata;
	}

	public void setDurata(long durata) {
		this.durata = durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}

	public int getNum_persone() {
		return num_persone;
	}

	public boolean isSoddisfatti() {
		return soddisfatti;
	}

	public void setSoddisfatti(boolean soddisfatti) {
		this.soddisfatti = soddisfatti;
	}

	public void setNum_persone(int num_persone) {
		this.num_persone = num_persone;
	}

}
