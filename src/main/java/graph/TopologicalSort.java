package graph;

import java.util.*;

public class TopologicalSort {

    public LinkedList<Vertex> topologicalSort(Graph graph) {
        LinkedList<Vertex> result = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();

        graph.getVertices().stream()
                .filter(vertex -> !visited.contains(vertex))
                .forEach(vertex -> {
                    visited.add(vertex);
                    dfs(graph, vertex, result, visited);
                });

        return result;
    }

    private void dfs(Graph graph, Vertex vertex, LinkedList<Vertex> result, Set<Vertex> visited) {
        Stack<Vertex> stack = new Stack<>();
        stack.push(vertex);

        while (!stack.isEmpty()) {
            Vertex current = stack.peek();
            int stackSize = stack.size();

            graph.getAdjacentVertices(current).stream()
                    .filter(next -> !visited.contains(next))
                    .forEach(next -> {
                        stack.push(next);
                        visited.add(next);
                    });

            if (stack.size() == stackSize) {
                // no new neighbor has been added
                stack.pop();
                result.addFirst(current);
            }

        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(true);

        graph.addEdge(new Vertex("undershorts"), new Vertex("pants"));
        graph.addEdge(new Vertex("undershorts"), new Vertex("shoes"));
        graph.addEdge(new Vertex("pants"), new Vertex("shoes"));
        graph.addEdge(new Vertex("pants"), new Vertex("belt"));
        graph.addEdge(new Vertex("belt"), new Vertex("jacket"));
        graph.addEdge(new Vertex("shirt"), new Vertex("belt"));
        graph.addEdge(new Vertex("shirt"), new Vertex("tie"));
        graph.addEdge(new Vertex("tie"), new Vertex("jacket"));
        graph.addEdge(new Vertex("socks"), new Vertex("shoes"));
        graph.addVertex(new Vertex("watch"));

        List<Vertex> sorted = new TopologicalSort().topologicalSort(graph);
        System.out.println(sorted);

    }
}
