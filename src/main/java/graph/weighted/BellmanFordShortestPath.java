package graph.weighted;

import graph.Vertex;

import java.util.HashMap;
import java.util.Map;

public class BellmanFordShortestPath {

    public boolean bellmanFord(Graph graph, Vertex s, Map<Vertex, Integer> distanceMap, Map<Vertex, Vertex> parentMap) {
        initializeSingleSource(graph, s, distanceMap);
        int numVertices = graph.getVertices().size();
        for (int i = 0; i < numVertices - 1; i++) {
            graph.getAdjacencyList()
                    .entrySet()
                    .stream()
                    .filter(vertexEntry -> !vertexEntry.getValue().isEmpty())
                    .forEach(vertexEntry ->    // vertexEntry.getKey() = vertex u
                        vertexEntry.getValue()
                                .entrySet()
                                .stream()
                                .forEach(adjacentEntry -> relaxation(   // adjacentEntry.getKey() = vertex v and adjacentEntry.getValue = w(u, v)
                                        vertexEntry.getKey(),
                                        adjacentEntry.getKey(),
                                        adjacentEntry.getValue(),
                                        distanceMap,
                                        parentMap))
                    );
        }

        return graph.getAdjacencyList()
                .entrySet()
                .stream()
                .filter(vertexEntry -> !vertexEntry.getValue().isEmpty())
                .anyMatch(vertexEntry ->    // vertexEntry.getKey() = vertex u
                    vertexEntry.getValue()
                            .entrySet()
                            .stream()
                            .anyMatch(adjacentEntry -> distanceMap.get(adjacentEntry.getKey()) >    // adjacentEntry.getKey() = vertex v and adjacentEntry.getValue = w(u, v)
                                                       distanceMap.get(vertexEntry.getKey()) + adjacentEntry.getValue())
                );
    }

    private void initializeSingleSource(Graph graph, Vertex s, Map<Vertex, Integer> distanceMap) {
        graph.getVertices().forEach(vertex -> {
            distanceMap.put(vertex, Integer.MAX_VALUE);
        });
        distanceMap.put(s, 0);
    }

    private void relaxation(Vertex u, Vertex v, int weight, Map<Vertex, Integer> distanceMap, Map<Vertex, Vertex> parentMap) {
        if (distanceMap.get(u) == Integer.MAX_VALUE) {
            return;
        }

        if (distanceMap.get(v) > distanceMap.get(u) + weight) {
            distanceMap.put(v, distanceMap.get(u) + weight);
            parentMap.put(v, u);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge(new Vertex('s'), new Vertex('t'), 6);
        graph.addEdge(new Vertex('s'), new Vertex('y'), 7);
        graph.addEdge(new Vertex('t'), new Vertex('x'), 5);
        graph.addEdge(new Vertex('t'), new Vertex('y'), 8);
        graph.addEdge(new Vertex('t'), new Vertex('z'), -4);
        graph.addEdge(new Vertex('y'), new Vertex('x'), -3);
        graph.addEdge(new Vertex('y'), new Vertex('z'), 9);
        graph.addEdge(new Vertex('x'), new Vertex('t'), -2);
        graph.addEdge(new Vertex('z'), new Vertex('x'), 7);
        graph.addEdge(new Vertex('z'), new Vertex('s'), 2);

        Map<Vertex, Integer> distanceMap = new HashMap<>();
        Map<Vertex, Vertex> parentMap = new HashMap<>();

        BellmanFordShortestPath shortestPath = new BellmanFordShortestPath();
        shortestPath.bellmanFord(graph, new Vertex('s'), distanceMap, parentMap);

        distanceMap.keySet().stream().forEach(vertex -> {
            System.out.println(String.format("Vertex %s, distance from s = %s, predecessor = %s",
                                             vertex.getValue(), distanceMap.get(vertex), parentMap.getOrDefault(vertex, new Vertex(' ')).getValue()));
        });
    }
}
