package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.Exhibitions;

public class Simulazione {
	int anno;
	SimpleDirectedGraph<Exhibitions, DefaultEdge> grafo;
	int numero;
	private PriorityQueue<Evento> coda;
	Exhibitions inizio;
	ArrayList<Studente> studente=new ArrayList<Studente>();


	public Simulazione(int anno, SimpleDirectedGraph<Exhibitions, DefaultEdge> grafo,int numero){
		this.anno =anno;
		this.grafo=grafo;
		this.coda = new PriorityQueue<Evento>();
		this.numero=numero;
		System.out.println(grafo.edgeSet().size());
		
		}
	
	public Exhibitions getInizio(){
		if(inizio==null){
			for (Exhibitions e:grafo.vertexSet())
				if (e.getBegin()==anno){
					inizio=e;
			return inizio;
				}
		}
		System.out.println(inizio);
		return inizio;
			
		
	}
	public void inserisci(int t,Exhibitions e,Studente i){
		Evento a = new Evento(t, e, i);
		coda.add(a);
	}

	
	
	public void simula(){
for(int i =0;i<numero;i++){
	Studente s=new Studente(i);
	studente.add(s);
	this.inserisci(0, this.getInizio(), s);
				}

while (!coda.isEmpty()) {
	Evento e = coda.poll();
	
	for(ArtObject a:e.getE().getObjects()){
		if (!e.getS().liosta.contains(a))
			e.getS().liosta.add(a);
		System.out.println(e.getE().getObjects());
	}
	
	double number=Math.random();
	if(Graphs.neighborListOf(grafo, e.getE()).size()>0){
//	Random r=new Random();
//	int f=r.nextInt(grafo.outgoingEdgesOf(e.getE()).size());
//	Graphs.neighborListOf(grafo, e.getE());
//	List<DefaultEdge> i= new ArrayList<>( grafo.outgoingEdgesOf(e.getE()));
//	System.out.println(e.getE()+" "+Graphs.neighborListOf(grafo, e.getE()).size()+" ");
	number=number*Graphs.neighborListOf(grafo, e.getE()).size();
	this.inserisci(e.getT()+1, 	Graphs.neighborListOf(grafo, e.getE()).get((int) number), e.getS());
	}
	
}



		
		
	
			
		
	}
	public List<Studente> getStudenti(){
		Collections.sort(studente);
		return studente;
	}
	
	
}
