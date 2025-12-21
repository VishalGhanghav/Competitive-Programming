package SdeSheetGraphs.Learning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConnectedComponentsInUndirectedGraph {
    /**
     * Approach 1: Depth First Search (DFS)
     * TC: O(V + E) - Every vertex and edge is visited once.
     * SC: O(V + E) - To store the adjacency list and visited array.
     */
    public ArrayList<ArrayList<Integer>> getComponentsDFS(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = buildAdjacencyList(V, edges);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[V];

        for(int i=0;i<V;i++) {
            if(!visited[i]) {
                ArrayList<Integer> components = new ArrayList<>();
                dfs(i,adj, components, visited);
                result.add(components);
            }
        }
        return result;
    }

    private void dfs(int node, ArrayList<ArrayList<Integer>> adjList, List<Integer> components, boolean[] visited) {
        visited[node] = true;
        components.add(node);
        for(int adjNode : adjList.get(node)) {
            if(!visited[adjNode]) {
                dfs(adjNode, adjList, components, visited);
            }
        }
    }

    /**
     * Approach 2: Breadth First Search (BFS)
     * TC: O(V + E) - Standard BFS complexity.
     * SC: O(V + E) - Adjacency list and queue/visited storage.
     */
    public ArrayList<ArrayList<Integer>> getComponentsBFS(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = buildAdjacencyList(V, edges);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                ArrayList<Integer> component = new ArrayList<>();
                Queue<Integer> q = new LinkedList<>();

                q.add(i);
                visited[i] = true;

                while (!q.isEmpty()) {
                    int curr = q.poll();
                    component.add(curr);

                    for (int neighbor : adj.get(curr)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            q.add(neighbor);
                        }
                    }
                }
                result.add(component);
            }
        }
        return result;
    }

    /**
     * Utility method to build Adjacency List
     * TC: O(V + E)
     * SC: O(V + E)
     */
    private ArrayList<ArrayList<Integer>> buildAdjacencyList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return adj;
    }

    public static void main(String[] args) {
        ConnectedComponentsInUndirectedGraph solution = new ConnectedComponentsInUndirectedGraph();
        int V = 5;
        int[][] edges = {{0, 1}, {2, 1}, {3, 4}};

        System.out.println("Input: V = 5, Edges = [[0, 1], [2, 1], [3, 4]]");

        // Running DFS Approach
        ArrayList<ArrayList<Integer>> resDFS = solution.getComponentsDFS(V, edges);
        System.out.println("DFS Approach Result: " + resDFS);

        // Running BFS Approach
        ArrayList<ArrayList<Integer>> resBFS = solution.getComponentsBFS(V, edges);
        System.out.println("BFS Approach Result: " + resBFS);
    }


}
