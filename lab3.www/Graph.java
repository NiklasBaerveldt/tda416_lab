import java.util.*;
import java.util.List;

/**
 * Created by Niklas on 2018-02-20.
 */
public class Graph<E extends Edge> {
    private java.util.List[] edgeList;

    /**
     * Constructor. Creates a list with nOfNodes elements. The list is then filled with linked lists.
     * These linked lists will contain edges. The edges coming from node i will be in the linked list at edgeList[i].
     * @param nOfNodes number of nodes in the graph i.e. the capacity of the list.
     */
    public Graph(int nOfNodes){
        edgeList = new List[nOfNodes];
        for(int i = 0; i < nOfNodes; i++) {
            edgeList[i] = new LinkedList<E>();
        }
    }

    /**
     * Adds an edge e to the graph at the source node of the edge.
     * @param e the edge to add
     */
    public void addEdge(E e)
    {
        int nodeFrom = e.getSource();
        edgeList[nodeFrom].add(e);
    }

    /**
     * Gets the edges that has the node with number fromNode as source.
     * @param fromNode The number of the node from which the edges will be returned.
     * @return The edges of the node in a linked list
     */
    public LinkedList<E> getEdges(int fromNode)
    {
        LinkedList<E> ll = (LinkedList<E>) edgeList[fromNode];
        return ll;
    }

    public int getNofNodes(){
        return edgeList.length;
    }

    public int getNofEdges(){
        int edgeCounter = 0;
        int nOfNodes = getNofNodes();
        for(int i = 0; i < nOfNodes; i++)
        {
            LinkedList<E> ll = (LinkedList<E>) edgeList[i];
            for(int j = 0; j < ll.size(); j++)
            {
                edgeCounter++;
            }
        }
        return edgeCounter;
    }

    /**
     * Sets the node with number i to point to a new set of edges
     * @param i The number of the node to assign new edges
     * @param ll The edges to assign to a node.
     */
    public void setNodePointer(int i, LinkedList<E> ll){
        edgeList[i] = ll;
    }
}
