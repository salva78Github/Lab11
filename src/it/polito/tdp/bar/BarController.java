package it.polito.tdp.bar;

import java.util.Random;

import it.polito.tdp.bar.model.Event;
import it.polito.tdp.bar.model.GruppoCustomer;
import it.polito.tdp.bar.model.Simulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class BarController {
	
	private Simulation simulation;
	
    @FXML
    private TextArea txtRisultato;
    
    public void setModel(Simulation model) {
    	
    	this.simulation=model;
    	
    	// Aggiungo i tavoli al simulatore
    	simulation.addTable(10);
    	simulation.addTable(10);

    	simulation.addTable(8);
    	simulation.addTable(8);
    	simulation.addTable(8);
    	simulation.addTable(8);

    	simulation.addTable(6);
    	simulation.addTable(6);
    	simulation.addTable(6);
    	simulation.addTable(6);

    	simulation.addTable(4);
    	simulation.addTable(4);
    	simulation.addTable(4);
    	simulation.addTable(4);
    	simulation.addTable(4);
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	
    	simulation.cleanup();
    	
		// Creo un nuovo generatore di numeri casuali con seed iniziale 42.
		Random rn = new Random(42);

		long lastTimeOfArrival = 0;
		
		// Genero 2000 eventi.
		for (int t = 0; t < 2000; ++t) {
			
			long timeOfArrival = lastTimeOfArrival + 1 + rn.nextInt(9);
			long duration = (long) (60 + Math.random() * 60);
			float tolerance = rn.nextFloat();
			int numberOfPeople =  1 + rn.nextInt(9);
			
			// Genro un nuovo gruppo di clienti
			GruppoCustomer customerGroup = new GruppoCustomer(timeOfArrival, duration, tolerance, numberOfPeople);
			
			// Creo un nuovo evento e lo inserisco nella coda.
			Event e = new Event(timeOfArrival, Event.eventTypeEnum.ARRIVO_GRUPPO_CLIENTI, customerGroup);
			simulation.addEvent(e);
		}
		
		// Avvio la simulazion
		simulation.simulate();
		
		// Ottengo il risultato
		txtRisultato.appendText(simulation.getStats().toString());	
    }
    
    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Bar.fxml'.";
    }
}