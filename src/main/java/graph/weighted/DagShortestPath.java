package graph.weighted;

import graph.TopologicalSort;
import graph.Vertex;

import java.util.*;

/**
 * Single source shortest path in directed acyclic graphs
 */
public class DagShortestPath {

    public void dagShortestPath(Graph graph, Vertex source, Map<Vertex, Integer> distanceMap, Map<Vertex, Vertex> parentMap) {
        LinkedList<Vertex> sorted = topologicalSort(graph);
        initializeSingleSource(graph, source, distanceMap);

        while (!sorted.isEmpty()) {
            Vertex vertex = sorted.removeFirst();
            graph.getAdjacentVertices(vertex)
                    .entrySet()
                    .stream()
                    .forEach(entry -> relax(vertex, entry.getKey(), entry.getValue(), distanceMap, parentMap));
        }

    }

    private void relax(Vertex u, Vertex v, int weight, Map<Vertex, Integer> distanceMap, Map<Vertex, Vertex> parentMap) {
        if (distanceMap.get(u) == Integer.MAX_VALUE) {
            return;
        }

        if (distanceMap.get(v) > distanceMap.get(u) + weight) {
            distanceMap.put(v, distanceMap.get(u) + weight);
            parentMap.put(v, u);
        }
    }

    private void initializeSingleSource(Graph graph,
                                        Vertex source,
                                        Map<Vertex, Integer> distanceMap) {
        graph.getVertices()
                .stream()
                .filter(vertex -> vertex != source)
                .forEach(vertex -> distanceMap.put(vertex, Integer.MAX_VALUE));
        distanceMap.put(source, 0);
    }

    private LinkedList<Vertex> topologicalSort(Graph graph) {
        LinkedList<Vertex> sorted = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();

        graph.getVertices().stream()
                .filter(vertex -> !visited.contains(vertex))
                .forEach(vertex -> dfs(graph, vertex, visited, sorted));
        return sorted;
    }

    private void dfs(Graph graph, Vertex start, Set<Vertex> visited, LinkedList<Vertex> sorted) {
        Stack<Vertex> stack = new Stack<>();
        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            int stackSize = stack.size();
            Vertex current = stack.peek();

            graph.getAdjacentVertices(current)
                    .entrySet()
                    .stream()
                    .filter(entry -> !visited.contains(entry.getKey()))
                    .forEach(entry -> {
                        stack.push(entry.getKey());
                        visited.add(entry.getKey());
                    });

            if (stackSize == stack.size()) {
                sorted.addFirst(current);
                stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        Vertex source = new Vertex('s');
        graph.addEdge(new Vertex('r'), source, 5);
        graph.addEdge(new Vertex('r'), new Vertex('t'), 3);
        graph.addEdge(source, new Vertex('t'), 2);
        graph.addEdge(source, new Vertex('x'), 6);
        graph.addEdge(new Vertex('t'), new Vertex('x'), 7);
        graph.addEdge(new Vertex('t'), new Vertex('y'), 4);
        graph.addEdge(new Vertex('t'), new Vertex('z'), 2);
        graph.addEdge(new Vertex('x'), new Vertex('y'), -1);
        graph.addEdge(new Vertex('x'), new Vertex('z'), 1);
        graph.addEdge(new Vertex('y'), new Vertex('z'), -2);

        Map<Vertex, Integer> distanceMap = new HashMap<>();
        Map<Vertex, Vertex> parentMap = new HashMap<>();

        new DagShortestPath().dagShortestPath(graph, source, distanceMap, parentMap);
        distanceMap.keySet().stream().forEach(vertex -> {
            System.out.println(String.format("Vertex %s, distance from s = %s, predecessor = %s",
                                             vertex.getValue(), distanceMap.get(vertex), parentMap.getOrDefault(vertex, new Vertex(' ')).getValue()));
        });
    }
}
