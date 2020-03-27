/*

Name: Dinuka Ravijaya Piyadigama
IIT ID: 2018373
UoW ID: w1742104

 */

package lk.dinuka.MaxFlowProblem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ConApp {
    private static int noOfNodes;
    private static int source;              // starting node of the floor network
    private static int sink;              // ending node of the floor network
    // if a capacity exists, an edge exists between two nodes

    static Scanner sc = new Scanner(System.in);            // scanner which used to get inputs from the user

    static HashMap<Integer, int[]> graphMap = new HashMap<>();       // stores inputs of the user
//    Key: starting node,
//    Value: array with ending node as index of array. Capacity as value at each index in array.


    public static void main(String[] args) {
        int chosenOption;

        do {
            System.out.println("\n\t\\~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/");
            System.out.println("\t\t||\t~~\tMax Flow Calculator\t~~\t||");
            System.out.println("\t/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\\");

            //display main menu
            System.out.println("\n1) Initialize the flow network");
            System.out.println("2) Find the Max Flow of the flow network");
            System.out.println("3) Delete a link from flow network");
            System.out.println("4) Modify the maximum capacity of a link");
            System.out.println("5) View Created Graph of the flow network");        // view of 2D array in console
            System.out.println("6) Exit program");
//            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter Option:\n>>");


            intInputValidation();       // integer input validation

            chosenOption = sc.nextInt();        // getting the chosen menu option of the user

            switch (chosenOption) {
                case 1:     // initialize flow network
                    initializeFlowNetwork();
                    break;
                case 2:     // Find the Max Flow of the flow network
                    findMaxFlowOfNetwork();
                    break;
                case 3:     // Delete link from flow network
                    deleteLink();
                    break;
                case 4:     // Modify the maximum capacity of a link
                    modifyMaxCapacity();
                    break;
                case 5:     // view created graph
                    viewGraph();
                    break;
                case 6:     // Exit program
                    System.out.println("Thank you for using Max Flow Calculator");
                    System.out.println("Exiting Program...");
                    System.exit(0);     // close program

                default:        // if the entered number isn't within the accepted range
                    System.out.println("Invalid input. Please try again");
            }
        } while (chosenOption != 6);
    }


    private static void initializeFlowNetwork() {            // initialize flow network
        System.out.println("Enter the number of Nodes in the flow network");
        System.out.print(">");
        intInputValidation();       // integer input validation

        noOfNodes = sc.nextInt();       // assigning user input to number of nodes in the flow network

        while (noOfNodes < 6) {
            System.out.println("The flow network must comprise of at least 6 nodes");              // allowing only floor network that have at least 6 nodes
            System.out.print("Please enter a greater amount of nodes: ");
            intInputValidation();           // validating integer input
            noOfNodes = sc.nextInt();
        }


        System.out.println("\nCreating the graph with capacities...");
        for (int i = 0; i < noOfNodes; ++i) {
            // array is created inside the for loop to create new arrays. Or else same array will be referenced from all hashmap values
            int[] arrayOfCapacities = new int[noOfNodes];          // all HashMap values are initialized with this. It holds the outward capacities in edges relevant to one node

            graphMap.put(i, arrayOfCapacities);
            System.out.println(Arrays.toString(graphMap.get(i)));
        }
//        System.out.println(graphMap.toString());      // to test the values (array references)

        System.out.print("The nodes of the graph are: ");
        for (int i = 0; i < noOfNodes; ++i) {
            System.out.print(i + " ");
        }


        System.out.print("\n\nChoose the source of the floor network: ");
        intInputValidation();           // validating integer input
        source = sc.nextInt();
        while (!graphMap.containsKey(source)) {
            System.out.println("The source node has to take a number between 0 to " + (noOfNodes - 1));
            System.out.print("Please enter a valid source: ");
            intInputValidation();           // validating integer input
            source = sc.nextInt();
        }

        System.out.print("\nChoose the sink of the floor network: ");
        intInputValidation();           // validating integer input
        sink = sc.nextInt();
        while (!graphMap.containsKey(sink)) {
            System.out.println("The sink node has to take a number between 0 to " + (noOfNodes - 1));
            System.out.print("Please enter a valid sink: ");
            intInputValidation();           // validating integer input
            sink = sc.nextInt();
        }


        // --------------------------------------------------------------------------
        System.out.println("\n\n~ Enter capacities between the nodes of the network ~");

        int noOfCapacitiesTBA = 1;

        do {        // there should be at least one capacity between a pair of nodes in the graph

            for (int n = 1; n <= noOfCapacitiesTBA; n++) {

                System.out.println("Enter the starting & ending nodes of the flow");
                System.out.print("Starting Node: ");
                intInputValidation();
                int startNode = sc.nextInt();
                while (!graphMap.containsKey(startNode)) {
                    System.out.println("Only nodes between 0 and " + (noOfNodes - 1) + " are allowed. Please try again.");
                    intInputValidation();           // validating integer input
                    startNode = sc.nextInt();
                }

                System.out.print("Ending Node: ");
                intInputValidation();
                int endNode = sc.nextInt();
                while (!graphMap.containsKey(startNode)) {
                    System.out.println("Only nodes between 0 and " + (noOfNodes - 1) + " are allowed. Please try again.");
                    intInputValidation();           // validating integer input
                    startNode = sc.nextInt();
                }
                System.out.print("Enter the capacity of the Edge between these two nodes: ");
                intInputValidation();
                int capacity = sc.nextInt();


                int[] modifiedArray = graphMap.get(startNode);
                modifiedArray[endNode] = capacity;          // changing the capacity of the array at the given point

                graphMap.put(startNode, modifiedArray);          // adding the newly modified array into the hashmap


                System.out.println();
            }

            System.out.println("How many more capacities do you wish to enter");
            System.out.println("Enter -1 to stop adding capacities");
            intInputValidation();
            noOfCapacitiesTBA = sc.nextInt();

        } while (noOfCapacitiesTBA != -1);
        // --------------------------------------------------------------------------


//        for (int i = 0; i < noOfNodes; ++i) {               // checking HashMap values
//            System.out.println(Arrays.toString(graphMap.get(i)));
//        }

    }


    private static void findMaxFlowOfNetwork() {            // Find the Max Flow of the flow network
//        //---------- graph created from user input
        int[][] graph = new int[noOfNodes][noOfNodes];                // assign inputs to this array here
        for (int i = 0; i < noOfNodes; i++) {
            for (int q = 0; q < noOfNodes; q++) {
                graph[i][q] = graphMap.get(i)[q];
            }
        }

        //---------- hard-coded graph
//        int[][] graph = new int[][]{{0, 16, 13, 0, 0, 0},
//                {0, 0, 10, 12, 0, 0},
//                {0, 4, 0, 0, 14, 0},
//                {0, 0, 9, 0, 0, 20},
//                {0, 0, 0, 7, 0, 4},
//                {0, 0, 0, 0, 0, 0}
//        };
//
//        noOfNodes = 6;
//        source = 0;
//        sink = 5;
        //-----------

        MaxFlow.totalVertices = noOfNodes;

//        MaxFlow.displayGraph(graph);

        MaxFlow m = new MaxFlow();

        System.out.println("~ Initial graph of flow network ~");
        System.out.println(Arrays.deepToString(graph) + "\n");


        try {      // run this only if a flow network has been initialized with sink & source -> otherwise index out of bounds error will be given
            long startTime = System.nanoTime();
            System.out.println("The Maximum Possible Flow is: " + m.fordFulkerson(graph, source, sink));
            long endTime = System.nanoTime();

            // get difference of two nanoTime values
            long timeElapsed = endTime - startTime;
            System.out.println("\nExecution time in nanoseconds  : " + timeElapsed);

//            System.out.println(getTimeRatio(timeElapsed1, timeElapsed2));

        } catch (Exception e) {
            System.out.println("The graph needs to be initialized with a sink and a source before calculating the Max Flow");
        }


    }

    private static void deleteLink() {      // Delete a link from flow network
        System.out.println("Choose the starting and ending nodes of the link to be deleted");

        System.out.print("Starting node: ");
        intInputValidation();       // validate integer input
        int startNode = sc.nextInt();
        while (!graphMap.containsKey(startNode)) {
            System.out.println("Only nodes between 0 and " + (noOfNodes - 1) + " are allowed. Please try again.");
            intInputValidation();           // validating integer input
            startNode = sc.nextInt();
        }

        System.out.print("Ending node: ");
        intInputValidation();       // validate integer input
        int endNode = sc.nextInt();
        while (!graphMap.containsKey(endNode)) {
            System.out.println("Only nodes between 0 and " + (noOfNodes - 1) + " are allowed. Please try again.");
            intInputValidation();           // validating integer input
            endNode = sc.nextInt();
        }

        if (graphMap.get(startNode)[endNode] != 0) {
            System.out.println("Successfully deleted the link from " + startNode + " to " + endNode + " node from the flow network.");
            graphMap.get(startNode)[endNode] = 0;           // when the capacity is 0; there's no link between the two nodes

        } else {
            System.out.println("There's no capacity from " + startNode + " to " + endNode + " node in the flow network.");
        }


    }

    private static void modifyMaxCapacity() {       // Modify the maximum capacity of a link
        System.out.println("Choose the starting and ending nodes of the link to be modified");

        System.out.print("Starting node: ");
        intInputValidation();       // validate integer input
        int startNode = sc.nextInt();
        while (!graphMap.containsKey(startNode)) {
            System.out.println("Only nodes between 0 and " + (noOfNodes - 1) + " are allowed. Please try again.");
            intInputValidation();           // validating integer input
            startNode = sc.nextInt();
        }

        System.out.print("Ending node: ");
        intInputValidation();       // validate integer input
        int endNode = sc.nextInt();
        while (!graphMap.containsKey(endNode)) {
            System.out.println("Only nodes between 0 and " + (noOfNodes - 1) + " are allowed. Please try again.");
            intInputValidation();           // validating integer input
            endNode = sc.nextInt();
        }

        System.out.print("Enter the new maximum capacity of the link: ");
        intInputValidation();             // validate integer input
        int changedMaxCapacity = sc.nextInt();

        graphMap.get(startNode)[endNode] = changedMaxCapacity;           // new max capacity assigned

        System.out.println("Successfully changed the maximum capacity on the link from node " + startNode + " to node " + endNode + " of the flow network");

    }


    private static void viewGraph() {           // View the graph created of the flow network (only if the graph was created from the console menu)
        // useful to check whether deletions/ modifications were made to the flow network as expected before trying to find max flow

        //---------- graph created from user input
        int[][] graph = new int[noOfNodes][noOfNodes];                // assign inputs to this array here
        for (int i = 0; i < noOfNodes; i++) {
            for (int q = 0; q < noOfNodes; q++) {
                graph[i][q] = graphMap.get(i)[q];
            }
        }

        System.out.println(Arrays.deepToString(graph));         // display graph
    }

    //--------------------

    public static float getTimeRatio(long firstTime, long secondTime) {      // get the ratio of changes between multiple times
        return (float) firstTime / secondTime;
    }

    private static void intInputValidation() {                     //validating integer input

        while (!sc.hasNextInt()) {
            System.out.println("Only integer numbers are allowed! Please provide a valid input");              //error handling message for characters other than integers
            sc.next();                                                     //removing incorrect input entered
        }
    }

}

/*
References:
https://stackoverflow.com/questions/7602665/store-an-array-in-hashmap/7602742
 */