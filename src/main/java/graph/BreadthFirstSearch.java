package graph;

import java.util.*;

public class BreadthFirstSearch {

    private enum Color {
        WHITE, GRAY, BLACK
    }

    public static class BFSResult {
        private final List traversal;
        private final Map<Vertex, Vertex> parentMap;
        private final Map<Vertex, Integer> distanceMap;

        public BFSResult(List traversal,
                         Map<Vertex, Vertex> parentMap,
                         Map<Vertex, Integer> distanceMap) {
            this.traversal = traversal;
            this.parentMap = parentMap;
            this.distanceMap = distanceMap;
        }

        public List getTraversal() {
            return traversal;
        }

        public Map<Vertex, Vertex> getParentMap() {
            return parentMap;
        }

        public Map<Vertex, Integer> getDistanceMap() {
            return distanceMap;
        }
    }

    public  BFSResult bfs(Graph graph, Vertex source) {
        Map<Vertex, Color> colorMap = new HashMap<>();
        Map<Vertex, Vertex> parentMap = new HashMap<>();
        Map<Vertex, Integer> distanceMap = new HashMap<>();

        graph.getVertices().stream().forEach(vertex -> {
            colorMap.put(vertex, Color.WHITE);
            distanceMap.put(vertex, Integer.MAX_VALUE);
        });

        colorMap.put(source, Color.GRAY);
        distanceMap.put(source, 0);

        List traversal = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            int distance = distanceMap.get(current);
            traversal.add(current.getValue());

            graph.getAdjacentVertices(current)
                    .stream()
                    .filter(next -> Color.WHITE == colorMap.get(next))
                    .forEach(next -> {
                        colorMap.put(next, Color.GRAY);
                        distanceMap.put(next, distance+1);
                        parentMap.put(next, current);
                        queue.offer(next);
            });

            colorMap.put(current, Color.BLACK);
        }

        return new BFSResult(traversal, parentMap, distanceMap);
    }

    public  String buildPath(Map<Vertex, Vertex> parentMap, Vertex s, Vertex v) {
        StringBuilder pathBuilder = new StringBuilder();
        buildPathHelper(parentMap, s, v, pathBuilder);
        if (pathBuilder.length() != 0) {
            pathBuilder.deleteCharAt(pathBuilder.length()-1);
        }
        return pathBuilder.toString();
    }

    private  void buildPathHelper(Map<Vertex, Vertex> parentMap, Vertex s, Vertex v, StringBuilder pathBuilder) {
        if (s == v) {
            pathBuilder.append(s + ",");
        } else if (!parentMap.containsKey(v)) {
            return;
        } else {
            buildPathHelper(parentMap, s, parentMap.get(v), pathBuilder);
            pathBuilder.append(v + ",");
        }
    }

    public static void main(String[] args) {
        Vertex r = new Vertex('r');
        Vertex s = new Vertex('s');
        Vertex t = new Vertex('t');
        Vertex u = new Vertex('u');
        Vertex v = new Vertex('v');
        Vertex w = new Vertex('w');
        Vertex x = new Vertex('x');
        Vertex y = new Vertex('y');

        Graph graph = new Graph(false);
        graph.addEdge(r, s);
        graph.addEdge(r, v);
        graph.addEdge(s, w);
        graph.addEdge(t, u);
        graph.addEdge(t, w);
        graph.addEdge(t, x);
        graph.addEdge(u, x);
        graph.addEdge(u, y);
        graph.addEdge(w, x);
        graph.addEdge(x, y);

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        BFSResult result = breadthFirstSearch.bfs(graph, s);
        System.out.println("Traversal : " + result.getTraversal());
        System.out.println("Parent Map : " + result.getParentMap());
        System.out.println("Distance Map : " + result.getDistanceMap());

        graph.getVertices().stream().filter(vertex -> !vertex.equals(s)).forEach(vertex -> {
            System.out.println(String.format("Path from %s to %s : %s", s, vertex, breadthFirstSearch.buildPath(result.getParentMap(), s, vertex)));
        });
    }
}
