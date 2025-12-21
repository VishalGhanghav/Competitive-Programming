package SdeSheetGraphs.DfsBfs;

import java.util.*;



// Class Name: UndirectedGraphCycle
class UndirectedGraphCycle {

    class Pair {
        int val;
        int src;

        Pair(int val, int src) {
            this.val = val;
            this.src = src;
        }
    }

    /**
     * Approach 1: Depth First Search (DFS)
     * TC: O(V + 2E) - Every node is visited once, and every edge is traversed twice.
     * SC: O(V) - Recursion stack and visited array.
     */
    public boolean isCycleDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int visited[] = new int[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                if (detectDFS(i, -1, visited, adj))
                    return true;
            }
        }
        return false;
    }

    public boolean detectDFS(int node, int parent, int visited[], ArrayList<ArrayList<Integer>> adj) {
        visited[node] = 1;
        // check neighbours
        for (int i : adj.get(node)) {
            if (visited[i] == 0) {
                if (detectDFS(i, node, visited, adj) == true) {
                    return true;
                }
            } else if (i != parent) {
                return true;
            }
        }
        return false;
    }

    /**
     * Approach 2: Breadth First Search (BFS)
     * TC: O(V + 2E) - Standard BFS complexity.
     * SC: O(V) - Queue and visited array.
     */
    public boolean isCycleBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int visited[] = new int[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                if (detectBFS(i, visited, adj))
                    return true;
            }
        }
        return false;
    }

    public boolean detectBFS(int src, int visited[], ArrayList<ArrayList<Integer>> adj) {
        visited[src] = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src, -1));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int node = pair.val;
            int parent = pair.src;
            for (int neighbour : adj.get(node)) {
                // if neighbour not visited
                if (visited[neighbour] == 0) {
                    visited[neighbour] = 1;
                    q.add(new Pair(neighbour, node));
                }
                // if already visited and not equal to parent node
                // that means someone visited it before hand.Hence cycle
                else if (parent != neighbour) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        UndirectedGraphCycle graph = new UndirectedGraphCycle();
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Creating a cycle: 0-1, 1-2, 2-0
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2);
        adj.get(3).add(4); adj.get(4).add(3);

        System.out.println("Graph has cycle (DFS): " + graph.isCycleDFS(V, adj));
        System.out.println("Graph has cycle (BFS): " + graph.isCycleBFS(V, adj));
    }
}
