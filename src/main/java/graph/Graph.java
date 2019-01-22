package graph;

import java.util.*;

public class Graph {

    private Map<Vertex, Set<Vertex>> adjacencyList = new HashMap<>();

    private boolean directed = false;

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public void addVertex(Vertex v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashSet<>());
        }
    }

    public void addEdge(Vertex u, Vertex v) {
        addVertex(u);
        addVertex(v);
        adjacencyList.get(u).add(v);
        if (!directed) {
            adjacencyList.get(v).add(u);
        }
    }

    public Set<Vertex> getVertices() {
        return adjacencyList.keySet();
    }

    public Set<Vertex> getAdjacentVertices(Vertex u) {
        return adjacencyList.getOrDefault(u, Collections.emptySet());
    }
}
