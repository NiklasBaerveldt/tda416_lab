import java.util.*;
import java.util.List;

/**
 * Created by Niklas on 2018-02-20.
 */
public class Listan<E extends Edge> {
    private java.util.List[] edgeList;

    public Listan(int nOfNodes){
        edgeList = new List[nOfNodes];
        for(int i = 0; i < nOfNodes; i++) {
            edgeList[i] = new LinkedList<E>();
        }
    }

    public void addEdge(E e)
    {
        int nodeFrom = e.getSource();
        edgeList[nodeFrom].add(e);
    }

    public LinkedList<E> getEdges(int fromNode)
    {
        LinkedList<E> ll = (LinkedList<E>) edgeList[fromNode]; // kan vara problem
        return ll;
    }

    public int getSize(){
        return edgeList.length;
    }
}
