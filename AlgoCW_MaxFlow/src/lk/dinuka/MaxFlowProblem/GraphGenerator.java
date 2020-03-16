package lk.dinuka.MaxFlowProblem;

import java.util.Arrays;
import java.util.Random;

public class GraphGenerator {
    public static void main(String[] args) {


        int noOfNodes = 48;
//        int source = 0;
//        int sink = 47;      //48-1
        // nodes are there from 0 to 47 (48 nodes in total)


        int[][] graph = new int[noOfNodes][noOfNodes];                // assign inputs to this array here


        System.out.println(Arrays.deepToString(graph));     // initial graph with all 0s

        int startNode = 0;
        int endNode = 0;

        int countUnique = 0;         // to make sure that numbers won't get overridden at positions

        while (countUnique < 80) {           // creating 80 links

            Random r = new Random();

            do {
                startNode = r.nextInt(47);          // random integer from 0 - 46 (sink can't have outward capacity)
                endNode = r.nextInt(48);            // random integer from 0 - 47
            } while ((startNode == endNode) || !(graph[startNode][endNode] == 0));      // to avoid self-connections
            // only add capacities to links that haven't been added

            int capacity = r.nextInt(100) + 1;        // capacity can take a value from 1- 100

//            if (graph[startNode][endNode] == 0) {       // insert new capacity only if a previous capacity wasn't entered
                graph[startNode][endNode] = capacity;
                countUnique++;
//            }
            System.out.println(startNode);
            System.out.println(endNode);
            System.out.println(graph[startNode][endNode]);
            System.out.println(Arrays.deepToString(graph));     // generated graph with 80 links

            System.out.println("-------------------------------------------");
        }

        System.out.println(Arrays.deepToString(graph));     // generated graph with 80 links
        System.out.println(countUnique);

    }
}
