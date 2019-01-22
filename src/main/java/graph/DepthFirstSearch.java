package graph;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DepthFirstSearch {

    private enum Color {
        WHITE, GRAY, BLACK
    }

    public static class DFSResult {
        private final List traversal;
        private final Map<Vertex, Vertex> parentMap;
        private final Map<Vertex, Integer> discoveryTimeMap;
        private final Map<Vertex, Integer> finishTimeMap;

        public DFSResult(List traversal,
                         Map<Vertex, Vertex> parentMap,
                         Map<Vertex, Integer> discoveryTimeMap,
                         Map<Vertex, Integer> finishTimeMap) {
            this.traversal = traversal;
            this.parentMap = parentMap;
            this.discoveryTimeMap = discoveryTimeMap;
            this.finishTimeMap = finishTimeMap;
        }

        public List getTraversal() {
            return traversal;
        }

        public Map<Vertex, Vertex> getParentMap() {
            return parentMap;
        }

        public Map<Vertex, Integer> getDiscoveryTimeMap() {
            return discoveryTimeMap;
        }

        public Map<Vertex, Integer> getFinishTimeMap() {
            return finishTimeMap;
        }
    }

    public  DFSResult dfs(Graph graph) {
        Map<Vertex, Color> colorMap = new HashMap();
        Map<Vertex, Vertex> parentMap = new HashMap();
        Map<Vertex, Integer> discoveryTimeMap = new HashMap();
        Map<Vertex, Integer> finishTimeMap = new HashMap();
        List traversal = new ArrayList();

        graph.getVertices().stream().forEach(vertex -> {
            colorMap.put(vertex, Color.WHITE);
        });

        AtomicInteger time = new AtomicInteger(0);
        graph.getVertices().stream()
                .filter(vertex -> Color.WHITE == colorMap.get(vertex))
                .forEach(vertex -> dfsVisit(graph, vertex, colorMap, parentMap, discoveryTimeMap, finishTimeMap, traversal, time));

        return new DFSResult(traversal, parentMap, discoveryTimeMap, finishTimeMap);
    }

    private  void dfsVisit(Graph graph, Vertex current,
                          Map<Vertex, Color> colorMap,
                          Map<Vertex, Vertex> parentMap,
                          Map<Vertex, Integer> discoveryTimeMap,
                          Map<Vertex, Integer> finishTimeMap,
                          List traversal,
                          AtomicInteger time) {
        discoveryTimeMap.put(current, time.incrementAndGet());
        colorMap.put(current, Color.GRAY);
        traversal.add(current.getValue());

        graph.getAdjacentVertices(current).stream()
                .filter(next -> Color.WHITE == colorMap.get(next))
                .forEach(next -> {
                    parentMap.put(next, current);
                    dfsVisit(graph, next, colorMap, parentMap, discoveryTimeMap, finishTimeMap, traversal, time);
                });

        colorMap.put(current, Color.BLACK);
        finishTimeMap.put(current, time.incrementAndGet());
    }

    public  DFSResult dfsIterative(Graph graph) {
        Map<Vertex, Color> colorMap = new HashMap();
        Map<Vertex, Vertex> parentMap = new HashMap();
        Map<Vertex, Integer> discoveryTimeMap = new HashMap();
        Map<Vertex, Integer> finishTimeMap = new HashMap();
        Map<Vertex, LinkedList<Vertex>> adjacencyList = new HashMap();
        List traversal = new ArrayList();

        graph.getVertices().stream().forEach(vertex -> {
            colorMap.put(vertex, Color.WHITE);
            adjacencyList.put(vertex, new LinkedList<>(graph.getAdjacentVertices(vertex)));
        });

        AtomicInteger time = new AtomicInteger(0);
        graph.getVertices().stream()
                .filter(vertex -> Color.WHITE == colorMap.get(vertex))
                .forEach(vertex -> {
                    discoveryTimeMap.put(vertex, time.incrementAndGet());
                    colorMap.put(vertex, Color.GRAY);
                    traversal.add(vertex.getValue());
                    dfsVisitIterative(adjacencyList, vertex, colorMap, parentMap, discoveryTimeMap, finishTimeMap, traversal, time);
                });

        return new DFSResult(traversal, parentMap, discoveryTimeMap, finishTimeMap);
    }

    private  void dfsVisitIterative(Map<Vertex, LinkedList<Vertex>> adjacencyList,
                                    Vertex vertex,
                                    Map<Vertex, Color> colorMap,
                                    Map<Vertex, Vertex> parentMap,
                                    Map<Vertex, Integer> discoveryTimeMap,
                                    Map<Vertex, Integer> finishTimeMap,
                                    List traversal,
                              AtomicInteger time) {

        Stack<Vertex> stack = new Stack();
        stack.push(vertex);

        while (!stack.isEmpty()) {
            Vertex current = stack.peek();

            if (adjacencyList.get(current).isEmpty()) {
                colorMap.put(current, Color.BLACK);
                finishTimeMap.put(current, time.incrementAndGet());
                stack.pop();

            } else {
                LinkedList<Vertex> nextVertices = adjacencyList.get(current);
                if (!nextVertices.isEmpty()) {
                    Vertex next = nextVertices.removeFirst();
                    if (Color.WHITE == colorMap.get(next)) {
                        parentMap.put(next, current);
                        stack.push(next);

                        discoveryTimeMap.put(next, time.incrementAndGet());
                        colorMap.put(next, Color.GRAY);
                        traversal.add(next.getValue());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(true);

        graph.addEdge(new Vertex('u'), new Vertex('v'));
        graph.addEdge(new Vertex('u'), new Vertex('x'));
        graph.addEdge(new Vertex('v'), new Vertex('y'));
        graph.addEdge(new Vertex('w'), new Vertex('y'));
        graph.addEdge(new Vertex('w'), new Vertex('z'));
        graph.addEdge(new Vertex('x'), new Vertex('v'));
        graph.addEdge(new Vertex('y'), new Vertex('x'));
        graph.addEdge(new Vertex('z'), new Vertex('z'));

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
        DFSResult dfsResult1 = depthFirstSearch.dfs(graph);

        System.out.println("DFS Recursive Demo:");
        System.out.println("Traversal : " + dfsResult1.getTraversal());
        System.out.println("Parent Map : " + dfsResult1.getParentMap());
        System.out.println("Discovery time Map : " + dfsResult1.getDiscoveryTimeMap());
        System.out.println("Finishing time Map : " + dfsResult1.getFinishTimeMap());

        DFSResult dfsResult2 = depthFirstSearch.dfsIterative(graph);
        System.out.println("DFS Iterative Demo:");
        System.out.println("Traversal : " + dfsResult2.getTraversal());
        System.out.println("Parent Map : " + dfsResult2.getParentMap());
        System.out.println("Discovery time Map : " + dfsResult2.getDiscoveryTimeMap());
        System.out.println("Finishing time Map : " + dfsResult2.getFinishTimeMap());
    }
}
