/*

Name: Dinuka Ravijaya Piyadigama
IIT ID: 2018373
UoW ID: w1742104

 */

package lk.dinuka.MaxFlowProblem;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

import javax.swing.*;
import java.awt.*;

// Creates and displays a graph
public class GraphGUI extends
        JApplet {

    private static final Dimension DEFAULT_SIZE = new Dimension(1000, 800);

    private JGraphXAdapter<String, EdgeCapacity> jgxAdapter;

    public static int[][] GUIGraph;
    static int GUITotalVertices;

    @Override
    public void init() {
        // create a JGraphT graph
        ListenableGraph<String, EdgeCapacity> g =
                new DefaultListenableGraph<>(new DefaultDirectedGraph<>(EdgeCapacity.class));

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(g);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        getContentPane().add(component);
        resize(DEFAULT_SIZE);


        // displaying a graph.
        // Need to show capacities~~~~>>>
        // Can't show capacities in both directions~~~~>>>??

        // creating all the nodes of the graph
        for (int i = 0; i < GUITotalVertices; i++) {
            g.addVertex(Integer.toString(i));
        }

        // creating all the edges that exists with Capacities
        for (int u = 0; u < GUITotalVertices; u++) {
            int[] startingNode = GUIGraph[u];
            for (int v = 0; v < GUITotalVertices; v++) {
                if (startingNode[v] > 0) {     // if a capacity exists
                    g.addEdge(Integer.toString(u), Integer.toString(v),new EdgeCapacity(GUIGraph[u][v]));
                }

            }
        }

        // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        // center the circle
        int radius = 100;
        layout.setX0((DEFAULT_SIZE.width / 2.0) - 3*radius);
        layout.setY0((DEFAULT_SIZE.height / 2.0) - 3*radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
    }


    // Driver method to test drawing of graph
//    public static void main(String[] args) {
//
//        GUIGraph = new int[][]{{0, 16, 13, 0, 0, 0},
//                {0, 0, 10, 12, 0, 0},
//                {0, 4, 0, 0, 14, 0},
//                {0, 0, 9, 0, 0, 20},
//                {0, 0, 0, 7, 0, 4},
//                {0, 0, 0, 0, 0, 0}
//        };
//        GUITotalVertices = 6;
//
//
//        GraphGUI applet = new GraphGUI();
//        applet.init();
//
//        JFrame frame = new JFrame();
//        frame.getContentPane().add(applet);
//        frame.setTitle("JGraphT Adapter to JGraphX Demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }

}


/*
References:
https://github.com/jgrapht/jgrapht
https://stackoverflow.com/questions/1023184/max-flow-graph-simulation-in-java
https://www.baeldung.com/jgrapht
https://jgrapht.org/guide/LabeledEdges
 */