package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: CityWithFewestNeighbors
class CityWithFewestNeighbors {

    // Helper class kept as inner class per instructions
    static class Pair {
        int node;
        int distance;
        Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * Approach 1: Floyd-Warshall Algorithm
     * TC: O(N^3) - Triple nested loops for all-pairs shortest paths.
     * SC: O(N^2) - To store the distance matrix.
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // In the original floyd warshal dist matrix was given here we need to create it
        int[][] dist = new int[n][n];
        // fill with max value at start
        for (int[] arr : dist) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        // Whatever distances provided in edges array they will be set in my distance matrix
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int distance = edges[i][2];
            dist[u][v] = distance;
            dist[v][u] = distance;
        }

        // If same node ie. 0,0 1,1 2,2. I will set dist=0
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }



        // Now I will apply floyd warshal.
        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][via] == Integer.MAX_VALUE || dist[via][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                }
            }
        }

        int city = -1;
        int cntMax = n + 1;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= cntMax) {
                cntMax = cnt;
                city = Math.max(city, i);
            }
        }
        return city;
    }

    /**
     * Approach 2: Multi-source Dijkstra
     * TC: O(N * E log V) - Running Dijkstra starting from every city.
     * SC: O(V + E) - Adjacency list and distance array.
     */
    public int findTheCityDijkstra(int n, int[][] edges, int distanceThreshold) {
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
            adj.get(edge[1]).add(new Pair(edge[0], edge[2]));
        }

        int minCount = n + 1;
        int resultCity = -1;

        for (int i = 0; i < n; i++) {
            int count = getReachableCities(i, n, adj, distanceThreshold);
            if (count <= minCount) {
                minCount = count;
                resultCity = i;
            }
        }
        return resultCity;
    }

    private int getReachableCities(int startNode, int n, List<List<Pair>> adj, int threshold) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[startNode] = 0;
        pq.add(new Pair(startNode, 0));
        int count = 0;

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;
            int d = curr.distance;

            if (d > dist[u]) continue;

            for (Pair neighbor : adj.get(u)) {
                if (dist[u] + neighbor.distance <= threshold && dist[u] + neighbor.distance < dist[neighbor.node]) {
                    dist[neighbor.node] = dist[u] + neighbor.distance;
                    pq.add(new Pair(neighbor.node, dist[neighbor.node]));
                }
            }
        }

        for (int d : dist) {
            if (d <= threshold) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        CityWithFewestNeighbors solver = new CityWithFewestNeighbors();
        int n = 4;
        int[][] edges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold = 4;

        System.out.println("Result using Floyd-Warshall: " + solver.findTheCity(n, edges, distanceThreshold));
        System.out.println("Result using Dijkstra: " + solver.findTheCityDijkstra(n, edges, distanceThreshold));
    }
}
