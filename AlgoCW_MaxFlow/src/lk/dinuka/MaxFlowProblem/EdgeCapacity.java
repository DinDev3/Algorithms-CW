/*

Name: Dinuka Ravijaya Piyadigama
IIT ID: 2018373
UoW ID: w1742104

 */

package lk.dinuka.MaxFlowProblem;

import org.jgrapht.graph.DefaultWeightedEdge;

// this class overrides the default behaviour of the graph
//  instead of displaying the two connecting vertices,
//  this ensures that the capacities are displayed
public class EdgeCapacity extends DefaultWeightedEdge {

    private int capacity;

    public EdgeCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return Double.toString(capacity);
    }
}


/*
References:
Displaying the capacity(weight) of the edges along the edges in the GUI graph
https://stackoverflow.com/questions/13378519/how-represent-edge-weight-via-jgrapht-visualization
 */