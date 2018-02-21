import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by Niklas on 2018-02-20.
 */
public class CompDijkstraPath<E extends Edge> {

    Comparator<E> comparator = new Comparator<E>() {
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

    PriorityQueue<E> priorityQueue;

    public LinkedList<E> CompDijkstraPath(Listan<E> l, int startNode, int destNode)
    {
        priorityQueue = new PriorityQueue<E>(l.getSize()-1, comparator);
        int weight;
        int node;
        LinkedList<E> path = new LinkedList<E>();

        priorityQueue.add();

        while(priorityQueue.size() > 0) {

        }
    }

    private class PqueueElement{
        private E edge;
        private LinkedList<E> path;
        private double weight;
        public PqueueElement(E edge, LinkedList<E> path)
        {
            this.path = path;
            this.edge = edge;
            this.weight = edge.getWeight();
        }

        public E getEdge()
        {
            return edge;
        }

        public LinkedList<E> getPath()
        {
            return path;
        }

        public double getWeight(){
            return weight;
        }
    }

}
