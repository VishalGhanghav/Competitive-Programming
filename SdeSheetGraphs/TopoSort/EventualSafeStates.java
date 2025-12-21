package SdeSheetGraphs.TopoSort;

import java.util.*;

class EventualSafeStates {

    /**
     * APPROACH 1: BFS (Reverse Kahn's) - Original Logic
     * Time Complexity: O(V + E log V) due to sorting at the end
     * Space Complexity: O(V + E) for the reversed adjacency list
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // Using the BFS(Toposort way)
        // create adjacency list
        List<List<Integer>> adjReverseList = new ArrayList<>();
        // create a reverse graph
        int noOfNodes = graph.length;
        for (int i = 0; i < noOfNodes; i++) {
            adjReverseList.add(new ArrayList<>());
        }
        int[] indegree = new int[noOfNodes];
        for (int i = 0; i < noOfNodes; i++) {
            // If there is edge 0->1.
            // I want to store 1->0
            // In row0,I get all my neighbours for 0.Similarly for 1,2..
            for (int nb : graph[i]) {
                adjReverseList.get(nb).add(i);
                indegree[i]++;
            }
        }
        // follow topo sort logic
        List<Integer> safeNodes = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < noOfNodes; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            safeNodes.add(node);
            for (int i : adjReverseList.get(node)) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }
        }

        // sort the safeNodes list
        Collections.sort(safeNodes);
        return safeNodes;
    }

    /**
     * APPROACH 2: DFS (Cycle Detection) - Better/Alternative
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public List<Integer> eventualSafeNodesDFS(int[][] graph) {
        int V = graph.length;
        int[] visited = new int[V];
        int[] pathVisited = new int[V];
        int[] check = new int[V]; // Stores if a node is safe

        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                dfsCheck(i, graph, visited, pathVisited, check);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (check[i] == 1) safeNodes.add(i);
        }
        return safeNodes;
    }

    private boolean dfsCheck(int node, int[][] graph, int[] visited, int[] pathVisited, int[] check) {
        visited[node] = 1;
        pathVisited[node] = 1;
        check[node] = 0; // Assume unsafe initially

        for (int neighbor : graph[node]) {
            if (visited[neighbor] == 0) {
                if (dfsCheck(neighbor, graph, visited, pathVisited, check)) return true;
            } else if (pathVisited[neighbor] == 1) {
                return true;
            }
        }

        check[node] = 1; // If no cycles found in paths, it's safe
        pathVisited[node] = 0;
        return false;
    }

    public static void main(String[] args) {
        EventualSafeStates solver = new EventualSafeStates();
        int[][] graph = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};

        System.out.println("--- BFS (Original Logic) Result ---");
        List<Integer> bfsResult = solver.eventualSafeNodes(graph);
        System.out.println(bfsResult);

        System.out.println("\n--- DFS Result ---");
        List<Integer> dfsResult = solver.eventualSafeNodesDFS(graph);
        System.out.println(dfsResult);
    }
}
