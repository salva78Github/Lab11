package it.polito.tdp.bar.model;

public class Stats {

	private int numero_totale_clienti;
	private int numero_clienti_soddisfatti;
	private int numero_clienti_insoddisfatti;
	private int numero_totale_gruppi = 0;
	private int numTavoli = 0;
	private int gruppi_soddisfatti = 0;
	private int gruppi_insoddisfatti = 0;

	public void aggiungiClienti(GruppoCustomer customer) {

		numero_totale_gruppi++;
		numero_totale_clienti += customer.getNum_persone();

		if (customer.isSoddisfatti()) {
			numero_clienti_soddisfatti += customer.getNum_persone();
			gruppi_soddisfatti++;

		} else {
			numero_clienti_insoddisfatti += customer.getNum_persone();
			gruppi_insoddisfatti++;
		}

	}

	public String stampale() {

		String ris = "Numero totale Gruppi:  " + this.numero_totale_gruppi + "\n";
		ris += "Numero totale Gruppi Soddisfatti:  " + this.gruppi_soddisfatti + "\n";
		ris += "Numero totale Gruppi Insoddisfatti:  " + this.gruppi_insoddisfatti + "\n";
		ris += "Numero totale Clienti:  " + this.numero_totale_clienti + "\n";
		ris += "Numero totale Clienti Soddisfatti:  " + this.numero_clienti_soddisfatti + "\n";
		ris += "Numero totale Clienti Insoddisfatti:  " + this.numero_clienti_insoddisfatti + "\n";
		ris += "Numero tavoli con cui lavora il simulatore:  " + this.numTavoli + "\n\n\n";

		return ris;
	}

	public void setNumTavoli(int numero) {
		numTavoli = numero;
	}

	public void cleanup() {
		this.numero_totale_gruppi = 0;
		this.gruppi_soddisfatti = 0;
		this.gruppi_insoddisfatti = 0;
		
		this.numero_totale_clienti = 0; 
		this.numero_clienti_soddisfatti = 0;
		this.numero_clienti_insoddisfatti = 0;
	}

	@Override
	public String toString() {

		String ris = "Numero totale Gruppi:  " + this.numero_totale_gruppi + "\n";
		ris += "Numero totale Gruppi Soddisfatti:  " + this.gruppi_soddisfatti + "\n";
		ris += "Numero totale Gruppi Insoddisfatti:  " + this.gruppi_insoddisfatti + "\n";
		ris += "Numero totale Clienti:  " + this.numero_totale_clienti + "\n";
		ris += "Numero totale Clienti Soddisfatti:  " + this.numero_clienti_soddisfatti + "\n";
		ris += "Numero totale Clienti Insoddisfatti:  " + this.numero_clienti_insoddisfatti + "\n";
		ris += "Numero tavoli con cui lavora il simulatore:  " + this.numTavoli + "\n\n\n";

		return ris;
	}
	
	
}
