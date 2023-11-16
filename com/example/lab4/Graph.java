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
        StringBuilder result = new StringBuilder();

        for (int[] row : adjacencyMatrix) {
            for (int col : row) {
                result.append(col).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
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
