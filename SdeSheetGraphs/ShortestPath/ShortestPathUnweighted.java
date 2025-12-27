package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: ShortestPathUnweighted
class ShortestPathUnweighted {

    // Side class kept under main class per instructions
    class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    /**
     * Time Complexity: O(V + 2E) - Every node is added to the queue once, and every edge is traversed twice.
     * Space Complexity: O(V + E) - To store the adjacency list, distance array, and the BFS queue.
     */
    public int[] shortestPath(int[][] edges, int n, int m, int src) {
        // Code here
        // I will follow BFS
        int dist[] = new int[n];
        List<List<Integer>> adjList = new ArrayList<>();
        // create adj list
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        // m because we are traversing through edges matrix
        for (int i = 0; i < m; i++) {
            // connect 0->1 and 1->0
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }

        // now perform graph
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src, 0));
        dist[src] = 0;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int node = pair.node;
            int dis = pair.dist;
            for (int nb : adjList.get(node)) {
                // if new distance is first time add in queue and set distance as well
                if (dis + 1 < dist[nb]) {
                    // if not first time check if new distance is less then update the dist array
                    dist[nb] = dis + 1;
                    q.add(new Pair(nb, dist[nb]));
                }

            }
        }
        // after completing ,whichever elements are still set to interger maxvalue.Set them to -1
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        ShortestPathUnweighted solver = new ShortestPathUnweighted();
        int n = 9, m = 10;
        int[][] edges = {
                {0, 1}, {0, 3}, {3, 4}, {4, 5}, {5, 6},
                {1, 2}, {2, 6}, {6, 7}, {7, 8}, {6, 8}
        };
        int src = 0;

        int[] result = solver.shortestPath(edges, n, m, src);

        System.out.println("Source Node: " + src);
        System.out.println("Shortest distances from source: " + Arrays.toString(result));
    }
}
