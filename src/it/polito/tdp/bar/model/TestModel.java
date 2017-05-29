package it.polito.tdp.bar.model;

import java.util.Random;

public class TestModel {

	public static void main(String[] args) {

    	Simulation simulation = new Simulation();

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

		System.out.print("\n\n*** SIMULATION STARTS ***\n\n");
		
		simulation.simulate();

		System.out.print("\n\n*** SIMULATION ENDS ***\n\n");

		System.out.println("\n\n\n STATISTICHE \n");

		System.out.println(simulation.getStats().stampale());

	}

}
