package it.polito.tdp.bar;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.GruppoClienti;
import it.polito.tdp.bar.model.Simulator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class BarController {

	private static final int NUMERO_EVENTI = 2000;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="buttonSimulaId"
	private Button buttonSimulaId; // Value injected by FXMLLoader

	@FXML // fx:id="txtAreaResultId"
	private TextArea txtAreaResultId; // Value injected by FXMLLoader

	Simulator model;
	
    public void setModel(Simulator model) {
    	this.model = model;
    }

	
	@FXML
	void doSimulation(ActionEvent event) {
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

		txtAreaResultId.appendText("<numero totale di clienti> " + sim.getNumeroTotaleClienti() + "\n");
		txtAreaResultId.appendText("<numero clienti soddisfatti> " + sim.getNumeroClientiSoddisfatti() + "\n");
		txtAreaResultId.appendText("<numero di clienti insoddisfatti> " + sim.getNumeroClientiInsoddisfatti() + "\n \n \n \n");

		sim.cleanup();
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		assert buttonSimulaId != null : "fx:id=\"buttonSimulaId\" was not injected: check your FXML file 'Bar.fxml'.";
		assert txtAreaResultId != null : "fx:id=\"txtAreaResultId\" was not injected: check your FXML file 'Bar.fxml'.";

	}
}
