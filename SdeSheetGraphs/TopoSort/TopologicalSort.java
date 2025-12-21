package SdeSheetGraphs.TopoSort;

import java.util.*;

// Class name updated to follow standard naming conventions
class TopologicalSort {

    /**
     * APPROACH: BFS (Kahn's Algorithm) - Better/Optimal
     * Time Complexity: O(V + E) - We visit every node and edge once.
     * Space Complexity: O(V) - For the in-degree array and queue.
     */
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        int[] inDegree = new int[V];
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int curr = q.poll();
            res.add(curr);

            for (int neighbor : adj.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }
        return res;
    }

    /**
     * APPROACH: DFS (Original Logic) - Brute/Alternative
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) - Recursion stack and auxiliary Stack.
     */
    static int[] topoSortDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        // Keeping original logic and variable names intact
        int[] visited = new int[V];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                dfs(i, visited, adj, s);
            }
        }
        int i = 0;
        int[] res = new int[V];
        while (!s.isEmpty()) {
            res[i] = s.pop();
            i++;
        }
        return res;
    }

    // Original DFS helper logic
    static void dfs(int node, int[] visited, ArrayList<ArrayList<Integer>> adj, Stack<Integer> s) {
        visited[node] = 1;
        for (int i : adj.get(node)) {
            if (visited[i] == 0) {
                dfs(i, visited, adj, s);
            }
        }
        s.add(node);
    }

    /**
     * Main method to run each approach independently.
     */
    public static void main(String[] args) {
        TopologicalSort solution = new TopologicalSort();
        int V = 4;
        int[][] edges = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        // 3->1-> 0
        // |
        // 2->0
        // Running BFS Approach
        System.out.println("--- BFS (Kahn's Algorithm) Output ---");
        ArrayList<Integer> bfsResult = solution.topoSort(V, edges);
        System.out.println(bfsResult);

        // Running DFS Approach (Converting edges to adj list for compatibility)
        System.out.println("\n--- DFS (Original Logic) Output ---");
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) adj.get(edge[0]).add(edge[1]);

        int[] dfsResult = topoSortDFS(V, adj);
        for (int val : dfsResult) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
