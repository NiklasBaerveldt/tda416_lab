import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by Niklas on 2018-02-20.
 */
public class CompKruskalEdge<E extends Edge> {
    Graph<E> graph;
    Graph<E> cc;
    int ccSize;

    Comparator<E> comparator = new Comparator<E>() {

        /**
         * Comparator for priority queue. Compares the weight of two edges
         * @param e1 the first edge
         * @param e2 the second edge
         * @return a negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater in weight than the
         * second.
         */
        @Override
        public int compare(E e1, E e2) {
            if(e1.getWeight() < e2.getWeight())
                return -1;
            else if(e1.getWeight() > e2.getWeight())
                return 1;
            else
                return 0;
        }
    };

    /**
     * Constructor
     * @param graph The base graph
     */
    public CompKruskalEdge(Graph<E> graph) {
        this.graph = graph;
        cc = new Graph<E>(graph.getNofNodes());
        ccSize = cc.getNofNodes();
    }

    /**
     * The main function for computing the minimum spanning tree (MST)
     * using a modified version of Kruskals algorithm.
     *
     * @return An iterator over all of the edges
     */
    public Iterator<E> kruskal()
    {
        ccSize = cc.getNofNodes();
        System.out.println(graph.getNofEdges());
        PriorityQueue<E> pqeue = new PriorityQueue<E>(graph.getNofEdges(),comparator);
        for(int i = 0; i < graph.getNofNodes(); i++)
        {
            LinkedList<E> ll = graph.getEdges(i);
            for(int j = 0; j < ll.size(); j++)
            {
                pqeue.add(ll.get(j));
            }
        }
        int source = 0;
        int dest = 0;
        while(pqeue.size() > 0 && ccSize > 1){
            E e = pqeue.poll();
            if(cc.getEdges(e.getSource()) != cc.getEdges(e.getDest()))
            {
                if(cc.getEdges(e.getSource()).size() < cc.getEdges(e.getDest()).size())
                {
                    cc.getEdges(e.getDest()).addAll(cc.getEdges(e.getSource()));
                    LinkedList<E> tll = cc.getEdges(e.getSource());
                    if(tll.size() > 0) {
                        for (int i = 0; i < tll.size(); i++) {
                            source = tll.get(i).from;
                            dest = tll.get(i).getDest();
                            cc.setNodePointer(source, cc.getEdges(e.getDest()));
                            cc.setNodePointer(dest, cc.getEdges(e.getDest()));
                        }
                    }
                    else{
                        cc.setNodePointer(e.getSource(), cc.getEdges(e.getDest()));
                        cc.setNodePointer(e.getDest(), cc.getEdges(e.getDest()));
                    }
                    cc.getEdges(e.getDest()).add(e);
                    ccSize--;
                }
                else
                {
                    cc.getEdges(e.getSource()).addAll(cc.getEdges(e.getDest()));
                    LinkedList<E> tll = cc.getEdges(e.getDest());
                    if(tll.size() > 0) {
                        for (int i = 0; i < tll.size(); i++) {
                            source = tll.get(i).from;
                            dest = tll.get(i).getDest();
                            cc.setNodePointer(source, cc.getEdges(e.getSource()));
                            cc.setNodePointer(dest, cc.getEdges(e.getSource()));
                        }
                    }
                    else{
                        cc.setNodePointer(e.getSource(), cc.getEdges(e.getSource()));
                        cc.setNodePointer(e.getDest(), cc.getEdges(e.getSource()));
                    }
                    cc.getEdges(e.getSource()).add(e);
                    ccSize--;
                }
            }
        }


        /**
         * Index does not matter because in the end all of the lists will point to the same thing.
         * We chose 0 arbitrarily.
         */
        return cc.getEdges(0).listIterator();
    }
}
