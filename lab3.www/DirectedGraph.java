
import java.util.*;

public class DirectedGraph<E extends Edge> {
	private List[] edgeList;
	private Graph<E> l;

	/**
	 * Constructor creates a new graph with size noOfNodes
	 * @param noOfNodes The number of nodes the graph will contain
	 */
	public DirectedGraph(int noOfNodes) {
		l = new Graph(noOfNodes);
	}

	/**
	 * Adds an edge e to the graph, e has to have a source node and a destination node in the graph.
	 * @param e The edge to be added
	 */
	public void addEdge(E e) {
		l.addEdge(e);
	}

	/**
	 * Finds the shortest path using the Dijkstra algorithm
	 * @param from The start of the path to be found
	 * @param to The end of the path to be found
	 * @return An iterator over the edges of the path to be found
	 */
	public Iterator<E> shortestPath(int from, int to) {
		CompDijkstraPath<E> cdp = new CompDijkstraPath<>();
		LinkedList<E> ll = cdp.CompDijkstraPath(l,from,to);
		return ll.listIterator();
	}

	/**
	 * Finds the minimum spanning tree of a graph.
	 * @return An iterator over the edges of the minimum spanning tree
	 */
	public Iterator<E> minimumSpanningTree() {
		CompKruskalEdge<E> compKruskalEdge = new CompKruskalEdge<>(l);
		return compKruskalEdge.kruskal();
	}
}
  
