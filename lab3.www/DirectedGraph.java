
import java.util.*;

public class DirectedGraph<E extends Edge> {
	private List[] edgeList;
	private Listan<E> l;


	public DirectedGraph(int noOfNodes) {
		Listan<E> l = new Listan(noOfNodes);
	}

	public void addEdge(E e) {
		l.addEdge(e);
	}

	public Iterator<E> shortestPath(int from, int to) {
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
  
