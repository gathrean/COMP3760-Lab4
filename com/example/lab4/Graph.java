package com.example.lab4;

/**
 * Graph class that represents a Graph data structure
 */
public class Graph {

    // Instance variables
    private int size;
    private String[] vertexLabels;
    private boolean isDirected;
    private int[][] adjacencyMatrix;

    // Constructor
    Graph(String[] vertexLabels, boolean isDirected) {

    }

    // Setter
    void setDirection(boolean isDirected) {

    }

    // Adds an edge between two vertices
    void addEdge(String source, String destination) {

    }

    // returns the number of vertices
    int size() {

    }

    // returns the string name of the vertex number "v" internally
    String getLabel(int v) {

    }

    // returns a string representation of the adjacency matrix
    String toString() {

    }

    // performs Depth First Search
    void runDFS(boolean quiet) {

    }

    // performs Depth First Search at vertex v
    void runDFS(String v, boolean quiet) {

    }

    // performs Breadth First Search
    void runBFS(boolean quiet) {

    }

    // performs BFS starting at vertex v
    void runBFS(String v, boolean quiet) {

    }

    // gets result of the most recently performed DFS
    String getLastDFSOrder() {

    }

    // gets result of the most recently performed BFS
    String getLastBFSOrder() {

    }

    // gets result of the most recently performed DFS
    String getLastDFSDeadEndOrder() {

    }

    // gets result of the most recently performed BFS
    String getLastBFSOrder() {

    }
}
