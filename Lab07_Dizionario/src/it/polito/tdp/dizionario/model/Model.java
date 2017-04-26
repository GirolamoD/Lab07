package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	UndirectedGraph<String,DefaultEdge> grafo = new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);

	public List<String> createGraph(int numeroLettere) {
		WordDAO dao = new WordDAO();
		List<String> parole = dao.getAllWordsFixedLength(numeroLettere);
		for(String s : parole)
			grafo.addVertex(s);
		for(String s : parole){
			List<String> simili = dao.getAllSimilarWords(s, s.length());
			for(String st : simili){
				grafo.addEdge(s, st);
			}
		}
		return new ArrayList<String>();
	}

	public List<String> displayNeighbours(String parolaInserita) {
		System.out.println("Model -- TODO");
		return new ArrayList<String>();
	}

	public String findMaxDegree() {
		System.out.println("Model -- TODO");
		return "";
	}
}
