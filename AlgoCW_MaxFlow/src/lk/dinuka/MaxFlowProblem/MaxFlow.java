/*

Name: Dinuka Ravijaya Piyadigama
IIT ID: 2018373
UoW ID: w1742104

 */

package lk.dinuka.MaxFlowProblem;

import java.util.LinkedList;

public class MaxFlow {
    static final int totalVertices = 6;    //Number of vertices the flow network

    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */
    boolean bfs(int[][] residualGraph, int source, int sink, int[] parent) {
        /* Create a visited array and mark all vertices as not visited */
        // A queue is used because the algorithm is implemented with Breath First Search <-------reason-------->
        boolean[] visited = new boolean[totalVertices];
        for (int i = 0; i < totalVertices; ++i)
            visited[i] = false;

        /* Create a queue, enqueue source-vertex and mark source-vertex as visited */
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;          // source is always visited at the beginning
        parent[source] = -1;             // -1 is never given to a node,
        // it's used to identify that there's no node before the source node

        /* Standard BFS Loop */
        while (queue.size() != 0) {
            int u = queue.poll();       // returns the first element of queue and removes it from the queue
            // returns null if queue is empty.

            for (int v = 0; v < totalVertices; v++) {
                if (!visited[v] && residualGraph[u][v] > 0) {
                    // if the node hasn't been visited previously and
                    // if a capacity exists outward from a node

                    // rGraph[][] index = Node
                    // Eg: rGraph[0][1] = Outward Capacity of Edge from 0 to 1

                    queue.add(v);           // adding all the nodes into the queue in increasing order of end node
                    parent[v] = u;          // stores the node before node v (which is node u) at the index v
                    visited[v] = true;      // marking the node which was just added to the queue as visited
                }
            }
        }

        /* If we reached sink in BFS starting from source, then
        return true, else false */
        return (visited[sink]);
    }


    /* Returns tne maximum flow from s to t in the given graph */
    int fordFulkerson(int[][] graph, int source, int sink) {
        int u;      // starting node
        int v;      // ending node

        /* Create a residual graph and fill the residual graph
            with given capacities in the original graph as
            residual capacities in residual graph */

        /* Residual graph where rGraph[i][j] indicates
            residual capacity of edge from i to j (if there
            is an edge. If rGraph[i][j] is 0, then there is
            not) */
        int[][] residualGraph = new int[totalVertices][totalVertices];     // initializing residual graph

        for (u = 0; u < totalVertices; u++)
            for (v = 0; v < totalVertices; v++)
                // initial residual capacities are equal to the capacities of the edges
                residualGraph[u][v] = graph[u][v];

        /* This array is filled by BFS and used to store the residual path */
        int[] parent = new int[totalVertices];
        // initialized with V because the max path can have all the number of vertices in the graph (same node can't be visited more than once)

        int maxFlow = 0;  /* There is no flow initially */

        /* Augment the flow while there is path from source to sink */
        while (bfs(residualGraph, source, sink, parent)) {
            /* Find minimum residual capacity of the edges along the path filled by BFS.
                Or we can say; find the maximum flow through the path found. */
            int pathFlow = Integer.MAX_VALUE;          // assigning the maximum value an integer can have - because minimum residual capacity is needed to be found
            for (v = sink; v != source; v = parent[v]) {
//                v = parent[v]       // since this is the residual path, the previous visited node(u) is taken after v

                u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);      // getting the minimum value out of path_flow and rGraph[u][v] (capacity of edge in residual graph)
            }

            /* update residual capacities of the edges and reverse edges along the path */
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                residualGraph[u][v] -= pathFlow;              // decreasing capacity from the flow sent; to get the residual capacity (forward direction)
                residualGraph[v][u] += pathFlow;              // adding the flow sent to the reverse capacity; to get the residual capacity (backward direction)
            }

            /* Add path flow to overall flow */
            maxFlow += pathFlow;
        }

        /* Return the overall flow */
        return maxFlow;
    }



    /* Driver program to test above functions */
    public static void main(String[] args) {
        // 2-D array is used to create the graph of the flow network
        // graph[][] index = Node
        // Eg: graph[0][2] = Outward Capacity of Edge from node 0 to node 2
        int[][] graph = new int[][]{{0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        MaxFlow m = new MaxFlow();

        System.out.println("The Maximum Possible Flow is: " +
                m.fordFulkerson(graph, 0, 5));

    }
}

/*

References:
https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/

Can use this to take in inputs from the user
https://practice.geeksforgeeks.org/problems/find-the-maximum-flow/0

 */