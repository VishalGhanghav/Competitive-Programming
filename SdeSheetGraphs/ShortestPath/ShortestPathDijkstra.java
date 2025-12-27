package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: ShortestPathDijkstra
import java.util.*;

// Class Name: ShortestPathDijkstra
class ShortestPathDijkstra {

    // Inner class kept under main class per instructions
    static class Pair {
        int distance;
        int node;

        Pair(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }
    }

    /**
     * Time Complexity: O(E * log V) - Where E is the number of edges and V is the number of vertices.
     * Space Complexity: O(V + E) - For the adjacency list and distance array.
     */
    public int[] dijkstra(int V, int[][] edges, int src) {
        // 1. Create Adjacency List from Edge List
        // edges[i] = [u, v, weight]
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            // Since Dijkstra is usually for directed graphs, but the explanation
            // suggests bidirectional reachability (2->1->0), we treat it as undirected.
            adj.get(u).add(new Pair(wt, v));
            adj.get(v).add(new Pair(wt, u));
        }

        // 2. Initialize Distance Array
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // 3. Priority Queue for Min-Heap
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        minHeap.add(new Pair(0, src));

        while (!minHeap.isEmpty()) {
            Pair p = minHeap.poll();
            int pdistance = p.distance;
            int pnode = p.node;

            // Relaxation Step
            for (Pair neighbor : adj.get(pnode)) {
                int adjNode = neighbor.node;
                int edgeWeight = neighbor.distance;

                if (pdistance + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = pdistance + edgeWeight;
                    minHeap.add(new Pair(dist[adjNode], adjNode));
                }
            }
        }

        // Convert any remaining MAX_VALUE to -1 if unreachable (not required by your example but safe)
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }

        return dist;
    }

    public static void main(String[] args) {
        ShortestPathDijkstra solver = new ShortestPathDijkstra();

        int V = 3;
        int[][] edges = {
                {0, 1, 1},
                {1, 2, 3},
                {0, 2, 6}
        };
        int src = 2;

        int[] result = solver.dijkstra(V, edges, src);

        System.out.println("Input: V = 3, Source = 2");
        System.out.println("Shortest Distances: " + Arrays.toString(result));
    }
}