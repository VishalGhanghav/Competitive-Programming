package SdeSheetGraphs.DfsBfs;

import java.util.*;

// Class Name: BipartiteGraph
class BipartiteGraph {

    /**
     * Approach 1: Depth First Search (DFS)
     * TC: O(V + 2E) - Every node is visited once, and every edge is traversed twice.
     * SC: O(V) - Recursion stack and color array.
     */
    public boolean isBipartiteDFS(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (dfs(i, 0, color, graph) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean dfs(int node, int currColor, int[] color, int graph[][]) {
        color[node] = currColor;

        for (int n : graph[node]) {
            // if not coloured before
            if (color[n] == -1) {
                // if 0 it will change to 1,
                // if 1 it will change to zero
                // if any of dfs calls return false then it is not bipartite(return false)
                if (dfs(n, 1 - currColor, color, graph) == false) {
                    return false;
                }
            } else if (color[n] == currColor) {
                // if previously coloured and has same colour as that of neighbour .It
                // mean not bipartite
                return false;
            }
        }
        // if complete dfs checked still no problem return true
        return true;
    }

    /**
     * Approach 2: Breadth First Search (BFS)
     * TC: O(V + 2E) - Standard BFS traversal.
     * SC: O(V) - Queue and color array.
     */
    public boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!checkBFS(i, graph, color)) return false;
            }
        }
        return true;
    }

    private boolean checkBFS(int start, int[][] graph, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int neighbor : graph[node]) {
                if (color[neighbor] == -1) {
                    color[neighbor] = 1 - color[node];
                    q.add(neighbor);
                } else if (color[neighbor] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BipartiteGraph solver = new BipartiteGraph();

        // Example 1: Bipartite (Square)
        int[][] graph1 = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        // Example 2: Not Bipartite (Triangle)
        int[][] graph2 = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};

        System.out.println("Graph 1 (Square) Is Bipartite: " + solver.isBipartiteDFS(graph1));
        System.out.println("Graph 2 (Triangle) Is Bipartite: " + solver.isBipartiteDFS(graph2));
    }
}