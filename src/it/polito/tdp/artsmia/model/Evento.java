package it.polito.tdp.artsmia.model;

import it.polito.tdp.artsmia.db.Exhibitions;


public class Evento implements Comparable<Evento> {
	private int t ;
	private Exhibitions e;
	private Studente s;
	public Evento(int t, Exhibitions e, Studente s) {
		super();
		this.t = t;
		this.e = e;
		this.s = s;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public Exhibitions getE() {
		return e;
	}

	public void setE(Exhibitions e) {
		this.e = e;
	}

	public Studente getS() {
		return s;
	}

	public void setS(Studente s) {
		this.s = s;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.getT()-o.getT();
	}



}
