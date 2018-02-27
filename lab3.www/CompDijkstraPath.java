import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by Niklas on 2018-02-20.
 */
public class CompDijkstraPath<E extends Edge> {

    Comparator<PqueueElement> comparator = new Comparator<PqueueElement>() {

        /**
         * Comparator for priority queue. Compares the weight of two edges
         * @param e1 the first edge
         * @param e2 the second edge
         * @return a negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater in weight than the
         * second.
         */
        @Override
        public int compare(PqueueElement e1, PqueueElement e2) {
            if(e1.getWeight() < e2.getWeight())
                return -1;
            else if(e1.getWeight() > e2.getWeight())
                return 1;
            else
                return 0;
        }
    };

    PriorityQueue<PqueueElement> priorityQueue;
    int vNodesIndex = 0; // skapa en indexvariabel för visitedNodes.


    /**
     * The main function for computing the shortest path from the starNode to the destNode
     * using a modified version of Dijkstras algorithm.
     *
     * Note:
     * Our version of the algorithm computes a different path from Bellevue to Lana.
     * Though the computed path is of the same weight, so it should be the same thing.
     *
     * @param graph The base graph
     * @param startNode the start node
     * @param destNode the destination node
     * @return a LinkedList<E> containing the edges of the path from startNode to destNode
     */
    public LinkedList<E> CompDijkstraPath(Graph<E> graph, int startNode, int destNode)
    {
        priorityQueue = new PriorityQueue<PqueueElement>(1000, comparator); // SKA ändras till mer optimalt
        int weight;
        int node;
        vNodesIndex = 0;
        LinkedList<E> path = new LinkedList<E>();
        int[] visitedNodes = new int[graph.getNofNodes()];
        LinkedList<E> edges;
        boolean nodeIsVisited = false;

        priorityQueue.add(new PqueueElement(startNode, path));

        while(priorityQueue.size() > 0) {
            PqueueElement pqe = priorityQueue.poll();
            node = pqe.getDestNodeNum();
            path = pqe.getPath();
            nodeIsVisited = false;
            for(int i = 0; i < vNodesIndex; i++) {
                if(visitedNodes[i] == node) {
                    nodeIsVisited = true;
                }
            }
            if(!nodeIsVisited) {
                if(node == destNode) {
                    return path;
                } else {
                    visitedNodes[vNodesIndex++] = node;
                    edges = graph.getEdges(node);
                    addToPQueue(edges, visitedNodes, path);
                }
            }
        }
        return path;
    }

    /**
     * This method takes a linked list with all the edges going out from a node.
     * It then checks if the edges destination node is visited.
     * If not visited, a new path is created that is composed of the old path and
     * the new edge. This new path is added to the priority queue along with the
     * destination node for the edge.
     *
     * @param edges All the edges going out from a node
     * @param visitedNodes  All the nodes visited so far
     * @param path The path so far
     */
    private void addToPQueue(LinkedList<E> edges,int[] visitedNodes, LinkedList<E> path)
    {
        int size = edges.size();
        while(size-- > 0) // kan vara fel
        {
            int node = edges.get(size).getDest();
            for(int i = 0; i < vNodesIndex; i++)
            {
                LinkedList<E> newPath = (LinkedList<E>)path.clone();
                if(visitedNodes[i] != node)
                {
                    newPath.add(edges.get(size));
                    priorityQueue.add(new PqueueElement(node, newPath));

                }
            }
        }
    }

    //private E findShortestEdge(LinkedList<E> edgesFromNode)
    //{
    //    int size = edgesFromNode.size();
    //    double leastWeight = Double.POSITIVE_INFINITY;
    //    E shortestEdge = null;
    //    while(size-- > 0) // kan vara fel
    //    {
    //        if(edgesFromNode.get(size).getWeight() < leastWeight)
    //            shortestEdge = edgesFromNode.get(size);
    //    }
    //    return shortestEdge;
    //}

    /**
     * An element that represents a path and the node that the path ends at.
     */
    private class PqueueElement{
        private int destNodeNum;
        private LinkedList<E> path;
        private double weight;

        /**
         * Constructor
         * @param destNodeNum the node that the path leads to.
         * @param path the path from the starting point to the node with number destNodeNum
         */
        public PqueueElement(int destNodeNum, LinkedList<E> path)
        {
            this.path = path;
            this.destNodeNum = destNodeNum;
            this.weight = calculateWeight();
        }

        /**
         * calculates the cumulative weight for a path.
         * @return the calculated cumulative weight of a path
         */
        private double calculateWeight()
        {
            int size = path.size();
            double cumulativeWeight = 0;
            while(size-- > 0) // kan vara fel
            {
                cumulativeWeight += path.get(size).getWeight();
            }
            return cumulativeWeight;
        }

        public int getDestNodeNum()
        {
            return destNodeNum;
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
