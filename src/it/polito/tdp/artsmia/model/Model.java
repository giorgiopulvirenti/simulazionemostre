package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;
import it.polito.tdp.artsmia.db.Exhibitions;

public class Model {
	SimpleDirectedGraph<Exhibitions, DefaultEdge> grafo;
	List<Integer> anni;
	ArtsmiaDAO dao = new ArtsmiaDAO();
	List<Exhibitions> exhibitions;
	List<ArtObject> objects;

	public Model() {
		// dao=new ArtsmiaDAO();

	}

	public List<Exhibitions> getExhibitions() {
		if (this.exhibitions == null)
			exhibitions = dao.listExibition();
		return this.exhibitions;

	}

	public List<ArtObject> getObject() {
		if (this.objects == null)
			this.objects = dao.listObject();
		return this.objects;

	}

	public List<Integer> listAnni() {
		if (anni == null) {
			anni = new ArrayList();
			for (Exhibitions e : this.getExhibitions())
				if (!anni.contains(e))
					anni.add(e.getBegin());

		}
		return anni;
	}

	public void creaGrafo(Integer value) {

		grafo = new SimpleDirectedGraph<Exhibitions, DefaultEdge>(DefaultEdge.class);

		for (Exhibitions e : this.getExhibitions())
			if (e.getBegin() >= value)
				grafo.addVertex(e);
		// Graphs.addAllVertices(grafo, this.getExhibitions());

		for (Exhibitions e1 : grafo.vertexSet()) {
			for (Exhibitions e2 : grafo.vertexSet()) {
				if (!e1.equals(e2) && e1.getBegin() < e2.getBegin() && e2.getBegin() < e1.getEnd())
					grafo.addEdge(e1, e2);
			
			}

		}
		// System.out.println(grafo);

	}

	public String isConnected(){
		String s="";
		ConnectivityInspector con= new ConnectivityInspector(grafo);
		List<Set<Exhibitions>> lista= con.connectedSets();
		if (lista.size()>1)
			s="grafo non connesso";
		if (lista.size()==1)
			s="grafo connesso";
		return s;
			
	}
	public String trovaMaggiorNumeroOpere() {
		this.getExhibitions();
		this.getObject();
		dao.connectionsObjectExhibitions();

		int i = Integer.MIN_VALUE;
		Exhibitions e = null;
		System.out.println(grafo.vertexSet());
		for (Exhibitions e1 : grafo.vertexSet())
			if (e1.getObjects().size() > i) {
				i = e1.getObjects().size();
				e = e1;
			}

		return e.toString() + " numero di opere: " + i;
	}

	public String simula(Integer value,int numero) {
		String ciao="";
		Simulazione s=new Simulazione(value ,grafo,numero);
		s.simula();
		
		for(Studente f :s.getStudenti())
			ciao+=f.toString()+" "+f.liosta.size()+"\n";
		
		return ciao;
		
		
	}

}
