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

    // DFS instance variables
    private List<String> dfsOrder = new ArrayList<>();
    private List<String> finishedOrder = new ArrayList<>();

    // BFS instance variable
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
        int x = getIndex(A);
        int y = getIndex(B);

        adjacencyMatrix[x][y] = 1;

        if (!isDirected) {
            adjacencyMatrix[y][x] = 1;
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
    @Override
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
     * Helper method to perform DFS on the graph.
     *
     * @param vertex  - the vertex to start the DFS from
     * @param visited - the list of visited vertices
     * @param quiet   - indicates whether to print the results or not
     */
    public void runDFSHelper(String vertex, ArrayList<String> visited, boolean quiet) {
        int index = getIndex(vertex);
        visited.add(vertex);
        dfsOrder.add(vertex);

        boolean deadEnd = true;

        // If not visited, visit.
        if (!quiet) {
            System.out.println("DFS visiting vertex " + vertex);
        }

        // Iterating through the adjacency matrix row corresponding to 'vertex'
        for (int i = 0; i < size; i++) {
            if (adjacencyMatrix[index][i] == 1 && !visited.contains(vertexLabels[i])) {
                // Recursion! If the vertex has not been visited, call the recursive helper function
                runDFSHelper(vertexLabels[i], visited, quiet);
                deadEnd = false;
            }
        }

        if (deadEnd) {
            finishedOrder.add(vertex);
        }
    }

    /**
     * Performs Depth First Search (DFS) on the graph.
     *
     * @param quiet - indicates whether to print the results or not
     */
    void runDFS(boolean quiet) {
        ArrayList<String> visited = new ArrayList<String>();

        for (String vertex : vertexLabels) {
            if (!visited.contains(vertex)) {
                runDFSHelper(vertex, visited, quiet);
            }
        }
    }

    /**
     * Performs Depth First Search (DFS) on the graph starting at vertex v.
     *
     * @param v     - the vertex to start the DFS from
     * @param quiet - indicates whether to print the results or not
     */
    void runDFS(String v, boolean quiet) {
        ArrayList<String> visited = new ArrayList<>();
        runDFSHelper(v, visited, quiet);
    }

    private void breadthFirstSearchUtil(int v, boolean[] visited, boolean quiet) {
        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.add(v);

        if (!quiet) {
            System.out.println("BFS visiting vertex " + vertexLabels[v]);
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            bfsOrder.add(vertexLabels[current]);

            for (int i = 0; i < size; i++) {
                if (adjacencyMatrix[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);

                    if (!quiet) {
                        System.out.println("BFS visiting vertex " + vertexLabels[i]);
                    }
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
        boolean[] visited = new boolean[size];

        if (!quiet) {
            System.out.println("BFS traversal of graph:");
        }

        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                breadthFirstSearchUtil(i, visited, quiet);
            }
        }

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
            breadthFirstSearchUtil(index, visited, quiet);

            // Results when there's no more unvisited vertices
            if (!quiet) {
                System.out.println("BFS Order: " + bfsOrder.toString());
            }
        } else {
            throw new IllegalArgumentException("Invalid vertex");
        }
    }

    /**
     * Gets result of the most recently performed DFS
     *
     * @return - a string containing the DFS order results of the most recently performed DFS
     */
    String getLastDFSOrder() {
        if (dfsOrder.isEmpty()) {
            return "No DFS order found";
        } else {
            return dfsOrder.toString();
        }
    }

    /**
     * Gets result of the most recently performed BFS
     *
     * @return - a string containing the BFS order results of the most recently performed BFS
     */
    String getLastBFSOrder() {
        if (bfsOrder.isEmpty()) {
            return "No BFS order found";
        } else {
            return bfsOrder.toString();
        }
    }

    /**
     * Gets result of the most recently performed DFS
     *
     * @return - a string containing the DFS order results of the most recently performed DFS
     */
    String getLastDFSDeadEndOrder() {
        if (finishedOrder.isEmpty()) {
            return "No DFS order found";
        } else {
            return finishedOrder.toString();
        }
    }

}
