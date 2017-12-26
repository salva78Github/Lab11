package it.polito.tdp.bar.model;

public class GruppoClienti {

	enum Status {
		NEW, SERVITO, OUT
	}

	private final int numeroClienti;
	private Tavolo tavolo;
	private final int tempoPermanenzaTavolo; // in minuti
	private final float tolleranza;
	private Status status;

	/**
	 * @param numeroClienti
	 */
	public GruppoClienti(int numeroClienti, int tempoPermanenzaTavolo, float tolleranza) {
		this.numeroClienti = numeroClienti;
		this.tempoPermanenzaTavolo = tempoPermanenzaTavolo;
		this.tolleranza = tolleranza;
	}

	
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}


	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}


	/**
	 * @return the tavolo
	 */
	public Tavolo getTavolo() {
		return tavolo;
	}

	/**
	 * @param tavolo
	 *            the tavolo to set
	 */
	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	/**
	 * @return the numeroClienti
	 */
	public int getNumeroClienti() {
		return numeroClienti;
	}

	/**
	 * @return the tempoPermanenzaTavolo
	 */
	public int getTempoPermanenzaTavolo() {
		return tempoPermanenzaTavolo;
	}

	/**
	 * @return the tolleranza
	 */
	public float getTolleranza() {
		return tolleranza;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GruppoClienti [numeroClienti=" + numeroClienti + ", tavolo=" + tavolo + ", tempoPermanenzaTavolo="
				+ tempoPermanenzaTavolo + ", tolleranza=" + tolleranza + "]";
	}


	public void rilasciaTavolo() {
		// TODO Auto-generated method stub
		setTavolo(null);
	}

}
