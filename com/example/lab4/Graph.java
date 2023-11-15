package com.example.lab4;

/**
 * com.example.lab4.Graph class that represents a com.example.lab4.Graph data structure
 */
public class Graph {
    private int[][] adjacencyMatrix;
    private int numVertices;
    private boolean isDirected;

    // Constructor to initialize the graph with a given number of vertices
    public Graph(int numVertices, boolean isDirected) {
        this.numVertices = numVertices;
        this.adjacencyMatrix = new int[numVertices][numVertices];
        this.isDirected = isDirected;
    }

    // Add an edge between two vertices
    public void addEdge(int source, int destination) {
        if (source >= 0 && source < numVertices && destination >= 0 && destination < numVertices) {
            adjacencyMatrix[source][destination] = 1;
            if (!isDirected) {
                adjacencyMatrix[destination][source] = 1;
            }
        } else {
            throw new IllegalArgumentException("Invalid vertices");
        }
    }

    // Print the adjacency matrix representing the graph
    public void printAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Create a directed graph with 5 vertices
        Graph directedGraph = new Graph(5, true);
        directedGraph.addEdge(0, 1);
        directedGraph.addEdge(0, 3);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(2, 4);
        directedGraph.addEdge(3, 4);

        directedGraph.printAdjacencyMatrix();

        // Create an undirected graph with 4 vertices
        Graph undirectedGraph = new Graph(4, false);
        undirectedGraph.addEdge(0, 1);
        undirectedGraph.addEdge(0, 2);
        undirectedGraph.addEdge(1, 2);
        undirectedGraph.addEdge(2, 3);

        undirectedGraph.printAdjacencyMatrix();
    }
}
