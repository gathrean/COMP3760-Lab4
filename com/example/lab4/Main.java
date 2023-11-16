package com.example.lab4;

public class Main {
    public static void main(String[] args) {
        // Define vertices
        String[] vertices = {"a", "b", "c", "d", "e", "f", "g", "h"};

        // Create a graph
        Graph G = new Graph(vertices, true); // Directed graph

        // Add edges
        G.addEdge("a", "b");
        G.addEdge("a", "e");
        G.addEdge("a", "f");
        G.addEdge("b", "f");
        G.addEdge("b", "g");
        G.addEdge("c", "d");
        G.addEdge("c", "g");
        G.addEdge("d", "h");
        G.addEdge("e", "f");
        G.addEdge("g", "h");

        // Perform DFS and BFS traversals
        G.runDFS(false); // DFS traversal without printing

        System.out.println("\n");

        G.runBFS(false); // BFS traversal without printing
    }
}