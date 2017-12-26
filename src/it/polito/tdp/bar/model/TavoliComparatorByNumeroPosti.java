package it.polito.tdp.bar.model;

import java.util.Comparator;

public class TavoliComparatorByNumeroPosti implements Comparator<Tavolo> {

	@Override
	public int compare(Tavolo o1, Tavolo o2) {
		// TODO Auto-generated method stub
		return (o1.getPosti()-o2.getPosti());
	}



}
