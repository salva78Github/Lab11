package it.polito.tdp.bar.test;

import java.util.Random;

public class TestTolleranza {

	public static void main(String[] args) {
		Random r = new Random();
		float tolleranza = r.nextFloat()*0.9f;
		//double x = Math.random();
		System.out.println("<main> tolleranza: " + tolleranza);
		int probability = (int)(tolleranza*100);
		System.out.println("<main> probability: " + probability);
		
		int y = (int)(Math.random()*100);
		System.out.println("<main> y: " + y);
		System.out.println("<main> scelta: " + ((y<=probability)?"A":"B"));
	

	}

}
