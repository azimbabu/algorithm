package graph.weighted;

import graph.Vertex;

import java.util.*;

public class Graph {

    private Map<Vertex, Map<Vertex, Integer>> adjacencyList = new HashMap<>();

    public void addVertex(Vertex v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashMap<>());
        }
    }

    public void addEdge(Vertex u, Vertex v, int weight) {
        addVertex(u);
        addVertex(v);

        if (!adjacencyList.get(u).containsKey(v)) {
            adjacencyList.get(u).put(v, weight);
        }
    }

    public Set<Vertex> getVertices() {
        return adjacencyList.keySet();
    }

    public Map<Vertex, Integer> getAdjacentVertices(Vertex u) {
        return adjacencyList.getOrDefault(u, Collections.emptyMap());
    }

    public Map<Vertex, Map<Vertex, Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}
