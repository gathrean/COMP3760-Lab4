package com.example.lab4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Graph class that represents a Graph data structure
 */
public class Graph {

    // Instance variables
    private int size;
    private String[] vertexLabels;
    private boolean isDirected;
    private int[][] adjacencyMatrix;

    private List<String> dfsOrder = new ArrayList<>();
    private List<String> finishedOrder = new ArrayList<>();

    private List<String> bfsOrder = new ArrayList<>();

    /**
     * Constructor to initialize the Graph.
     *
     * @param vertexLabels - array of Strings that represent the names of the vertices
     * @param isDirected   - indicates whether the graph is directed or not
     */
    Graph(String[] vertexLabels, boolean isDirected) {
        this.size = vertexLabels.length;
        this.vertexLabels = vertexLabels;
        this.isDirected = isDirected;
        this.adjacencyMatrix = new int[size][size];
    }

    /**
     * Can be used to manually set/reset whether the graph is directed or not.
     *
     * @param isDirected - indicates whether the graph is directed or not
     */
    void setDirection(boolean isDirected) {
        this.isDirected = isDirected;
    }

    /**
     * Returns true or false - true if the graph is a directed graph.
     */
    boolean isDirected() {
        return isDirected;
    }

    /**
     * Adds an edge between vertex A to the vertex B.
     * <p>
     * If the graph is undirected this means setting
     * two entries in the adjacency matrix to 1.
     *
     * @param A - the source vertex
     * @param B - the destination vertex
     */
    void addEdge(String A, String B) {
        // Get the index of the source and destination vertices
        int sourceIndex = getIndex(A);
        int destinationIndex = getIndex(B);

        // Check if the vertices are valid
        if (sourceIndex != 1 && destinationIndex != 1) {
            adjacencyMatrix[sourceIndex][destinationIndex] = 1;
            if (!isDirected) {
                adjacencyMatrix[destinationIndex][sourceIndex] = 1;
            }
        } else {
            throw new IllegalArgumentException("Invalid vertices");
        }
    }

    /**
     * Helper method to get the index of a vertex label
     *
     * @param label - the vertex label
     * @return - the index of the vertex label
     */
    private int getIndex(String label) {
        for (int i = 0; i < size; i++) {
            if (vertexLabels[i].equals(label)) {
                return i;
            }
        }
        return -1; // meaning the vertex label is not found!
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return - the number of vertices in the graph
     */
    int size() {
        return size;
    }

    /**
     * Returns the string name of the vertex number "v" internally.
     * <p>
     * (i.e. the vertex that was at position 'v' in the array that was passed to the constructor
     * when the graph was initialized)
     *
     * @param v - an integer in the range 0 to N-1 inclusive.
     * @return - the name of the vertex numbered 'v' on your internal list of vertices.
     */
    String getLabel(int v) {
        // Check if the vertex is valid
        if (v >= 0 && v < size) {
            return vertexLabels[v];
        } else { // Otherwise, throw an exception
            throw new IllegalArgumentException("Invalid vertex");
        }
    }

    /**
     * Returns a string representation of the adjacency matrix.
     *
     * @return - a string representation of the adjacency matrix
     */
    public String toString() {
        // Create a StringBuilder object to store the result
        StringBuilder result = new StringBuilder();

        // Loop through the adjacency matrix and append each element to the result
        for (int[] row : adjacencyMatrix) {
            for (int col : row) {
                result.append(col).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    /**
     * Depth First Search (DFS) algorithm
     * <p>
     * Helper method for runDFS()
     * </p>
     *
     * @param v       - the vertex to start the DFS from
     * @param visited - boolean array to keep track of visited vertices
     */
    private void depthFirstSearchUtil(int v, boolean[] visited) {
        visited[v] = true;
        dfsOrder.add(vertexLabels[v]);

        for (int i = 0; i < size; i++) {
            if (adjacencyMatrix[v][i] == 1 && !visited[i]) {
                depthFirstSearchUtil(i, visited);
            }
        }

        finishedOrder.add(vertexLabels[v]);
    }

    /**
     * Performs Depth First Search (DFS) on the graph.
     *
     * @param quiet - indicates whether to print the results or not
     */
    void runDFS(boolean quiet) {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[size];

        // Call the recursive helper function to print DFS traversal
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                depthFirstSearchUtil(i, visited);
            }
        }

        // Results when there's  no more unvisited vertices
        if (!quiet) {
            System.out.println("DFS Order: " + dfsOrder.toString());
            System.out.println("Finished Order: " + finishedOrder.toString());
        }
    }

    /**
     * Performs Depth First Search (DFS) on the graph starting at vertex v.
     *
     * @param v     - the vertex to start the DFS from
     * @param quiet - indicates whether to print the results or not
     */
    void runDFS(String v, boolean quiet) {
        // Get the index of the vertex
        int index = getIndex(v);

        // Check if the vertex is valid
        if (index != -1) {
            // Mark all the vertices as not visited
            boolean[] visited = new boolean[size];

            // Call the recursive helper function to print DFS traversal
            depthFirstSearchUtil(index, visited);

            // Results when there's  no more unvisited vertices
            if (!quiet) {
                System.out.println("DFS Order: " + dfsOrder.toString());
                System.out.println("Finished Order: " + finishedOrder.toString());
            }
        } else {
            throw new IllegalArgumentException("Invalid vertex");
        }
    }

    /**
     * Helper method for runBFS() methods.
     *
     * @param v       - the vertex to start the BFS from
     * @param visited - boolean array to keep track of visited vertices
     */
    private void breadthFirstSearchUtil(int v, boolean[] visited) {
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[v] = true;
        queue.add(v);

        // Loop through the queue
        while (!queue.isEmpty()) {
            v = queue.poll(); // Dequeue a vertex from queue and print it
            bfsOrder.add(vertexLabels[v]); // Add the vertex to the BFS order

            // Get all adjacent vertices of the dequeued vertex v
            for (int i = 0; i < size; i++) {
                if (adjacencyMatrix[v][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    /**
     * Performs the Breadth First Search (BFS) algorithm on the graph.
     *
     * @param quiet - indicates whether to print the results or not
     */
    void runBFS(boolean quiet) {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[size];

        // Recursion! Call the recursive helper function to print BFS traversal
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                breadthFirstSearchUtil(i, visited);
            }
        }

        // Results when there's no more unvisited vertices
        if (!quiet) {
            System.out.println("BFS Order: " + bfsOrder.toString());
        }
    }

    /**
     * Performs the Breadth First Search (BFS) algorithm on the graph starting at vertex v.
     *
     * @param v     - the vertex to start the BFS from
     * @param quiet - indicates whether to print the results or not
     */
    void runBFS(String v, boolean quiet) {
        // Get the index of the vertex
        int index = getIndex(v);

        // Check if the vertex is valid
        if (index != -1) {
            // Mark all the vertices as not visited
            boolean[] visited = new boolean[size];

            // Recursion! Call the recursive helper function to print BFS traversal
            breadthFirstSearchUtil(index, visited);

            // Results when there's no more unvisited vertices
            if (!quiet) {
                System.out.println("BFS Order: " + bfsOrder.toString());
            }
        } else {
            throw new IllegalArgumentException("Invalid vertex");
        }
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
