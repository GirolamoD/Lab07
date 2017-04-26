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
//		for(String s : parole){
//			List<String> simili = dao.getAllSimilarWords(s, s.length());
//			for(String st : simili){
//				grafo.addEdge(s, st);
//			}
//		}
		for(int i=0 ; i<parole.size() ; i++)
			for(int j=i+1 ; j<parole.size() ; j++){
				String prima = parole.get(i);
				String seconda = parole.get(j);
				for(int k=0 ; k<numeroLettere ; k++){
					String prove = seconda.substring(0, k)+"."+seconda.substring(k+1, seconda.length());
					if(prima.matches(prove))
						grafo.addEdge(prima, seconda);
				}
			}
		
		return new ArrayList<String>();
	}

	public List<String> displayNeighbours(String parolaInserita) {
		List<String> result = new ArrayList<String>();
		for(String s : grafo.vertexSet())
			if(grafo.containsEdge(parolaInserita, s))
				result.add(s);
		return result;
	}

	public String findMaxDegree() {
		int max = 0 ;
		String result = "" ;
		for(String s : grafo.vertexSet())
			if(grafo.degreeOf(s)>max){
				max = grafo.degreeOf(s);
				result=s ;
			}
		return result;
	}
}
