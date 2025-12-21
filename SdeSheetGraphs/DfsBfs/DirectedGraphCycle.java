package SdeSheetGraphs.DfsBfs;

import java.util.*;

// Class Name: DirectedGraphCycle
class DirectedGraphCycle {

    /**
     * Approach 1: Depth First Search (DFS) using Path Recording
     * TC: O(V + E) - Every node and edge is visited at most once.
     * SC: O(V) - For visited array, path array, and recursion stack.
     */
    public boolean isCycleDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int visited[] = new int[V];
        int path[] = new int[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                if (dfs(i, visited, path, adj) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int node, int[] visited, int[] path, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = 1;
        path[node] = 1;

        for (int it : adj.get(node)) {

            if (visited[it] == 0) {
                // if node is not visited visit it
                if (dfs(it, visited, path, adj) == true) {
                    // if any dfs returns rteu no need to check further return true
                    return true;
                }
            }
            // if node is visited already and
            // on same path also I have traversed before(path[it]==1.It means we have found a cycle
            else if (path[it] == 1) {
                return true;
            }
        }
        path[node] = 0;
        return false;
    }

    public static void main(String[] args) {
        DirectedGraphCycle solver = new DirectedGraphCycle();
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Creating a cycle: 0 -> 1 -> 2 -> 3 -> 1
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(1);

        System.out.println("Directed Graph has cycle: " + solver.isCycleDFS(V, adj));
    }
}