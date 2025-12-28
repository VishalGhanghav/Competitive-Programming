package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: BellmanFordAlgorithm
class BellmanFordAlgorithm {

    /**
     * Time Complexity: O(V * E) - We relax all E edges V-1 times.
     * Space Complexity: O(V) - To store the distance array.
     */
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        // Write your code here
        int[] dist = new int[V];
        // For this question max value they have 100000000
        // instead of interger.max_value so 100000000.10^8 ie.1e8
        Arrays.fill(dist, (int) 1e8);
        dist[S] = 0;

        // I need to do relaxation N-1 times for all edges
        for (int i = 0; i < V - 1; i++) {

            for (ArrayList<Integer> edge : edges) {
                int src = edge.get(0);
                int dest = edge.get(1);
                int distance = edge.get(2);
                if (dist[src] != 1e8 && dist[src] + distance < dist[dest]) {
                    dist[dest] = dist[src] + distance;
                }
            }
        }

        // But in the code they have mentioned if it contains negative cycle do nth iteration  as well
        for (ArrayList<Integer> edge : edges) {
            int src = edge.get(0);
            int dest = edge.get(1);
            int distance = edge.get(2);
            if (dist[src] != 1e8 && dist[src] + distance < dist[dest]) {
                return new int[]{-1};
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 6;
        int S = 0;
        // edges list: {u, v, weight}
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(3, 2, 6)));
        edges.add(new ArrayList<>(Arrays.asList(5, 3, 1)));
        edges.add(new ArrayList<>(Arrays.asList(0, 1, 5)));
        edges.add(new ArrayList<>(Arrays.asList(1, 5, -3)));
        edges.add(new ArrayList<>(Arrays.asList(1, 2, -2)));
        edges.add(new ArrayList<>(Arrays.asList(3, 4, -2)));
        edges.add(new ArrayList<>(Arrays.asList(2, 4, 3)));

        int[] result = bellman_ford(V, edges, S);

        System.out.println("Source Node: " + S);
        if (result.length == 1 && result[0] == -1) {
            System.out.println("Negative Cycle Detected!");
        } else {
            System.out.println("Shortest distances: " + Arrays.toString(result));
        }
    }
}
