package graph.weighted;

import graph.Vertex;

import java.util.*;

public class DijkstraShortestPath {

    public void dijkstra(Graph graph, Vertex source, Map<Vertex, Integer> distanceMap, Map<Vertex, Vertex> parentMap) {
        initializeSingleSource(graph, source, distanceMap);

        PriorityQueue<Vertex> minHeap = new PriorityQueue<>(Comparator.comparing(vertex -> distanceMap.get(vertex)));
        graph.getVertices().stream()
                .forEach(vertex -> minHeap.offer(vertex));

        Set<Vertex> set = new HashSet<>();

        while (!minHeap.isEmpty()) {
            Vertex vertex = minHeap.poll();
            set.add(vertex);
            graph.getAdjacentVertices(vertex)
                    .entrySet()
                    .stream()
                    .forEach(entry -> {
                        if (relax(vertex, entry.getKey(), entry.getValue(), distanceMap, parentMap)) {
                            minHeap.remove(entry.getKey());
                            minHeap.offer(entry.getKey());
                        }
                    });
        }
    }

    private boolean relax(Vertex u, Vertex v, int weight, Map<Vertex, Integer> distanceMap, Map<Vertex, Vertex> parentMap) {
        if (distanceMap.get(u) == Integer.MAX_VALUE) {
            return false;
        }

        if (distanceMap.get(v) > distanceMap.get(u) + weight) {
            distanceMap.put(v, distanceMap.get(u) + weight);
            parentMap.put(v, u);
            return true;
        } else {
            return false;
        }
    }

    private void initializeSingleSource(Graph graph, Vertex source, Map<Vertex, Integer> distanceMap) {
        graph.getVertices().stream()
                .filter(vertex -> !vertex.equals(source))
                .forEach(vertex -> distanceMap.put(vertex, Integer.MAX_VALUE));
        distanceMap.put(source, 0);
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        Vertex s = new Vertex('s');
        Vertex t = new Vertex('t');
        Vertex x = new Vertex('x');
        Vertex y = new Vertex('y');
        Vertex z = new Vertex('z');

        graph.addEdge(s, t, 10);
        graph.addEdge(s, y, 5);
        graph.addEdge(t, x, 1);
        graph.addEdge(t, y, 2);
        graph.addEdge(y, t, 3);
        graph.addEdge(y, x, 9);
        graph.addEdge(y, z, 2);
        graph.addEdge(x, z, 4);
        graph.addEdge(z, s, 7);
        graph.addEdge(z, x, 6);

        Map<Vertex, Integer> distanceMap = new HashMap<>();
        Map<Vertex, Vertex> parentMap = new HashMap<>();

        new DijkstraShortestPath().dijkstra(graph, s, distanceMap, parentMap);

        distanceMap.keySet().stream().forEach(vertex -> {
            System.out.println(String.format("Vertex %s, distance from s = %s, predecessor = %s",
                                             vertex.getValue(), distanceMap.get(vertex), parentMap.getOrDefault(vertex, new Vertex(' ')).getValue()));
        });
    }
}
