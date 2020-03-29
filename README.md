# Algorithms-CW
This repository contains code that I implemented for the Max Flow Problem which was given for the Coursework of the Algorithms module; which I followed in the 2nd year of my undergraduate degree.

Included files:
- GraphGUI.java - Creates and displays a graph
- MaxFlow.java - Finds the maximum flow of a graph
- ConApp.java - Controls all the console menu options.
- EdgeCapacity.java - Custom edge class to display the capacities along edges in the GUI
- GraphGenerator.java - Used to create a random graph with a specified number of nodes and edges﻿

---

The graph can be input into the system either by hardcoding the values in the main method of the MaxFlow.java or the ConApp.java class's main method.<br/>
It can also be initialized by the first option in the console application (ConApp).<br/>

The max flow can be found by the 2nd option in the console menu in ConApp.java or by running the main method in the MaxFlow class.<br/>

A link can be deleted using the 3rd option in the console menu.<br/>

A link's capacity can be modified using the 4th option in the console menu.<br/>
A link can also be added or created using the modify option.(0 capacity = no link)<br/>

The 5th option in the console menu allows the user to visualize the graph in the GUI as a graph and the console application as a 2D array. This option is to help the user to make sure that the required graph has been created from the console input.<br/>

The 6th option in the console menu, exits the console menu application. 
