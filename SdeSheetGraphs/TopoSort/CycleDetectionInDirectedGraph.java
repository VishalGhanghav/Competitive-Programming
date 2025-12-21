package SdeSheetGraphs.TopoSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class CycleDetectionInDirectedGraph {

    /**
     * APPROACH 1: BFS (Kahn's Algorithm)
     * Logic: If the number of nodes in Topo Sort != V, there is a cycle.
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public boolean isCyclicBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        int count = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            count++;

            for (int neighbor : adj.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) q.add(neighbor);
            }
        }

        // If count of sorted nodes is not equal to V, there's a cycle
        return count != V;
    }

    /**
     * APPROACH 2: DFS (Original Logic style + pathVisited)
     * Logic: Uses recursion stack (pathVisited) to find back-edges.
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public boolean isCyclicDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] visited = new int[V];
        int[] pathVisited = new int[V];

        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                if (dfsCheck(i, adj, visited, pathVisited)) return true;
            }
        }
        return false;
    }

    private boolean dfsCheck(int node, ArrayList<ArrayList<Integer>> adj, int[] visited, int[] pathVisited) {
        visited[node] = 1;
        pathVisited[node] = 1;

        for (int neighbor : adj.get(node)) {
            // If neighbor not visited, recurse
            if (visited[neighbor] == 0) {
                if (dfsCheck(neighbor, adj, visited, pathVisited)) return true;
            }
            // If neighbor is visited on the same path, cycle detected
            else if (pathVisited[neighbor] == 1) {
                return true;
            }
        }

        pathVisited[node] = 0; // Backtrack: remove from current path
        return false;
    }

    public static void main(String[] args) {
        CycleDetectionInDirectedGraph detector = new CycleDetectionInDirectedGraph();
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Creating a Cycle: 0 -> 1 -> 2 -> 3 -> 1
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(1);



        System.out.println("--- Cycle Detection Results ---");
        System.out.println("BFS (Kahn's) detects cycle: " + detector.isCyclicBFS(V, adj));
        System.out.println("DFS (Recursion) detects cycle: " + detector.isCyclicDFS(V, adj));
    }
}