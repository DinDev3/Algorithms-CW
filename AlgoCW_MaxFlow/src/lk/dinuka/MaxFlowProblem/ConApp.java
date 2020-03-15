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
    // if a capacity exists, an edge exists between two nodes

    static Scanner sc = new Scanner(System.in);            // scanner which used to get inputs from the user

    static HashMap<Integer, int[]> graphMap = new HashMap<>();       // stores inputs of the user
//    Key: starting node,
//    Value: array with ending node as index of array. Capacity as value at each index in array.


    public static void main(String[] args) {
        int chosenOption;

        do {

            //display main menu
            System.out.println("\n1) Initialize the flow network");
            System.out.println("2) Find the Max Flow of the flow network");
            System.out.println("3) Delete link from flow network");
            System.out.println("4) Modify the maximum capacity of a link");
            System.out.println("5) Exit program");
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
                case 5:     // Exit program
//                display exit message ---------------------------->>>>>>>>>>>>>>>
                    System.exit(0);     // close program
                default:        // if the entered number isn't within the accepted range
                    System.out.println("Invalid input. Please try again");
            }
        } while (chosenOption != 5);
        System.out.print("Enter the number of Nodes: ");
    }


    private static void initializeFlowNetwork() {            // initialize flow network
        System.out.println("Enter the number of Nodes in the flow network");
        System.out.print(">");
        intInputValidation();       // integer input validation
        noOfNodes = sc.nextInt();       // assigning user input to number of nodes in the flow network


        System.out.println("Creating the graph with capacities...");
        for (int i = 0; i < noOfNodes; ++i) {
            // array is created inside the for loop to create new arrays. Or else same array will be referenced from all hashmap values
            int[] arrayOfCapacities = new int[noOfNodes];          // all HashMap values are initialized with this. It holds the outward capacities in edges relevant to one node

            graphMap.put(i, arrayOfCapacities);
            System.out.println(Arrays.toString(graphMap.get(i)));
        }
//        System.out.println(graphMap.toString());      // to test the values (array references)


        // --------------------------------------------------------------------------
//        System.out.println("~ Enter capacities between the nodes of the network ~");
//
//        int noOfCapacitiesTBA;
//
//        do {        // there should be at least one capacity
//            System.out.print("Enter the starting node of the flow: ");
//            intInputValidation();
//            sc.nextInt();
//
//            System.out.print("Enter the ending node of the flow: ");
//            intInputValidation();
//            sc.nextInt();
//
//            System.out.println();
//
//            System.out.print("Enter the capacity of the Edge between these two nodes: ");
//            intInputValidation();
//            sc.nextInt();
//
//            System.out.println("If you wish to enter another capacity, input any integer number");
//            System.out.println("Enter -1 to stop adding capacities");
//            intInputValidation();
//            noOfCapacitiesTBA = sc.nextInt();
//
//        } while (noOfCapacitiesTBA != -1);
        // --------------------------------------------------------------------------


    }


    private static void findMaxFlowOfNetwork() {            // Find the Max Flow of the flow network
//        int[][] graph = new int[][]{};                // assign inputs to this array here
        int[][] graph = new int[][]{{0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        MaxFlow.totalVertices = 6;

        MaxFlow m = new MaxFlow();

        System.out.println("~ Initial graph of flow network ~");
        System.out.println(Arrays.deepToString(graph) + "\n");

        System.out.println("The Maximum Possible Flow is: " +
                m.fordFulkerson(graph, 0, 5));

    }

    private static void deleteLink() {      // Delete link from flow network

    }

    private static void modifyMaxCapacity() {       // Modify the maximum capacity of a link

    }


    //--------------------

    private static void intInputValidation() {                     //validating integer input

        while (!sc.hasNextInt()) {
            System.out.println("Only integer numbers are allowed! Please provide a valid input");              //error handling message for characters other than integers
            sc.next();                                                     //removing incorrect input entered
        }
    }


}
