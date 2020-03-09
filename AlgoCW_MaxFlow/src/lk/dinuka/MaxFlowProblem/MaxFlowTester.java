package lk.dinuka.MaxFlowProblem;

import java.util.LinkedList;

public class MaxFlowTester {
    static final int V = 6;    //Number of vertices in graph

    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */
    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not visited
        // A queue is used because the algorithm is implemented with Breath First Search <-------reason-------->
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;          // source is always visited at the beginning
        parent[s] = -1;             //----------------------? what is this doing>>>>>???

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();       // returns the first element of queue, or null if queue is empty.

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    // if the node hasn't been visited previously and
                    // if a capacity exists outward from a node
                    // rGraph[][] index = Node
                    // rGraph[0][1] = Outward Capacity of Edge from 0 to 1

                    queue.add(v);           // adding all the nodes into the queue in increasing order
                    parent[v] = u;          // stores the path from s-------------------------->>>>>>>>>????????
                    visited[v] = true;      // marking the node which was just added to the queue as visited
                }
            }
        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }


    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[V];                  // why is it initialized with V------------->>>>>>>?????????

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while there is path from source to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edges along the path filled by BFS.
            // Or we can say; find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;          // assigning the maximum value an integer can have---------->>>>>>???????
            for (v = t; v != s; v = parent[v]) {        // what is parent[v] doing ------------>>>>>>>>????????
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);      // getting the minimum value out of path_flow and rGraph[u][v]
            }

            // update residual capacities of the edges and reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;              // decreasing capacity from the flow sent; to get the residual capacity (forward direction)
                rGraph[v][u] += path_flow;              // adding the flow sent to the reverse capacity; to get the residual capacity (backward direction)
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }

    // Driver program to test above functions
    public static void main(String[] args) throws java.lang.Exception {
        // Let us create a graph shown in the above example
        // 2-D array is used
        // graph[][] index = Node
        // graph[0][2] = Outward Capacity of Edge from node 0 to node 2
        int graph[][] = new int[][]{{0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        MaxFlowTester m = new MaxFlowTester();

        System.out.println("The maximum possible flow is " +
                m.fordFulkerson(graph, 0, 5));

    }
}

/*

References:
https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/

Can use this to take in inputs from the user
https://practice.geeksforgeeks.org/problems/find-the-maximum-flow/0

 */