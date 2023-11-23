import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Gathrean Dela Cruz
 * A01167248
 * <p>
 * This program represents a Graph with the ability to perform DFS
 * and BFS, with some basic graph operations as well.
 */
public class Graph {

    // Instance variables
    private int size;
    private String[] names;
    private boolean isDirected;
    int[][] adjacencyMatrix;

    // DFS instance variables
    private List<String> dfsOrder = new ArrayList<>();
    private List<String> finishedOrder = new ArrayList<>();

    // BFS instance variable
    private List<String> bfsOrder = new ArrayList<>();

    /**
     * Constructor to initialize the Graph.
     *
     * @param names      - array of Strings that represent the names of the vertices
     * @param isDirected - indicates whether the graph is directed or not
     */
    Graph(String[] names, boolean isDirected) {
        this.size = names.length;
        this.names = names;
        this.isDirected = isDirected;

        adjacencyMatrix = new int[size][size];
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
            if (names[i].equals(label)) {
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
    public int size() {
        return names.length;
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
    public String getLabel(int v) {
        return names[v];
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
            // Loop through each column in the row
            for (int col : row) {
                result.append(col).append(" ");
            }
            result.append("\n");
        }

        return result.toString().trim();
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
            if (adjacencyMatrix[index][i] == 1 && !visited.contains(getLabel(i))) {
                // Recursion! If the vertex has not been visited, call the recursive helper function
                runDFSHelper(getLabel(i), visited, quiet);
            }
        }
        // If the vertex is a dead end, add it to the finishedOrder list
        finishedOrder.add(vertex);
    }

    /**
     * Performs Depth First Search (DFS) on the graph.
     *
     * @param quiet - indicates whether to print the results or not
     */
    public void runDFS(boolean quiet) {
        ArrayList<String> visited = new ArrayList<>();

        // Clear the dfsOrder and finishedOrder lists
        dfsOrder.clear();
        finishedOrder.clear();

        // Loop through each vertex in the graph
        for (String vertex : names) {
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

        // Clear the dfsOrder and finishedOrder lists
        dfsOrder.clear();
        finishedOrder.clear();

        runDFSHelper(v, visited, quiet);
    }

    /**
     * Helper method to perform BFS on the graph.
     *
     * @param vertex  - the vertex to start the BFS from
     * @param visited - the list of visited vertices
     * @param quiet   - indicates whether to print the results or not
     */
    public void runBFSHelper(String vertex, ArrayList<String> visited, boolean quiet) {
        Queue<String> queue = new LinkedList<>();

        // Add the vertex to the queue and mark it as visited
        visited.add(vertex);
        queue.add(vertex);
        bfsOrder.add(vertex);

        // While the queue is not empty, remove the first vertex in the queue
        while (!queue.isEmpty()) {
            String currentVertex = queue.poll();
            int currentIndex = getIndex(currentVertex);

            if (!quiet) {
                System.out.println("BFS visiting vertex " + currentVertex);
            }

            for (int i = 0; i < size; i++) {
                if (adjacencyMatrix[currentIndex][i] == 1 && !visited.contains(getLabel(i))) {
                    queue.add(getLabel(i));
                    visited.add(getLabel(i));
                    bfsOrder.add(getLabel(i));
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
        ArrayList<String> visited = new ArrayList<>();

        // Clear the bfsOrder list
        bfsOrder.clear();

        for (String vertex : names) {
            if (!visited.contains(vertex)) {
                runBFSHelper(vertex, visited, quiet);
            }
        }
    }

    /**
     * Performs the Breadth First Search (BFS) algorithm on the graph starting at vertex v.
     *
     * @param v     - the vertex to start the BFS from
     * @param quiet - indicates whether to print the results or not
     */
    void runBFS(String v, boolean quiet) {
        ArrayList<String> visited = new ArrayList<>();

        // Clear the bfsOrder list
        bfsOrder.clear();

        // Create a queue and add the starting vertex to it
        Queue<String> queue = new LinkedList<>();
        queue.add(v);
        visited.add(v);

        // While the queue is not empty, remove the first vertex in the queue
        while (!queue.isEmpty()) {
            String currentVertex = queue.poll();
            bfsOrder.add(currentVertex);
            if (!quiet) {
                System.out.println("BFS visiting vertex " + currentVertex);
            }

            int currentIndex = getIndex(currentVertex);
            for (int i = 0; i < size; i++) {
                if (adjacencyMatrix[currentIndex][i] == 1 && !visited.contains(names[i])) {
                    queue.add(getLabel(i));
                    visited.add(getLabel(i));
                }
            }
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
            return String.join(", ", dfsOrder);
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
            return String.join(", ", bfsOrder);
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
            return String.join(", ", finishedOrder);
        }
    }

}
