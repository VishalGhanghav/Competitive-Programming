package SdeSheetGraphs.DfsBfs;

import java.util.*;

// Class Name: GraphConnectedComponents
class GraphConnectedComponents {

    /**
     * Approach 1: Breadth First Search (BFS)
     * Time Complexity: O(V + E) - Every vertex and edge is visited once.
     * Space Complexity: O(V + E) - To store the adjacency list and visited array.
     */
    public int countComponentsBFS(int V, int[][] edges) {
        // Create adjacency list from edge list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        // Array to keep track of visited nodes
        boolean[] visited = new boolean[V];

        // Variable to count the number of connected components
        int components = 0;

        // Traverse all nodes in the graph
        for (int i = 0; i < V; i++) {

            // If the node is not visited, it's a new component
            if (!visited[i]) {
                components++;

                // Start BFS from this node
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                visited[i] = true;

                // Perform BFS traversal
                while (!q.isEmpty()) {
                    int node = q.poll();

                    // Visit all unvisited neighbors
                    for (int nbr : adj.get(node)) {
                        if (!visited[nbr]) {
                            visited[nbr] = true;
                            q.offer(nbr);
                        }
                    }
                }
            }
        }
        return components;
    }

    /**
     * Approach 2: Depth First Search (DFS)
     * Time Complexity: O(V + E) - Standard DFS traversal.
     * Space Complexity: O(V + E) - Adjacency list and recursion stack.
     */
    public int countComponentsDFS(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[V];
        int components = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                components++;
                dfsHelper(i, adj, visited);
            }
        }
        return components;
    }

    private void dfsHelper(int node, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int nbr : adj.get(node)) {
            if (!visited[nbr]) {
                dfsHelper(nbr, adj, visited);
            }
        }
    }

    public static void main(String[] args) {
        GraphConnectedComponents solver = new GraphConnectedComponents();

        int V = 6;
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        // Expected: Component 1: {0,1,2}, Component 2: {3,4}, Component 3: {5}

        System.out.println("Graph Data: V=" + V + ", Edges=" + Arrays.deepToString(edges));

        // Running BFS Approach
        int bfsCount = solver.countComponentsBFS(V, edges);
        System.out.println("BFS Total Components: " + bfsCount);

        // Running DFS Approach
        int dfsCount = solver.countComponentsDFS(V, edges);
        System.out.println("DFS Total Components: " + dfsCount);
    }
}
