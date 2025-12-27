package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: NetworkDelayTime
class NetworkDelayTime {

    // Helper class kept as inner class per instructions
    class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * Time Complexity: O(E * log V) - Where E is the number of times (edges) and V is n (nodes).
     * Space Complexity: O(V + E) - For the adjacency list and the distance array.
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // We see minimum or shortest. Looks like Dijkstra
        // Lets create dist array then
        int[] dist = new int[n + 1];
        // fill with maxValue
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // We have data in edges format. Lets get it in adjList
        List<List<Pair>> adjList = new ArrayList<>();
        int m = times.length;

        // why not just 4. Because we can have 4 as node as well.
        // And we will get array index out of bound for that.
        // The reason: we have 1 based indexing
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int src = times[i][0];
            int end = times[i][1];
            int distance = times[i][2];
            adjList.get(src).add(new Pair(end, distance));
        }

        // I will need time, node, distance in queue
        // We need pq as time can change and we dont care about distance.
        // So need to add in pq
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.first - y.first);
        pq.add(new Pair(0, k));

        while (!pq.isEmpty()) {
            // Get all data from queue triplet
            Pair p = pq.poll();
            int time = p.first;
            int node = p.second;

            // Logic for Dijkstra relaxation
            for (Pair pair : adjList.get(node)) {
                int adjNode = pair.first;
                int adjTime = pair.second;

                // If my new distance is less than whatever sitting in dist array.
                // add in queue and update dist array
                if ((time + adjTime) < dist[adjNode]) {
                    dist[adjNode] = time + adjTime;
                    pq.add(new Pair(time + adjTime, adjNode));
                }
            }
        }

        int resultTime = 0;
        // I need to find if all nodes were visited
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            } else {
                resultTime = Math.max(resultTime, dist[i]);
            }
        }
        return resultTime;
    }

    public static void main(String[] args) {
        NetworkDelayTime solver = new NetworkDelayTime();

        // Example: n nodes, times[i] = {u, v, w}, source k
        int n = 4;
        int k = 2;
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };

        int result = solver.networkDelayTime(times, n, k);

        System.out.println("Network Delay Time (Source " + k + "): " + result);
    }
}
